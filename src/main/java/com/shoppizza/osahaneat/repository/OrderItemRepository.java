package com.shoppizza.osahaneat.repository;

import com.shoppizza.osahaneat.entity.OrderItem;
import com.shoppizza.osahaneat.entity.keys.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
}
