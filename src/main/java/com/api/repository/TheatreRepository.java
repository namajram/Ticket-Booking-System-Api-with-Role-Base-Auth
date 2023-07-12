package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entites.Theatre;
@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long>{

}
