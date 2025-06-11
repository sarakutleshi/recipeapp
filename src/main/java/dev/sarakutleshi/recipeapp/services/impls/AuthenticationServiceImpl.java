package dev.sarakutleshi.recipeapp.services.impls;

import dev.sarakutleshi.recipeapp.security.AppUserDetails;
import dev.sarakutleshi.recipeapp.services.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final Long expireTimeInMs = 86400000L; // expire time in milliseconds = 24 hours
    @Value("${jwt.secret}") // merre vleren prej applicaiton.properties
    private String secretKey;

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getLoggedInUserEmail() {
        Authentication authentication = getAuthentication();
        return authentication.getName();
    }

    public static String getLoggedInUserRole() {
        Authentication authentication = getAuthentication();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities()); // krejt roles dhe permissions ruhen te getAuthorities
        claims.put("id", ((AppUserDetails) userDetails).getUser().getId());
        claims.put("name", ((AppUserDetails) userDetails).getUser().getName());
        claims.put("role", ((AppUserDetails) userDetails).getUser().getRole().name());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // "sub": email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeInMs)) // 24h
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public UserDetails validateToken(String token) {
        String email = extractEmail(token);
        return userDetailsService.loadUserByUsername(email);
    }
}