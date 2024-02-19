package com.shoppizza.osahaneat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "Food")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "is_freeship")
    private boolean isFreeship;

    @Column(name = "time_ship")
    private String timeShip;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    @OneToMany(mappedBy = "food")
    private Set<RatingFood> listRatingFoods;

    @OneToMany(mappedBy = "food")
    private Set<OrderItem> listOrderItems;
}
