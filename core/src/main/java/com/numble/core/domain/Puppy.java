package com.numble.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@ToString
@Getter
@Entity
public class Puppy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String image;

    private String description;

    protected Puppy() {
    }

    public Puppy(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public static Puppy of(String name, String image, String description) {
        return new Puppy(name, image, description);
    }

}
