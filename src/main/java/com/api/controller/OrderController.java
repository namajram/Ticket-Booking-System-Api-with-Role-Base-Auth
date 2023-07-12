package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entites.Order;
import com.api.services.impl.OrderServiceImpl;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@PutMapping("/Order/update/{orderId}")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> updateOrder(@PathVariable long orderId,@RequestBody Order order ) {
		try {
			Order updatedUser = orderServiceImpl.updateOrder(orderId, order);
	        if (updatedUser != null) {
	            return ResponseEntity.ok(updatedUser);
	        }
	        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
	}
	
	@DeleteMapping("/Order/delete/{orderId}")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
	  try {
        boolean deleted = orderServiceImpl.deleteOrder(orderId);
        if (deleted) {
        	 return ResponseEntity.ok("successfully deleted");
        }
        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	    }
  
  @GetMapping("/Order/get/{orderId}")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
	  try {
		  Order user = orderServiceImpl.getOrderById(orderId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	    }
  
  @GetMapping("/Order/get/all")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllOrder() {
	  try {
		  List<Order> users = orderServiceImpl.getAllOrder();
	  
        return ResponseEntity.ok(users);
  } catch (Exception e) {
		return ResponseEntity.internalServerError().body(e.getMessage());
	}

}
}
