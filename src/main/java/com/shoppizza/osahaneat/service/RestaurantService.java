package com.shoppizza.osahaneat.service;

import com.shoppizza.osahaneat.dto.CategoryDTO;
import com.shoppizza.osahaneat.dto.MenuDTO;
import com.shoppizza.osahaneat.dto.RestaurantDTO;
import com.shoppizza.osahaneat.entity.Food;
import com.shoppizza.osahaneat.entity.MenuRestaurant;
import com.shoppizza.osahaneat.entity.RatingRestaurant;
import com.shoppizza.osahaneat.entity.Restaurant;
import com.shoppizza.osahaneat.repository.FoodRepository;
import com.shoppizza.osahaneat.repository.MenuRestaurantRepository;
import com.shoppizza.osahaneat.repository.RatingRestaurantRepository;
import com.shoppizza.osahaneat.repository.RestaurantRepository;
import com.shoppizza.osahaneat.service.imp.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileService fileService;

    @Autowired
    RatingRestaurantRepository ratingRestaurantRepository;

    @Autowired
    MenuRestaurantRepository menuRestaurantRepository;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean createRestaurant(MultipartFile file, String title, String subTitle, String description, boolean isFreeShip, String address, String openDate) {
        boolean isCreate = false;
        try {
            boolean isSuccess = fileService.saveFile(file);
            if (isSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubTitle(subTitle);
                restaurant.setDescription(description);
                restaurant.setFreeship(isFreeShip);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setAddress(address);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                Date date = simpleDateFormat.parse(openDate);
                restaurant.setOpenDate(date);
                restaurantRepository.save(restaurant);
                isCreate = true;
            }
        } catch (Exception ex) {
            System.out.println("Error create Restaurant: " + ex.getMessage());
        }
        return isCreate;
    }

    @Override
    public List<RestaurantDTO> getHomeRestaurant() {
        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Restaurant> listRestaurants = restaurantRepository.findAll(pageRequest);
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant item : listRestaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setImage(item.getImage());
            restaurantDTO.setTitle(item.getTitle());
            restaurantDTO.setSubTitle(item.getSubTitle());
            restaurantDTO.setFreeShip(item.isFreeship());
            restaurantDTO.setRating(caculatorRating(ratingRestaurantRepository.findAll(), item.getId()));
            restaurantDTOList.add(restaurantDTO);
        }
        return restaurantDTOList;
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        if (restaurant.isPresent()){
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            List<MenuRestaurant> menuRestaurantList = menuRestaurantRepository.findAll();
            Restaurant data = restaurant.get();
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setSubTitle(data.getSubTitle());
            restaurantDTO.setRating(caculatorRating(ratingRestaurantRepository.findAll(), data.getId()));
            restaurantDTO.setOpenDate(data.getOpenDate());
            restaurantDTO.setFreeShip(data.isFreeship());

            for (var item:menuRestaurantList) {
                if (item.getCategory().getId() == data.getId()) {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setName_cate(item.getCategory().getNameCate());

                    List<Food> foodList = foodRepository.findAll();
                    List<MenuDTO> menuDTOList = new ArrayList<>();
                    for (Food food : foodList) {
                        if (food.getCategory().getId() == item.getCategory().getId()) {
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
            }
            restaurantDTO.setListCategory(categoryDTOList);
        }
        return restaurantDTO;
    }

    private double caculatorRating(List<RatingRestaurant> list, int resId) {
        double totalPoint = 0;
        double count = 0;
        for (RatingRestaurant item : list) {
            if (resId == item.getRestaurant().getId()) {
                totalPoint += item.getRatePoint();
                count++;
            }
        }
        return (totalPoint / count);
    }
}
