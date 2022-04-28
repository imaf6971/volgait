package ru.tisbi.volgait.applications;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.tisbi.volgait.model.AbstractEntity;
import ru.tisbi.volgait.security.registration.User;

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
}
