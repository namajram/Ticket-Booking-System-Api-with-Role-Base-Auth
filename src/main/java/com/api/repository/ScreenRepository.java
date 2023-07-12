package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entites.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long>{

}
