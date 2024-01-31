package com.shoppizza.osahaneat.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyMenuRestaurant implements Serializable {
    @Column(name = "cate_id")
    private int cateId;

    @Column(name = "res_id")
    private int resID;

}
