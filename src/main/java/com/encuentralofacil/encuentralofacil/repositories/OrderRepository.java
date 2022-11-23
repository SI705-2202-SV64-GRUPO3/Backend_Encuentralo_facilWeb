package com.encuentralofacil.encuentralofacil.repositories;

import com.encuentralofacil.encuentralofacil.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findOrdersByClientId(Long clientId);
}
