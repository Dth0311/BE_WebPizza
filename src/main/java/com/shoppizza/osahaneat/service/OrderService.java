package com.shoppizza.osahaneat.service;

import com.shoppizza.osahaneat.entity.*;
import com.shoppizza.osahaneat.entity.keys.KeyOrderItem;
import com.shoppizza.osahaneat.payload.request.OrderRequest;
import com.shoppizza.osahaneat.repository.OrderItemRepository;
import com.shoppizza.osahaneat.repository.OrderRepository;
import com.shoppizza.osahaneat.service.imp.IOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try {
            Users users = new Users();
            users.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getRestId());


            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setRestaurant(restaurant);
            orderRepository.save(orders);

            List<OrderItem> itemList = new ArrayList<>();
            for (var idFood:orderRequest.getFoodIds()) {
                OrderItem orderItem = new OrderItem();
                KeyOrderItem keyOrderItem = new KeyOrderItem(orders.getId(),idFood);
                orderItem.setKeys(keyOrderItem);
                itemList.add(orderItem);
            }
            orderItemRepository.saveAll(itemList);
            return true;
        }catch (Exception ex){
            System.out.println("Error insert order: " + ex.getMessage());
            return false;
        }
    }
}
