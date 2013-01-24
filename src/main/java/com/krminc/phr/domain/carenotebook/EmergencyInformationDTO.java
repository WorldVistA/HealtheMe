package com.krminc.phr.domain.carenotebook;

import java.io.Serializable;

/**
 * @author cmccall
 *
 */
public class EmergencyInformationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private EmergencyInformation ei;
	private FamilyMembers fm;
	private CareProviders cp;
	private Long healthRecordId;

	public Long getHealthRecordId() {
		return healthRecordId;
	}

	public EmergencyInformationDTO(EmergencyInformation e, FamilyMembers f,
			CareProviders c, Long healthRecordId) {
		this.ei = e;
		this.fm = f;
		this.cp = c;
		this.healthRecordId = healthRecordId;
	}

	public EmergencyInformation getEmergencyInformation() {
		return ei;
	}

	public void setEmergencyInformation(EmergencyInformation ei) {
		this.ei = ei;
	}

	public FamilyMembers getFamilyMembers() {
		return fm;
	}

	public void setFamilyMembers(FamilyMembers fm) {
		this.fm = fm;
	}

	public CareProviders getCareProviders() {
		return cp;
	}

	public void setCareProviders(CareProviders cp) {
		this.cp = cp;
	}

}
