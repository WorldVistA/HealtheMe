/**
 * Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.krminc.phr.web;

import com.krminc.phr.core.AppConfig;
import com.krminc.phr.core.UserConfig;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.*;
import com.krminc.phr.domain.carenotebook.*;
import com.krminc.phr.domain.vitals.*;
import com.krminc.phr.web.admin.Admin;
import com.krminc.phr.web.admin.UserManager;
import com.krminc.phr.web.admin.UserWebResource;
import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.container.servlet.PerSession;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Essentially the controller class for the project.
 * 
 * @author Daniel Shaw (dshaw.com)
 */
@Path("/")
@PerSession
public class Main {

	transient final Logger logger = LoggerFactory.getLogger(Main.class);

	@Context
	protected transient UriInfo uriInfo;
	@Context
	protected transient ResourceContext resourceContext;
	@Context
	protected transient SecurityContext securityContext;
	@Context
	protected transient HttpServletRequest servletRequest;

	private Long userId = null;
	private Long healthRecordId = -1L;

	public Main() {
	}

	/**
	 * @return the Application Name.
	 */
	public String getApplicationName() {
		return AppConfig.APPLICATION_NAME;
	}

	/**
	 * @return an Authenticated User.
	 */
	public User getAuthenticatedUser() {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		EntityManager em = persistenceSvc.getEntityManager();
		try {
			// persistenceSvc.beginTx(); -- Transaction already exists in *some*
			// callers.
			return (User) em.find(User.class, getAuthenticatedUserId());
		} finally {
			persistenceSvc.close();
		}
	}

	/**
	 * @return User ID if user is authenticated, otherwise -1L.
	 */
	public Long getAuthenticatedUserId() {
		if (userId == null) {
			PersistenceService persistenceSvc = PersistenceService
					.getInstance();
			EntityManager em = persistenceSvc.getEntityManager();
			try {
				userId = (Long) em
						.createNamedQuery("User.findUserIdByUsername")
						.setParameter("username", getUsername())
						.getSingleResult();
			} catch (Exception e) {
				logger.warn("Unable to retrieve UserID for user.", e);
				return -1L;
			} finally {
				persistenceSvc.close();
			}
		}
		return userId;
	}

	public String getFirstLogin() {
		if (getUsername() == null) {
			// user not logged in yet
			return "";
		}
		HttpSession session = servletRequest.getSession(true);
		Long localUserId = getAuthenticatedUserId();
		if (securityContext.isUserInRole(UserConfig.ROLE_PATIENT)) {
			Long localHealthRecordId = getPrimaryHealthRecordId(localUserId);
			session.setAttribute("healthRecordId", localHealthRecordId);

			// set user session preferences
			UserPreferences pref = getPreferencesByRecId(localHealthRecordId);
			boolean showCN;
			if (pref != null) {
				showCN = (pref.getShowCarenotebook() == true) ? true : false;
			} else {
				showCN = false;
			}
			session.setAttribute("showCareNotebook", showCN);
		}
		setLoginUpdates(localUserId);
		return "";
	}

	/**
	 * JSP accessible getter for Primary Health Record Id.
	 *
	 * @return healthRecordId
	 */
	public Long getHealthRecordId() {
		// logger.error("Asked for health record Id.");
		return getPrimaryHealthRecordId();
	}

	/**
	 * JSP accessible getter for Primary Health Record Id.
	 *
	 * @return healthRecordId
	 */
	public Long getPrimaryHealthRecordId() {
		return getPrimaryHealthRecordId(getAuthenticatedUserId());
	}

	/**
	 * Primary Health Record ID for an authenticated User.
	 *
	 * @param userId
	 * @return healthRecordId
	 */
	public Long getPrimaryHealthRecordId(Long userId) {
		if (healthRecordId == null || healthRecordId < 0) {
			PersistenceService persistenceSvc = PersistenceService
					.getInstance();
			EntityManager em = persistenceSvc.getEntityManager();
			try {
				User user = (User) em.find(User.class, userId);
				healthRecordId = user.getPrimaryHealthRecord()
						.getHealthRecordId();
			} catch (Exception e) {
				logger.warn("Unable to retrieve healthRecordId for user.", e);
				healthRecordId = -1L;
			} finally {
				persistenceSvc.close();
			}
		}
		return healthRecordId;
	}

