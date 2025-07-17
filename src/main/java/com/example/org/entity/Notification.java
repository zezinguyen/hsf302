package com.example.org.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipient_id", nullable = false)
    private Long recipientId; // Can be patient_id or user_id

    @Column(name = "recipient_type", nullable = false, length = 20)
    // Consider using an Enum for recipient type: PATIENT, USER
    private String recipientType;

    @Column(name = "message", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String message;

    @Column(name = "notification_type", nullable = false, length = 50)
    // Consider using an Enum for notification type: EMAIL, SMS, IN_APP_MESSAGE
    private String notificationType;

    @Column(name = "send_time", nullable = false)
    private LocalDateTime sendTime;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "status", nullable = false, length = 20)
    // Consider using an Enum for status: PENDING, SENT, FAILED
    private String status;

    @Column(name = "related_entity_type", length = 50)
    private String relatedEntityType; // e.g., 'APPOINTMENT', 'MEDICAL_RECORD'

    @Column(name = "related_entity_id")
    private Long relatedEntityId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
