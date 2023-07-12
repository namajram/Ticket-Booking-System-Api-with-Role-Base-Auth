package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entites.SeatNo;
@Repository
public interface SeatNoRepository extends JpaRepository<SeatNo, Long> {
	List<SeatNo> findByScreen_ScreenId(Long screenId);
}
