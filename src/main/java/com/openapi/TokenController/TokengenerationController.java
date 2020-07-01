package com.openapi.TokenController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openapi.auth.exception.InternalException;
import com.openapi.auth.vo.AuthResp;
import com.openapi.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

@RestController
@RequestMapping("/auth")
public class TokengenerationController {
	
	@Autowired
	TokenService tokenService;
	
	@Operation(summary = "Returns a current date", description = "Returns A date",tags = "security.open", responses = {
			
		      @ApiResponse(description = "Successfully Generated the token", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResp.class))) ,
		      @ApiResponse(description = "not found Operation", responseCode = "404")
		      })
		 
	 @SecurityRequirements(value = {})
	@GetMapping("/token")
	public ResponseEntity getToken() throws InternalException {
		
		
		AuthResp resp = tokenService.generateToken();
		
		return new ResponseEntity(resp, HttpStatus.OK);
		
	}
	
	
	
	

}
