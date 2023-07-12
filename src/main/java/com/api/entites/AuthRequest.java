package com.api.entites;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	
	@NotNull(message = "username or email must not null")
	private String email ;
	
	@NotNull(message = "password must not null")
    private String password;

	
	
}
