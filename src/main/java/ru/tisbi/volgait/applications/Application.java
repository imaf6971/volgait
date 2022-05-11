package ru.tisbi.volgait.applications;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.tisbi.volgait.applications.request.Request;
import ru.tisbi.volgait.model.NamedEntity;
import ru.tisbi.volgait.security.user.User;

@Entity
@Table(name = "applications")
public class Application extends NamedEntity {

	@Column(name = "unique_id", unique = true)
	private UUID uuid;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User createdBy;

	@Column(name = "creation_date", nullable = false)
	private LocalDate creationDate;

	@OneToMany(cascade = ALL, fetch = EAGER, orphanRemoval = true)
	@JoinColumn(name = "application_id", nullable = false)
	private List<Request> requests = new ArrayList<>();

	public Application() {

	}

	public Application(UUID uuid, String name, User creator) {
		this.uuid = uuid;
		setName(name);
		this.createdBy = creator;
		this.creationDate = LocalDate.now();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public List<Request> getRequests() {
		return Collections.unmodifiableList(requests);
	}

	public void addRequest(Request request) {
		requests.add(request);
	}
	
}
