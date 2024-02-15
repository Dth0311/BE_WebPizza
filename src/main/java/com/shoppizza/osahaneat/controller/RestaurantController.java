package com.shoppizza.osahaneat.controller;

import com.shoppizza.osahaneat.payload.ResponseData;
import com.shoppizza.osahaneat.service.FileService;
import com.shoppizza.osahaneat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private FileService fileService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("")
    public ResponseEntity<?> createRestaurant(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam String subTitle,
            @RequestParam String description,
            @RequestParam boolean isFreeShip,
            @RequestParam String address,
            @RequestParam String openDate
            ){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = restaurantService.createRestaurant(
                file,title,subTitle,description,isFreeShip,address,openDate);
        responseData.setSuccess(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getHomeRestaurant(){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantService.getHomeRestaurant());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename) {
        Resource file = fileService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
