////package com.security;
////
////import java.security.Key;
////import java.util.Date;
////import java.util.function.Function;
////
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Component;
////
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
////import io.jsonwebtoken.security.Keys;
////
////@Component
////public class JwtHelper {
////
////	// Secret Key (must be at least 256 bits for HS256)
////	private final String SECRET = "MySuperSecretKeyForJwtToken1234567890"; // ❌ production me env variable se lo
////
////	// Token validity (5 hours)
////	private final long TOKEN_VALIDITY = 5 * 60 * 60 * 1000;
////
////	private Key getSigningKey() {
////		return Keys.hmacShaKeyFor(SECRET.getBytes());
////	}
////
////	// =================== Generate Token ===================
////	public String generateToken(UserDetails userDetails) {
////		return Jwts.builder().setSubject(userDetails.getUsername()) // email as subject
////				.claim("role", userDetails.getAuthorities().iterator().next().getAuthority()) // add role
////				.setIssuedAt(new Date(System.currentTimeMillis()))
////				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
////				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
////	}
////
////	// =================== Extract Username ===================
////	public String extractUsername(String token) {
////		return extractClaim(token, Claims::getSubject);
////	}
////
////	// =================== Extract Expiration ===================
////	public Date extractExpiration(String token) {
////		return extractClaim(token, Claims::getExpiration);
////	}
////
////	// =================== Extract Custom Claim ===================
////	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
////		final Claims claims = extractAllClaims(token);
////		return claimsResolver.apply(claims);
////	}
////
////	private Claims extractAllClaims(String token) {
////		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
////	}
////
////	// =================== Validate Token ===================
////	public boolean validateToken(String token, UserDetails userDetails) {
////		final String username = extractUsername(token);
////		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
////	}
////
////	private boolean isTokenExpired(String token) {
////		return extractExpiration(token).before(new Date());
////	}
////}
//package com;
//
