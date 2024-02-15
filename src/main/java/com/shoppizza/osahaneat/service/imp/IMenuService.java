package com.shoppizza.osahaneat.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface IMenuService {
    boolean createMenu(MultipartFile file, String title, boolean isFreeShip, String time_ship, double price, int cate_id);
}
