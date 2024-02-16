package com.shoppizza.osahaneat.controller;

import com.shoppizza.osahaneat.payload.ResponseData;
import com.shoppizza.osahaneat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getHomeCategory(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryService.getCategoryHomePage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
