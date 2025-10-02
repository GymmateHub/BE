package com.gymmate.gym.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shared.base.AuditableEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members", indexes = {
    @Index(name = "idx_member_email_gym", columnList = "email, gym_id"),
    @Index(name = "idx_member_membership_number", columnList = "membership_number")
})
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"gym", "bookings", "healthMetrics"})
public class Member extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;

    @Column(name = "membership_number", unique = true, nullable = false)
    private String membershipNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "address_street")),
        @AttributeOverride(name = "city", column = @Column(name = "address_city")),
        @AttributeOverride(name = "state", column = @Column(name = "address_state")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "address_zip_code")),
        @AttributeOverride(name = "country", column = @Column(name = "address_country"))
    })
    private Address address;

    // Emergency Contact
    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    @Column(name = "emergency_contact_relationship")
    private String emergencyContactRelationship;

    // Membership Details
    @Enumerated(EnumType.STRING)
    @Column(name = "membership_type")
    private MembershipType membershipType;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_status")
    private MembershipStatus membershipStatus = MembershipStatus.ACTIVE;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Column(name = "membership_expires_at")
    private LocalDateTime membershipExpiresAt;

    @Column(name = "freeze_start_date")
    private LocalDate freezeStartDate;

    @Column(name = "freeze_end_date")
    private LocalDate freezeEndDate;

    // Health and Fitness
    @Column(name = "has_health_conditions")
    private Boolean hasHealthConditions = false;

    @Column(name = "health_notes", columnDefinition = "TEXT")
    private String healthNotes;

    // Preferences
    @Column(name = "preferred_contact_method")
    @Enumerated(EnumType.STRING)
    private ContactMethod preferredContactMethod = ContactMethod.EMAIL;

    @Column(name = "marketing_consent")
    private Boolean marketingConsent = false;

    // Profile
    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "last_check_in")
    private LocalDateTime lastCheckIn;

    // Relationships
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassBooking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HealthMetric> healthMetrics = new ArrayList<>();

    // Domain Methods
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isMembershipActive() {
        return membershipStatus == MembershipStatus.ACTIVE && 
               (membershipExpiresAt == null || membershipExpiresAt.isAfter(LocalDateTime.now()));
    }

    public boolean isMembershipFrozen() {
        return membershipStatus == MembershipStatus.FROZEN ||
               (freezeStartDate != null && freezeEndDate != null &&
                LocalDate.now().isAfter(freezeStartDate) && LocalDate.now().isBefore(freezeEndDate));
    }

    public int getAge() {
        if (dateOfBirth == null) return 0;
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    public boolean canBookClasses() {
        return isMembershipActive() && !isMembershipFrozen() && membershipStatus != MembershipStatus.SUSPENDED;
    }

    public enum Gender {
        MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY
    }

    public enum MembershipType {
        MONTHLY, 
        QUARTERLY, 
        SEMI_ANNUAL, 
        ANNUAL, 
        CLASS_PACKAGE, 
        DAY_PASS,
        FAMILY,
        CORPORATE
    }

    public enum MembershipStatus {
        ACTIVE, 
        FROZEN, 
        SUSPENDED, 
        CANCELLED, 
        EXPIRED,
        PENDING_ACTIVATION
    }

    public enum ContactMethod {
        EMAIL, SMS, PHONE, IN_APP
    }
}