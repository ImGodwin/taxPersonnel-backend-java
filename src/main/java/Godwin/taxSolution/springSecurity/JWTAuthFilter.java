package Godwin.taxSolution.springSecurity;

import Godwin.taxSolution.entities.TaxPersonnel;
import Godwin.taxSolution.exceptions.UnauthorizedException;
import Godwin.taxSolution.service.TaxPersonnelService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private TaxPersonnelService taxPersonnelService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new UnauthorizedException("Please add the Bearer Token");
        } else {
            String token = authHeader.substring(7);
            System.out.println("TOKEN -> " + token);

            jwtTools.verifyToken(token);

            String id = jwtTools.extractIdFromToken(token);
            TaxPersonnel taxPersonnelService1= taxPersonnelService.findById(UUID.fromString(id));

            Authentication authentication = new  UsernamePasswordAuthenticationToken(taxPersonnelService1, null,
                    taxPersonnelService1.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        }
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/authenticate/**", request.getServletPath());
    }
}
