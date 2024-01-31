package com.shoppizza.osahaneat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private String image;
    private String title;
    private double rating;
    private String subTitle;
    private boolean isFreeShip;

}