	/**
	 * @return Username from Security Context if authenticated, otherwise null.
	 */
	public String getUsername() {
		if (securityContext != null
				&& securityContext.getUserPrincipal() != null) {
			return securityContext.getUserPrincipal().getName();
		}
		return null;
	}

	/**
	 * Does initial database updates upon login
	 *
	 * @param loggedInUserId
	 * @return boolean indicating whether updates succeeded
	 */
	private boolean setLoginUpdates(Long loggedInUserId) {
		boolean returnValue = false;
		if (loggedInUserId > 0) {
			PersistenceService persistenceSvc = PersistenceService
					.getInstance();
			EntityManager em = persistenceSvc.getEntityManager();
			try {
				persistenceSvc.beginTx();
				if (em.createNamedQuery("User.updateLoggedInTime")
						.setParameter("userId", loggedInUserId).executeUpdate() == 1) {
					if (em.createNamedQuery("User.updateLoggedInTotal")
							.setParameter("userId", loggedInUserId)
							.executeUpdate() == 1) {
						returnValue = true;
					}
				}
				persistenceSvc.commitTx();
			} catch (Exception e) {
				logger.warn("Unable to update logged in status for user", e);
				persistenceSvc.rollbackTx();
				returnValue = false;
			} finally {
				persistenceSvc.close();
			}
		}
		return returnValue;
	}

