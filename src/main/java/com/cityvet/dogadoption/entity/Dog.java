package com.cityvet.dogadoption.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String breed;
    private Integer age;
    private String status; // AVAILABLE, ADOPTED

    public Dog() {}

    public Dog(String name, String breed, Integer age, String status) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}