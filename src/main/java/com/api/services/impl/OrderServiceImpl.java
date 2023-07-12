package com.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entites.Order;
import com.api.repository.OrderRepository;
import com.api.services.OrderService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	

	@Override
	public Order updateOrder(long orderId, Order order ) {
		Order existOrder =orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order Id not found"));
		existOrder.setPaymentStatus(order.getPaymentStatus());
		existOrder.setTheatre(order.getTheatre());
		existOrder.setUser(order.getUser());
		
		return orderRepository.save(existOrder);
	}
	
	@Override
	  public boolean deleteOrder(long orderId) {
  Optional<Order> optional = orderRepository.findById(orderId);
  if (optional.isPresent()) {
	  orderRepository.delete(optional.get());
      return true;
  }
  return false;
}
	@Override
	 public Order getOrderById(long orderId) {
	        Optional<Order> optional = orderRepository.findById(orderId);
	        return optional.orElseThrow(() -> new EntityNotFoundException("Order Id not found"));
	    }

	@Override
	public List<Order> getAllOrder() {
		
		return orderRepository.findAll();
	}

}