	private UserPreferences getPreferencesByRecId(Long recId) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		EntityManager em = persistenceSvc.getEntityManager();
		UserPreferences pref;
		try {
			HealthRecord hr = (HealthRecord) em
					.createNamedQuery("HealthRecord.findByHealthRecordId")
					.setParameter("healthRecordId", recId).getSingleResult();

			pref = hr.getPreferences();
		} catch (Exception e) {
			// logger.warn("Unable to retrieve UserPreferences for user.", e);
			// // common case, not worth warning
			pref = null;
		} finally {
			persistenceSvc.close();
		}
		return pref;
	}

	/**
	 * Authorization Check
	 *
	 * If a patient is logged on viewing their own record, make sure they are
	 * accessing their own records. Otherwise, throw 403 Forbidden.
	 *
	 * @param healthRecordId
	 */
	public void authorizeFor(Long healthRecordId) {
		// users who need a password reset not allowed
		if (securityContext.isUserInRole(UserConfig.ROLE_PATIENT)
				|| securityContext.isUserInRole(UserConfig.ROLE_CARETAKER)) {
			/*
			 * PATIENT and CARETAKER users only authorized for health data
			 * resources
			 */
			if (!getAuthenticatedUser().authorizedToAccessHealthRecord(
					healthRecordId)) {
				logger.error("Attempted disallowed patient data access by {}",
						securityContext.getUserPrincipal());
				throw new WebApplicationException(Status.FORBIDDEN);
			} /* else allowed to access the record */
		} else {
			logger.error(
					"Invalid patient access attempt without Patient role by {}",
					securityContext.getUserPrincipal());
			throw new WebApplicationException(Status.FORBIDDEN);
		}
	}

	/**
	 * "Generic" resource method for retrieving domain objects.
	 *
	 * <p>
	 * Performs a patient authorization check for all method calls that pass
	 * through here.
	 * </p>
	 *
	 * @param patientId
	 * @param resourceType
	 * @param resourceKey
	 * @param resourceId
	 * @return Object of the resourceType
	 */
	private Object patientWebResource(Long healthRecordId, String resourceType,
			String resourceKey, Long resourceId) {
		authorizeFor(healthRecordId);

		PersistenceService persistenceSvc = PersistenceService.getInstance();
		EntityManager em = persistenceSvc.getEntityManager();
		try {
			persistenceSvc.beginTx();
			return em
					.createNamedQuery(
							resourceType + ".findByPrimaryKeyForRecord")
					.setParameter("healthRecordId", healthRecordId)
					.setParameter(resourceKey, resourceId).getSingleResult();
		} catch (NoResultException ex) {
			throw new NotFoundException(resourceType + ", " + resourceId
					+ ", not found");
		} finally {
			persistenceSvc.close();
		}
	}

	/**
	 * "Generic" resource method for retrieving care notebook domain objects.
	 * This differs from the patientWebResource method in that this is a
	 * 1-per-patient mapping which is mapped on health record id rather than the
	 * id of the object Note that this method uses a different namedquery and
	 * handles errors differently
	 *
	 * <p>
	 * Performs a patient authorization check for all method calls that pass
	 * through here.
	 * </p>
	 *
	 * @param patientId
	 * @param resourceType
	 * @return Object of the resourceType
	 */
	private Object patientCareResource(Long healthRecordId,
			String qualifiedPackageType, String resourceType) {
		authorizeFor(healthRecordId);

		PersistenceService persistenceSvc = PersistenceService.getInstance();
		EntityManager em = persistenceSvc.getEntityManager();
		try {
			persistenceSvc.beginTx();
			// logger.error("Attempting to find single result with listed healthrecord");
			return em.createNamedQuery(resourceType + ".findByHealthRecordId")
					.setParameter("healthRecordId", healthRecordId)
					.getSingleResult();
		} catch (NoResultException ex) {
			// logger.error("Unable to find a result for NamedQuery {}.findByHealthRecordId",
			// resourceType);

			Class t;
			try {
				t = Class.forName(qualifiedPackageType + "." + resourceType);
				Constructor[] constructors = t.getConstructors();
				for (Constructor c : constructors) {
					Class[] types = c.getParameterTypes();
					if (types.length > 0) {
						for (Class type : types) {
							if (type.getName().equalsIgnoreCase(
									Long.class.getName())) {
								try {
									return c.newInstance(healthRecordId);
								} catch (InstantiationException ex1) {
									logger.error(
											"Error creating new care resource: ",
											ex1);
								} catch (IllegalAccessException ex1) {
									logger.error(
											"Error creating new care resource: ",
											ex1);
								} catch (IllegalArgumentException ex1) {
									logger.error(
											"Error creating new care resource: ",
											ex1);
								} catch (InvocationTargetException ex1) {
									logger.error(
											"Error creating new care resource: ",
											ex1);
								}
							}
						}
					}
				}
				// fall back on default constructor if cant call by type for
				// plugging in hrid
				logger.error("Unable to find appropriate constructor with type Long as argument");
				return t.newInstance();
			} catch (ClassNotFoundException ex1) {
				logger.error("Error creating new care resource: ", ex1);
			} catch (InstantiationException ex2) {
				logger.error("Error creating new care resource: ", ex2);
			} catch (IllegalAccessException ex3) {
				logger.error("Error creating new care resource: ", ex3);
			}
			throw new NotFoundException(resourceType + " not found");
		} finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	private Collection<Object> patientCareResources(Long healthRecordId,
			String qualifiedPackageType, String resourceType) {
		authorizeFor(healthRecordId);

		PersistenceService persistenceSvc = PersistenceService.getInstance();
		EntityManager em = persistenceSvc.getEntityManager();
		try {
			persistenceSvc.beginTx();
			// logger.error("Attempting to find single result with listed healthrecord");
			return em.createNamedQuery(resourceType + ".findByHealthRecordId")
					.setParameter("healthRecordId", healthRecordId)
					.getResultList();
		} catch (NoResultException ex) {
			// logger.error("Unable to find a result for NamedQuery {}.findByHealthRecordId",
			// resourceType);

			Class t;
			try {
				t = Class.forName(qualifiedPackageType + "." + resourceType);
				Constructor[] constructors = t.getConstructors();
				for (Constructor c : constructors) {
					Class[] types = c.getParameterTypes();
					if (types.length > 0) {
						for (Class type : types) {
							if (type.getName().equalsIgnoreCase(
									Long.class.getName())) {
								try {
									List<Object> singleEntryList = new ArrayList<Object>();
									singleEntryList.add(c
											.newInstance(healthRecordId));
									return singleEntryList;
								} catch (InstantiationException ex1) {
									logger.error(
											"Error creating new care resource: ",
											ex1);
								} catch (IllegalAccessException ex1) {
									logger.error(
											"Error creating new care resource: ",
											ex1);
								} catch (IllegalArgumentException ex1) {
									logger.error(
											"Error creating new care resource: ",
											ex1);
								} catch (InvocationTargetException ex1) {
									logger.error(
											"Error creating new care resource: ",
											ex1);
								}
							}
						}
					}
				}
				// fall back on default constructor if cant call by type for
				// plugging in hrid
				logger.error("Unable to find appropriate constructor with type Long as argument");
				List<Object> singleEntryList = new ArrayList<Object>();
				singleEntryList.add(t.newInstance());
				return singleEntryList;
			} catch (ClassNotFoundException ex1) {
				logger.error("Error creating new care resource: ", ex1);
			} catch (InstantiationException ex2) {
				logger.error("Error creating new care resource: ", ex2);
			} catch (IllegalAccessException ex3) {
				logger.error("Error creating new care resource: ", ex3);
			}
			throw new NotFoundException(resourceType + " not found");
		} finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/* --- Controller mappings (try to keep alphabetized) --- */

	/**
	 * Admin service controller
	 *
	 * @return Admin resource
	 */
	@Path(AppConfig.PATH_ADMIN_ROOT + "/")
	public Admin getAdmin() {
		return new Admin();
	}

	/**
	 * Admin service controller
	 *
	 * @return Admin resource
	 */
	@Path(AppConfig.PATH_ADMIN_ROOT + "/users/{userId}/")
	public UserWebResource getAdminUser(@PathParam("userId") Long userId) {
		return new UserWebResource(userId, Admin.APP_AREA_IDENTIFIER);
	}

	/**
	 * User Manager service controller.
	 *
	 * @return UserManager resource
	 */
	@Path(AppConfig.PATH_USERMANAGER_ROOT + "/")
	public UserManager getUserManager() {
		return new UserManager();
	}

	/**
	 * Care taker service controller.
	 *
	 * @return UserManager resource
	 */
	@Path(AppConfig.PATH_CARETAKER_ROOT + "/")
	public CareTaker getCareTaker() {
		return new CareTaker();
	}

	/* --- Patient Specific Domain Object Controllers --- */
	/**
	 * Returns an instance of the patients calendar.
	 *
	 * @param patientId
	 * @return patients Calendar for the current month.
	 */
	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/calendar/")
	public PHRCalendar getCalendar(@PathParam("patientId") Long patientId) {
		authorizeFor(patientId);
		PHRCalendar calendar = new PHRCalendar(patientId);
		// TODO: we probably need to add something to deal with the date range
		// (DAY, WEEK, MONTH, YEAR)
		return calendar;
	}

	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/calendar/{year: (19|20)\\d{2}}}/")
	public PHRCalendar getCalendar(@PathParam("patientId") Long patientId,
			@PathParam("year") int year) {
		authorizeFor(patientId);
		PHRCalendar calendar = new PHRCalendar(patientId, year, 1);
		return calendar;
	}

	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/calendar/{year: (19|20)\\d{2}}/{month: (0[1-9]|1[012]|[1-9])}/")
	public PHRCalendar getCalendar(@PathParam("patientId") Long patientId,
			@PathParam("year") int year, @PathParam("month") int month) {
		authorizeFor(patientId);
		PHRCalendar calendar = new PHRCalendar(patientId, year, month);
		return calendar;
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/")
	public HealthSummary getHealthSummary(@PathParam("patientId") Long patientId) {
		authorizeFor(patientId);
		return new HealthSummary(patientId);
	}

	/**
	 * Allergy controller
	 *
	 * @param patientId
	 * @param allergyId
	 * @return Allergy
	 */
	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/allergies/{allergyId}/")
	public Allergy getAllergy(@PathParam("patientId") Long patientId,
			@PathParam("allergyId") Long allergyId) {
		return (Allergy) patientWebResource(patientId, "Allergy", "allergyId",
				allergyId);
	}

	/**
	 * Immunization controller
	 *
	 * @param patientId
	 * @param allergyId
	 * @return Immunization
	 */
	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/immunizations/{immunizationId}/")
	public Immunization getImmunization(@PathParam("patientId") Long patientId,
			@PathParam("immunizationId") Long immunizationId) {
		return (Immunization) patientWebResource(patientId, "Immunization",
				"immunizationId", immunizationId);
	}

	/**
	 * Medication controller
	 *
	 * @param patientId
	 * @param medicationId
	 * @return Medication
	 */
	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/medications/{medicationId}/")
	public Medication getMedication(@PathParam("patientId") Long patientId,
			@PathParam("medicationId") Long medicationId) {
		return (Medication) patientWebResource(patientId, "Medication",
				"medicationId", medicationId);
	}

	/**
	 * MedicalEvent controller
	 *
	 * @param patientId
	 * @param problemId
	 * @return Problem
	 */
	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/medicalevents/{medicalEventId}/")
	public MedicalEvent getMedicalEvent(@PathParam("patientId") Long patientId,
			@PathParam("medicalEventId") Long medicalEventId) {
		return (MedicalEvent) patientWebResource(patientId, "MedicalEvent",
				"medicalEventId", medicalEventId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/visits/{visitId}/")
	public Visit getVisit(@PathParam("patientId") Long patientId,
			@PathParam("visitId") Long visitId) {
		return (Visit) patientWebResource(patientId, "Visit", "visitId",
				visitId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/exercises/{exerciseId}/")
	public Exercise getExercise(@PathParam("patientId") Long patientId,
			@PathParam("exerciseId") Long exerciseId) {
		return (Exercise) patientWebResource(patientId, "Exercise",
				"exerciseId", exerciseId);
	}

	// vitals paths
	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/vitals/bloodpressure/{bloodpressureid}/")
	public BloodPressure getBloodPressure(
			@PathParam("patientId") Long patientId,
			@PathParam("bloodpressureid") Long bloodPressureId) {
		return (BloodPressure) patientWebResource(patientId, "BloodPressure",
				"bloodPressureId", bloodPressureId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/vitals/bloodsugar/{bloodsugarid}/")
	public BloodSugar getBloodSugar(@PathParam("patientId") Long patientId,
			@PathParam("bloodsugarid") Long bloodSugarId) {
		return (BloodSugar) patientWebResource(patientId, "BloodSugar",
				"bloodSugarId", bloodSugarId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/vitals/temperature/{temperatureid}/")
	public Temperature getTemperature(@PathParam("patientId") Long patientId,
			@PathParam("temperatureid") Long temperatureId) {
		return (Temperature) patientWebResource(patientId, "Temperature",
				"temperatureId", temperatureId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/vitals/weight/{weightid}/")
	public Weight getWeight(@PathParam("patientId") Long patientId,
			@PathParam("weightid") Long weightId) {
		return (Weight) patientWebResource(patientId, "Weight", "weightId",
				weightId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/vitals/heartrate/{heartrateid}/")
	public HeartRate getHeartRate(@PathParam("patientId") Long patientId,
			@PathParam("heartrateid") Long heartRateId) {
		return (HeartRate) patientWebResource(patientId, "HeartRate",
				"heartRateId", heartRateId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/vitals/height/{heightid}/")
	public Height getHeight(@PathParam("patientId") Long patientId,
			@PathParam("heightid") Long heightId) {
		return (Height) patientWebResource(patientId, "Height", "heightId",
				heightId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/vitals/pain/{painid}/")
	public Pain getPain(@PathParam("patientId") Long patientId,
			@PathParam("painid") Long painId) {
		return (Pain) patientWebResource(patientId, "Pain", "painId", painId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT
			+ "/{patientId}/vitals/peakflow/{peakflowid}/")
	public PeakFlow getPeakFlow(@PathParam("patientId") Long patientId,
			@PathParam("peakflowid") Long peakFlowId) {
		return (PeakFlow) patientWebResource(patientId, "PeakFlow",
				"peakFlowId", peakFlowId);
	}

	/* --- Care Notebook Object Controllers --- */

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/emergencyinformation/")
	public EmergencyInformationDTO getEmergencyInformation(
			@PathParam("patientId") Long patientId) {
		EmergencyInformation ei = (EmergencyInformation) patientCareResource(
				patientId, "com.krminc.phr.domain.carenotebook",
				"EmergencyInformation");
		FamilyMembers fm = getFamilyMembers(patientId);
		CareProviders cp = getCareProviders(patientId);
		EmergencyInformationDTO dto = new EmergencyInformationDTO(ei, fm, cp,
				patientId);
		return dto;
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/insuranceinformation/")
	public InsurancesInformation getInsuranceInformation(
			@PathParam("patientId") Long patientId) {
		Vector<InsuranceInformation> c = (Vector) patientCareResources(
				patientId, "com.krminc.phr.domain.carenotebook",
				"InsuranceInformation");
		return new InsurancesInformation(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/specialtyclinics/")
	public SpecialtyClinics getSpecialtyClinics(
			@PathParam("patientId") Long patientId) {
		Vector<SpecialtyClinic> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "SpecialtyClinic");
		return new SpecialtyClinics(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/pharmacies/")
	public Pharmacies getPharmacies(@PathParam("patientId") Long patientId) {
		Vector<Pharmacy> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "Pharmacy");
		return new Pharmacies(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/careproviders/")
	public CareProviders getCareProviders(@PathParam("patientId") Long patientId) {
		Vector<CareProvider> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "CareProvider");
		return new CareProviders(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/contacts/")
	public FamilyMembers getFamilyMembers(@PathParam("patientId") Long patientId) {
		Vector<FamilyMember> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "FamilyMember");
		return new FamilyMembers(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/familyhistory/")
	public FamilyHistory getFamilyHistory(@PathParam("patientId") Long patientId) {
		return (FamilyHistory) patientCareResource(patientId,
				"com.krminc.phr.domain.carenotebook", "FamilyHistory");
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/nutrition/")
	public Nutrition getNutrition(@PathParam("patientId") Long patientId) {
		return (Nutrition) patientCareResource(patientId,
				"com.krminc.phr.domain.carenotebook", "Nutrition");
	}

	/* Logs section of Care Notebook */

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/respiratory/")
	public RespiratoryCareLog getRespiratoryCareLog(
			@PathParam("patientId") Long patientId) {
		Vector<RespiratoryCare> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "RespiratoryCare");
		return new RespiratoryCareLog(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/communication/")
	public CommunicationLog getCommunicationLog(
			@PathParam("patientId") Long patientId) {
		Vector<Communication> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "Communication");
		return new CommunicationLog(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/mobility/")
	public MobilityLog getMobilityLog(@PathParam("patientId") Long patientId) {
		Vector<Mobility> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "Mobility");
		return new MobilityLog(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/rest/")
	public RestLog getRestLog(@PathParam("patientId") Long patientId) {
		Vector<Rest> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "Rest");
		return new RestLog(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/social/")
	public SocialLog getSocialLog(@PathParam("patientId") Long patientId) {
		Vector<Social> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "Social");
		return new SocialLog(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/stress/")
	public StressLog getStressLog(@PathParam("patientId") Long patientId) {
		Vector<Stress> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "Stress");
		return new StressLog(c, patientId);
	}

	/* End Logs section of Care Notebook */

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/transitions/")
	public Transition getTransitions(@PathParam("patientId") Long patientId) {
		return (Transition) patientCareResource(patientId,
				"com.krminc.phr.domain.carenotebook", "Transition");
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/employment/")
	public Employments getEmployments(@PathParam("patientId") Long patientId) {
		Vector<Employment> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "Employment");
		return new Employments(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/school/")
	public Schools getSchools(@PathParam("patientId") Long patientId) {
		Vector<School> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "School");
		return new Schools(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/schoolpersonnel/")
	public SchoolPersonnels getSchoolPersonnel(
			@PathParam("patientId") Long patientId) {
		Vector<SchoolPersonnel> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "SchoolPersonnel");
		return new SchoolPersonnels(c, patientId);
	}

	@Path(AppConfig.PATH_PATIENT_ROOT + "/{patientId}/meetings/")
	public Meetings getMeetings(@PathParam("patientId") Long patientId) {
		Vector<Meeting> c = (Vector) patientCareResources(patientId,
				"com.krminc.phr.domain.carenotebook", "Meeting");
		return new Meetings(c, patientId);
	}

	/* --- Non-Domain Object Controllers --- */
	/**
	 * Login service controller
	 *
	 * @return Login resource
	 */
	@Path("/login/")
	public Login getLogin() {
		getFirstLogin();
		return new Login();
	}

	/**
	 * Logout service controller
	 *
	 * @return Logout resource
	 */
	@Path("/logout")
	public Logout doLogout() {
		return new Logout();
	}

	/**
	 * Error service controller
	 *
	 * @return ErrorDisplay resource
	 */
	@Path("/error/")
	public ErrorDisplay displayError() {
		return new ErrorDisplay();
	}
}