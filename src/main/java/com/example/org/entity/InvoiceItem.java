package com.example.org.entity;

import com.example.org.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false, referencedColumnName = "id")
    private Invoice invoice;

    @Column(name = "item_type", nullable = false, length = 50)
    // Consider using an Enum for item type: DRUG, SERVICE, TEST
    private String itemType;

    @Column(name = "item_id")
    private Long itemId; // This could be a generic ID, or you could use polymorphic associations

    @Column(name = "item_name", nullable = false, length = 255)
    private String itemName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "notes", columnDefinition = "NVARCHAR(MAX)")
    private String notes;
}
