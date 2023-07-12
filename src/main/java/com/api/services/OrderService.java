package com.api.services;

import java.util.List;

import com.api.entites.Order;

public interface OrderService {

	Order updateOrder(long orderId, Order order);

	boolean deleteOrder(long orderId);

	Order getOrderById(long orderId);

	List<Order> getAllOrder();

}
