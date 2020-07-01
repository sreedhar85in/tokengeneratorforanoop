package com.openapi.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.openapi.auth.exception.InternalException;
import com.openapi.auth.vo.AuthResp;

@Service
public class TokenService {
	
	@Value("${jwt.salt}")
    private String jwtSalt;
	
	public AuthResp generateToken() throws InternalException {
		
		  LocalDateTime now = LocalDateTime.now();
	        LocalDateTime exp = now.plusHours(1);
		
		 Date nowDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
	        Date expDate  = Date.from(exp.atZone(ZoneId.systemDefault()).toInstant());
	        
	        AuthResp resp = new AuthResp();

        String token = "";
        Long tester = 1234L;
        try {

            byte[] secret = Base64.decodeBase64(jwtSalt);

            Algorithm algorithmT = Algorithm.HMAC256(secret);

            JWTCreator.Builder builder = JWT.create();
            builder
                    .withIssuer("Somnaware Ltd")
                    .withIssuedAt(nowDate)
                    .withExpiresAt(expDate)
                    .withClaim("moduleCode", "Technichal")
                    .withClaim("traderId", tester)
                    .withClaim("userName", "test")
                    .withClaim("programId", "Developer");

           

            token = builder.sign(algorithmT);

        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new InternalException(-1, "Fail to issue JWT token!", exception.getMessage());
        }

        resp.setExpireAt(exp);

       
        resp.setToken(token);

        return resp;
	}

}
