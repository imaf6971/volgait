package ru.tisbi.volgait.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TitledEntity extends AbstractEntity {

	@Column(name = "title", nullable = false, unique = true)
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
