package ru.tisbi.volgait.applications.request;

import javax.persistence.Entity;
import javax.persistence.Table;

import ru.tisbi.volgait.model.TitledEntity;

@Entity
@Table(name = "request_titles")
public class RequestType extends TitledEntity {

	public RequestType() {

	}

	public RequestType(String requestTitle) {
		setTitle(requestTitle);
	}

}
