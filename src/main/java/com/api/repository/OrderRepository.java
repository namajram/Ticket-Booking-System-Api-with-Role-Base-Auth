package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entites.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
