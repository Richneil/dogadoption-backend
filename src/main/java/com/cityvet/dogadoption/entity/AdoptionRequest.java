package com.cityvet.dogadoption.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_requests")
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many requests can belong to one dog
    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;

    // Many requests can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String contactNumber;

    @Column(length = 500)
    private String message;

    private String status; // PENDING / APPROVED / REJECTED

    private LocalDateTime createdAt;

    public AdoptionRequest() {}

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = "PENDING";
    }

    public Long getId() { return id; }

    public Dog getDog() { return dog; }
    public void setDog(Dog dog) { this.dog = dog; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}