package com.shoppizza.osahaneat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String name_cate;
    private List<MenuDTO> menuDTOS;
}
