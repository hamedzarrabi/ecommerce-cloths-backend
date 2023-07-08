package com.hami.Entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double price;
    private String image;
    @Column(name = "key_word")
    private String keyWord;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory categoryId;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private ProductInventory inventoryId;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private ProductDiscount discountId;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private Date modifiedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
