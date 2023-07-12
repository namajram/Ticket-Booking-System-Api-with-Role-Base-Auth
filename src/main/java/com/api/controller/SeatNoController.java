package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entites.SeatNo;
import com.api.services.impl.SeatNoServiceImpl;

@RestController
@RequestMapping("/api")
public class SeatNoController {
	
	@Autowired
	private SeatNoServiceImpl seatNoServiceImpl;
	

	@PostMapping("/screen/{screenId}/create/seatNo")
    public ResponseEntity<?> createTheatre( @RequestBody SeatNo seatNo,@PathVariable long screenId) {
		try {   
			SeatNo createdUser = seatNoServiceImpl.createSeatNo(seatNo, screenId);
        return ResponseEntity.ok(createdUser);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error create Theatre");
		}
    }
	
	
	@PutMapping("/screen/{screenId}/update/{seatNoId}")
	public ResponseEntity<?> updateSeatNo( @RequestBody SeatNo seatNo,@PathVariable long screenId,@PathVariable long seatNoId  ) {
		try {
			SeatNo updatedUser = seatNoServiceImpl.updateSeatNo(seatNo, screenId, seatNoId);
	        if (updatedUser != null) {
	            return ResponseEntity.ok(updatedUser);
	        }
	        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
	}
	
	@DeleteMapping("/SeatNo/delete/{seatNoId}")
	  
    public ResponseEntity<?> deleteSeatNo(@PathVariable Long seatNoId) {
	  try {
        boolean deleted = seatNoServiceImpl.deleteSeatNo(seatNoId);
        if (deleted) {
        	 return ResponseEntity.ok("successfully deleted");
        }
        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	    }
  
  @GetMapping("/SeatNo/get/{seatNoId}")
 
    public ResponseEntity<?> getSeatNoById(@PathVariable Long seatNoId) {
	  try {
		  SeatNo user = seatNoServiceImpl.getSeatNoById(seatNoId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	    }
  @GetMapping("/screen/{screenId}/seatNo/get")
  
  public ResponseEntity<?> getScreenSeatById(@PathVariable Long screenId) {
	  try {
		  List<SeatNo> seatById = seatNoServiceImpl.getScreenSeatById(screenId);
      if (seatById != null) {
          return ResponseEntity.ok(seatById);
      }
      return ResponseEntity.notFound().build();
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	    }
  
  @GetMapping("/seatNo/get/all")
  
    public ResponseEntity<?> getAllSeatNo() {
	  try {
		  List<SeatNo> users = seatNoServiceImpl.getAllSeatNo();
	  
        return ResponseEntity.ok(users);
  } catch (Exception e) {
		return ResponseEntity.internalServerError().body(e.getMessage());
	}

}

}
