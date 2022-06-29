package org.acme.hibernate.orm;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "whiteboard")
@NamedQuery(name = "Whiteboard.findAll", query = "SELECT f FROM Whiteboard f ORDER BY f.name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Whiteboard {

    @Id
    @SequenceGenerator(name = "whiteboardSequence", sequenceName = "known_whiteboard_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "whiteboardSequence")
    private Integer id;

    @Column(length = 100, unique = true)
    private String name;

    @Column(length = 50, unique = true)
    private String businessId;

    @OneToMany(mappedBy = "whiteboard")
    private List<Note> notes;

    public Whiteboard() {
    }

    public Whiteboard(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBussinessId() {
        return this.businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

}