package com.encuentralofacil.encuentralofacil.services;

import com.encuentralofacil.encuentralofacil.entities.*;
import com.encuentralofacil.encuentralofacil.repositories.OrderItemRepository;
import com.encuentralofacil.encuentralofacil.repositories.OrderRepository;
import com.encuentralofacil.encuentralofacil.repositories.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private StoreProductRepository storeProductRepository;

    @Transactional
    public void createOrder(List<CartItem> cartItems, Long storeId, Long clientId) {
        Map<Long, Integer> productQuantityMap = new HashMap<>();
        List<Long> productIds = new ArrayList<>();

        for (CartItem cartItem: cartItems) {
            productIds.add(cartItem.getProductId());
            productQuantityMap.put(cartItem.getProductId(), cartItem.getQuantity());
        }

        List<StoreProduct> storeProducts = this.storeProductRepository.findProductsIdsAndStore(productIds, storeId);

        Order order = new Order();
        order.setPurchaseDate(new Date());
        order.setClientId(clientId);
        order = this.orderRepository.save(order);

        Long orderId = order.getId();

        for (StoreProduct sp: storeProducts) {
            OrderItem orderItem = new OrderItem();
            orderItem.setStoreProductId(sp.getId());
            orderItem.setQuantity(productQuantityMap.get(sp.getProductId()));
            orderItem.setPurchasePrice(sp.getPrice());
            orderItem.setOrderId(orderId);

            this.orderItemRepository.save(orderItem);
        }
    }

    public List<OrderWithTotal> getOrderHistory(Long clientId) {
        List<Order> orders = this.orderRepository.findOrdersByClientId(clientId);
        List<OrderWithTotal> totalOrders = orders.stream().map(order -> {
            List<OrderItem> orderItems = this.orderItemRepository.findOrderItemsByOrderId(order.getId());

            float total = 0;
            for (OrderItem orderItem: orderItems) {
                total += orderItem.getPurchasePrice() * orderItem.getQuantity();
            }

            OrderWithTotal totalOrder = new OrderWithTotal();
            totalOrder.setTotal(total);
            totalOrder.setId(order.getId());
            totalOrder.setClientId(order.getClientId());
            totalOrder.setPurchaseDate(order.getPurchaseDate());

            return totalOrder;
        }).collect(Collectors.toList());

        return totalOrders;
    }
}
