package Godwin.taxSolution.springSecurity;

import Godwin.taxSolution.entities.TaxPersonnel;
import Godwin.taxSolution.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${JWT_SECRET}")
    private String tokenKey;

    public String createToken(TaxPersonnel taxPersonnel){

        //this method is used for login
        //returns a string
        return Jwts.builder().setSubject(String.valueOf(taxPersonnel.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7 ) )
                .signWith(Keys.hmacShaKeyFor(tokenKey.getBytes())).compact();
    }

    public void verifyToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(tokenKey.getBytes()))
                    .build().parse(token);
        } catch (Exception ex){
            throw new UnauthorizedException("Invalid token, please login!");
        }

    }

    public String extractIdFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(tokenKey.getBytes()))
                .build().parseClaimsJws(token).getBody().getSubject();
    }
}
