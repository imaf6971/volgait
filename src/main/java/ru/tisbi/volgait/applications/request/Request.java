package ru.tisbi.volgait.applications.request;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.tisbi.volgait.model.AbstractEntity;

@Entity
@Table(name = "requests")
public class Request extends AbstractEntity {

	@ManyToOne(cascade = ALL, fetch = EAGER, optional = false)
	@JoinColumn(name = "type_id", nullable = false)
	private RequestType type;

	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;

	@Column(name = "additional_info", columnDefinition = "TEXT", nullable = false)
	private String additionalInfo;

	public Request() {

	}

	public Request(RequestType requestType, String additionalInfo) {
		this.type = requestType;
		this.additionalInfo = additionalInfo;
		this.dateTime = LocalDateTime.now();
	}

	public RequestType getType() {
		return type;
	}

	public void setType(RequestType type) {
		this.type = type;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}
