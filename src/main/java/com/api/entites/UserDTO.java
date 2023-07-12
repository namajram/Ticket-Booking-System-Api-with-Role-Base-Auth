package com.api.entites;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	
	private String name;

	
	private String email;
	
	 @NotBlank
	 @Size(min = 8,message = "Password must contain at least 8 characters")
	 @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Password must contain at least one letter and one number")
	private String password;

	
	private UserRole roleName;



	
	
}
