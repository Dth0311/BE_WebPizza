package com.shoppizza.osahaneat.service.imp;

import com.shoppizza.osahaneat.dto.RestaurantDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRestaurantService {
    boolean createRestaurant(
             MultipartFile file,
             String title,
             String subTitle,
             String description,
             boolean isFreeShip,
             String address,
             String openDate
    );

    List<RestaurantDTO> getHomeRestaurant();
}
