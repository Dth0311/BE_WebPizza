package com.shoppizza.osahaneat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private String image;
    private String title;
    private double rating;
    private String subTitle;
    private boolean isFreeShip;
    private Date openDate;
    private List<CategoryDTO> listCategory;
}
