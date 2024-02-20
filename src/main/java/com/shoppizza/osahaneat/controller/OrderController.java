package com.shoppizza.osahaneat.controller;

import com.shoppizza.osahaneat.payload.request.OrderRequest;
import com.shoppizza.osahaneat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("")
    public ResponseEntity<?> loadOrders(@RequestBody OrderRequest orderRequest){

        return new ResponseEntity<>(orderService.insertOrder(orderRequest), HttpStatus.OK);
    }
}
