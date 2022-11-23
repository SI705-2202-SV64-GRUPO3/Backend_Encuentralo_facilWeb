package com.encuentralofacil.encuentralofacil.repositories;

import com.encuentralofacil.encuentralofacil.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findOrderItemsByOrderId(Long orderId);
}
