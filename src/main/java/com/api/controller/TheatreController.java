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

import com.api.entites.Theatre;
import com.api.services.impl.TheatreServiceImpl;

@RestController
@RequestMapping("/api")
public class TheatreController {
	@Autowired
	private TheatreServiceImpl theatreServiceImpl;
	
	@PostMapping("/theatre/create")
    public ResponseEntity<?> createTheatre( @RequestBody Theatre  theatre) {
		try {   
			 Theatre createdUser = theatreServiceImpl.createTheatre(theatre);
        return ResponseEntity.ok(createdUser);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error create Theatre");
		}
    }
	
	 @PutMapping("/theatre/update/{theatreId}")
	 
	    public ResponseEntity<?> updateTheatre(@PathVariable Long theatreId, @RequestBody Theatre  theatre) {
		  try {
			  Theatre updatedUser = theatreServiceImpl.updateTheatre(theatreId, theatre);
	        if (updatedUser != null) {
	            return ResponseEntity.ok(updatedUser);
	        }
	        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
		    }
	 
	 @DeleteMapping("/theatre/delete/{theatreId}")
	  
	    public ResponseEntity<?> deleteTheatre(@PathVariable Long theatreId) {
		  try {
	        boolean deleted = theatreServiceImpl.deleteTheatre(theatreId);
	        if (deleted) {
	        	 return ResponseEntity.ok("successfully deleted");
	        }
	        return ResponseEntity.notFound().build();
		  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
		    }
	  
	  @GetMapping("/theatre/get/{theatreId}")
	 
	    public ResponseEntity<?> getTheatreById(@PathVariable Long theatreId) {
		  try {
			  Theatre user = theatreServiceImpl.getTheatreById(theatreId);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        }
	        return ResponseEntity.notFound().build();
		  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
		    }
	  
	  @GetMapping("/theatre/get/all")
	  
	    public ResponseEntity<?> getAllTheatre() {
		  try {
			  List<Theatre> users = theatreServiceImpl.getAllTheatre();
		  
	        return ResponseEntity.ok(users);
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}

}
}