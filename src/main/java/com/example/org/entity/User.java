package com.example.org.entity; // Updated package

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(exclude = {"roles", "patient", "doctor", "staff"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "is_active", nullable = false, columnDefinition = "BIT DEFAULT 1")
    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Relationships
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Patient patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Doctor doctor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Staff staff;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Schedule> schedules = new HashSet<>();

    // For relationships with ON DELETE SET NULL in SQL, you typically don't use CascadeType.SET_NULL
    // as it's not a JPA cascade type. The nullable attribute on the @JoinColumn in the child entity
    // and the database's ON DELETE SET NULL constraint handle this.
    // We remove the invalid CascadeType.SET_NULL here.
    @OneToMany(mappedBy = "createdBy") // Removed invalid CascadeType.SET_NULL
    private Set<Invoice> createdInvoices = new HashSet<>();

    @OneToMany(mappedBy = "performedBy") // Removed invalid CascadeType.SET_NULL
    private Set<TestResult> performedTestResults = new HashSet<>();

    @OneToMany(mappedBy = "user") // Removed invalid CascadeType.SET_NULL
    private Set<StockTransaction> stockTransactions = new HashSet<>();

    // Helper methods to manage relationships (optional, but good practice)
    public void addRole(Role role) {
        this.roles.add(role);
        // Ensure the other side of the relationship is also updated
        // This line assumes Role entity has a public getUsers() method, which Lombok should generate.
        // If you still get error here, check your Lombok setup.
        if (role.getUsers() != null) { // Added null check for safety
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        // Ensure the other side of the relationship is also updated
        // This line assumes Role entity has a public getUsers() method, which Lombok should generate.
        // If you still get error here, check your Lombok setup.
        if (role.getUsers() != null) { // Added null check for safety
            role.getUsers().remove(this);
        }
    }
}
