package com.krminc.phr.domain.carenotebook;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.krminc.phr.web.HealthSummary;

/**
 * @author cmccall
 */
@Entity
@Table(name = "carenotebook_nutritionevent", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries ({
	@NamedQuery(name = "NutritionEvent.findByHealthRecordId", query = "SELECT n FROM NutritionEvent n WHERE n.healthRecordId = :healthRecordId")
})
public class NutritionEvent extends HealthSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "event_id", nullable = false)
	private Long eventId;
	@Column(name = "event_date")
	@Temporal(TemporalType.DATE)
	private Date eventDate;
	@Column(name = "event", length = 200)
	private String event;
	@Basic(optional = false)
	@Column(name = "rec_id", nullable = false)
	private Long healthRecordId;
	@Basic(optional = false)
	@Column(name = "data_source_id", nullable = false)
	private long dataSourceId;
	@Column(name = "care_document_id")
	private BigInteger careDocumentId;
	@Column(name = "source_id")
	private BigInteger sourceId;
	@Basic(optional = false)
	@Column(name = "date_added", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "comments", length = 512)
	private String comments;
	@Column(name = "mask", length = 50)
	private String mask;

	public NutritionEvent() {
	}

	public NutritionEvent(Long healthRecordId) {
		super(healthRecordId);
		this.healthRecordId = healthRecordId;
	}

	/** needed to map existing entities by carenotebook form processor **/
	public void setEventId(String eventId) {
		this.eventId = Long.parseLong(eventId);
	}

	public Long getEventId() {
		return eventId;
	}

	// public void setEventId(Long eventId) {
	// this.eventId = eventId;
	// }

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public void setEventDate(String eventDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		this.eventDate = df.parse(eventDate);
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Long getHealthRecordId() {
		return healthRecordId;
	}

	public void setHealthRecordId(Long healthRecordId) {
		this.healthRecordId = healthRecordId;
	}

	public long getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(long dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public BigInteger getSourceId() {
		return sourceId;
	}

	public void setSourceId(BigInteger sourceId) {
		this.sourceId = sourceId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

}
