package com.shoppizza.osahaneat.entity;

import com.shoppizza.osahaneat.entity.keys.KeyMenuRestaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "MenuRestaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRestaurant {
    @EmbeddedId
    KeyMenuRestaurant keys;

    @ManyToOne
    @JoinColumn(name = "cate_id",insertable = false,updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "res_id",insertable = false,updatable = false)
    private Restaurant restaurant;

    @Column(name = "create_date")
    private Date createDate;
}
