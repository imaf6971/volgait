package ru.tisbi.volgait.applications;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.tisbi.volgait.model.AbstractEntity;
import ru.tisbi.volgait.security.core.User;

@Entity
@Table(name = "applications")
public class Application extends AbstractEntity {

    @Column(name = "unique_id", unique = true)
    private UUID uuid;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", updatable = false)
    private User createdBy;

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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
    
}
