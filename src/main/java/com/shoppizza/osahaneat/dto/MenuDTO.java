package com.shoppizza.osahaneat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private int id;
    private String title;
    private String image;
    private boolean isFreeship;
    private String description;
    private double price;
}
