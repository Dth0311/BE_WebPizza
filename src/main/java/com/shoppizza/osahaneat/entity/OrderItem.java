package com.shoppizza.osahaneat.entity;

import com.shoppizza.osahaneat.entity.keys.KeyOrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "OrderItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @EmbeddedId
    KeyOrderItem keys;

    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false,updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "food_id",insertable = false,updatable = false)
    private Food food;

    @Column(name = "create_date")
    private Date createDate;
}
