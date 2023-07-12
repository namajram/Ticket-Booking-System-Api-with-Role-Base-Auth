package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.entites.Screen;
import com.api.services.impl.ScreenServiceImpl;

@RestController
@RequestMapping("/api")
public class ScreenController {

	@Autowired
	private ScreenServiceImpl screenServiceImpl;
	
	@PostMapping("/{theatreId}/screen/create")
    public ResponseEntity<?> createScreen(@ModelAttribute Screen screen,@PathVariable long theatreId,@RequestParam("image") MultipartFile file)throws Throwable {
		try {   
			Screen createdUser = screenServiceImpl.createScreen(screen, theatreId, file);
        return ResponseEntity.ok(createdUser);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error create Theatre");
		}
    }
	
	 @PutMapping("/{theatreId}/screen/update/{screenId}")
	 
	    public ResponseEntity<?> updateScreen(@PathVariable long screenId,@ModelAttribute Screen screen,@PathVariable long theatreId,@RequestParam("image") MultipartFile file)throws Throwable {
		  try {
			  Screen updatedUser = screenServiceImpl.updateScreen(screenId, screen, theatreId, file);
	        if (updatedUser != null) {
	            return ResponseEntity.ok(updatedUser);
	        }
	        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
		    }
	 
	 @DeleteMapping("/screen/delete/{screenId}")
	  
	    public ResponseEntity<?> deleteScreen(@PathVariable Long screenId) {
		  try {
	        boolean deleted = screenServiceImpl.deleteScreen(screenId);
	        if (deleted) {
	        	 return ResponseEntity.ok("successfully deleted");
	        }
	        return ResponseEntity.notFound().build();
		  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
		    }
	  
	  @GetMapping("/screen/get/{screenId}")
	 
	    public ResponseEntity<?> getScreenById(@PathVariable Long screenId) {
		  try {
			  Screen user = screenServiceImpl.getScreenById(screenId);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        }
	        return ResponseEntity.notFound().build();
		  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
		    }
	  
	  @GetMapping("/sceen/get/all")
	  
	    public ResponseEntity<?> getAllScreen() {
		  try {
			  List<Screen> users = screenServiceImpl.getAllScreen();
		  
	        return ResponseEntity.ok(users);
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	  }
}
