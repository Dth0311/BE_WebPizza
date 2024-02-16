package com.shoppizza.osahaneat.service;

import com.shoppizza.osahaneat.dto.CategoryDTO;
import com.shoppizza.osahaneat.dto.MenuDTO;
import com.shoppizza.osahaneat.entity.Category;
import com.shoppizza.osahaneat.entity.Food;
import com.shoppizza.osahaneat.repository.CategoryRepository;
import com.shoppizza.osahaneat.repository.FoodRepository;
import com.shoppizza.osahaneat.service.imp.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<Category> listCategories = categoryRepository.findAll(pageRequest);
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        List<Food> foodList = foodRepository.findAll();
        for (Category data:listCategories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName_cate(data.getNameCate());

            List<MenuDTO> menuDTOList = new ArrayList<>();
            for (Food food: foodList) {
                if(food.getCategory().getId() == data.getId()){
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setFreeship(food.isFreeship());
                    menuDTO.setImage(food.getImage());
                    menuDTOList.add(menuDTO);
                }
            }

            categoryDTO.setMenuDTOS(menuDTOList);

            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }
}
