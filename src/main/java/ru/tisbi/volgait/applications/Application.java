package ru.tisbi.volgait.applications;

import lombok.Getter;
import lombok.Setter;
import ru.tisbi.volgait.registration.domain.User;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "unique_id", unique = true)
    private UUID uuid;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", updatable = false)
    private User createdBy;
}
