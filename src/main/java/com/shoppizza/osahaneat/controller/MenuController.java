package com.shoppizza.osahaneat.controller;

import com.shoppizza.osahaneat.payload.ResponseData;
import com.shoppizza.osahaneat.service.FileService;
import com.shoppizza.osahaneat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    FileService fileService;

    @PostMapping("")
    public ResponseEntity<?> createMenu(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam boolean is_freeShip,
            @RequestParam String time_ship,
            @RequestParam double price,
            @RequestParam int cate_id
    ){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = menuService.createMenu(file,title,is_freeShip,time_ship,price,cate_id);
        responseData.setSuccess(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename) {
        Resource file = fileService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
