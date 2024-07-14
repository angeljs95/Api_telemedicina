package com.NoCountry.telemedicinaBack.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


    private static final String SECRET_KEY = "11111111111111asdaf18asf877777777777777777777777";
    private static final long ACCESS_TOKEN_VALIDATE_SECONDS= 2_592_000L;

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);

    }


    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        long expirationTime= ACCESS_TOKEN_VALIDATE_SECONDS * 1_000;
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60* expirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return  getClaim(token, Claims:: getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username= getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims= getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
      //  long expirationTime= ACCESS_TOKEN_VALIDATE_SECONDS * 1_000;
        return getExpiration(token).before(new Date(System.currentTimeMillis()));//System.currentTimeMillis()+ expirationTime));
    }

}
