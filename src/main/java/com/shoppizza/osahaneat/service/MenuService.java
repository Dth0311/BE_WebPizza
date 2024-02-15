package com.shoppizza.osahaneat.service;

import com.shoppizza.osahaneat.entity.Category;
import com.shoppizza.osahaneat.entity.Food;
import com.shoppizza.osahaneat.entity.Restaurant;
import com.shoppizza.osahaneat.repository.FoodRepository;
import com.shoppizza.osahaneat.service.imp.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuService implements IMenuService {

    @Autowired
    FileService fileService;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean createMenu(MultipartFile file, String title, boolean isFreeShip, String time_ship, double price, int cate_id) {
        boolean isCreate = false;
        try {
            boolean isSuccess = fileService.saveFile(file);
            if (isSuccess) {
                Food food = new Food();
                food.setTitle(title);
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(time_ship);
                food.setFreeship(isFreeShip);
                food.setPrice(price);

                Category category = new Category();
                category.setId(cate_id);

                food.setCategory(category);
                foodRepository.save(food);
                isCreate = true;
            }
        } catch (Exception ex) {
            System.out.println("Error create Restaurant: " + ex.getMessage());
        }
        return isCreate;
    }
}
