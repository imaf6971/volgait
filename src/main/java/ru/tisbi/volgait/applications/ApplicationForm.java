package ru.tisbi.volgait.applications;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class ApplicationForm {
	
	private UUID uuid;
	
	@NotBlank(message = "Название не должно быть пустым")
	private String title;
	
	public ApplicationForm() {
		uuid = UUID.randomUUID();
		title = "";
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
