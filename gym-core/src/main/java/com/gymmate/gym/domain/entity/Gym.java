package com.gymmate.gym.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shared.base.AuditableEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gyms")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"members", "classes", "trainers"})
public class Gym extends AuditableEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "address_street")),
        @AttributeOverride(name = "city", column = @Column(name = "address_city")),
        @AttributeOverride(name = "state", column = @Column(name = "address_state")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "address_zip_code")),
        @AttributeOverride(name = "country", column = @Column(name = "address_country"))
    })
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    // Operating Hours
    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @Column(name = "timezone")
    private String timezone;

    // Business Settings
    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Column(name = "booking_advance_days")
    private Integer bookingAdvanceDays = 7;

    @Column(name = "cancellation_hours")
    private Integer cancellationHours = 12;

    @Column(name = "is_active")
    private Boolean isActive = true;

    // Subscription Information
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_plan")
    private SubscriptionPlan subscriptionPlan;

    @Column(name = "subscription_expires_at")
    private java.time.LocalDateTime subscriptionExpiresAt;

    // Relationships
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GymClass> classes = new ArrayList<>();

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trainer> trainers = new ArrayList<>();

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equipment> equipment = new ArrayList<>();

    // Domain Methods
    public boolean isOperational() {
        return isActive && subscriptionExpiresAt.isAfter(java.time.LocalDateTime.now());
    }

    public boolean canBookClass(int daysInAdvance) {
        return daysInAdvance <= bookingAdvanceDays;
    }

    public boolean isWithinCancellationWindow(java.time.LocalDateTime classTime) {
        return java.time.LocalDateTime.now().plusHours(cancellationHours).isBefore(classTime);
    }

    public enum SubscriptionPlan {
        BASIC,
        PREMIUM,
        ENTERPRISE,
        TRIAL
    }
}