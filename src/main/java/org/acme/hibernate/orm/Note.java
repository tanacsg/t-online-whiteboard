package org.acme.hibernate.orm;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "note")
@NamedQuery(name = "Note.findAll", query = "SELECT f FROM Note f ORDER BY f.id", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Note {

    @Id
    @SequenceGenerator(name = "noteSequence", sequenceName = "known_note_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "noteSequence")
    private Integer id;

    @Column(length = 500)
    private String text;

    @Column(length = 50)
    private String color;

    @Column
    private int x;

    @Column
    private int y;

    public Note() {
    }

    public Note(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}