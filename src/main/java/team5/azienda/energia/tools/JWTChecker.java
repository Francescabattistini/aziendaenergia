package team5.azienda.energia.tools;

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
import team5.azienda.energia.entities.User;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.exceptions.UnauthorizedException;
import team5.azienda.energia.servicies.UserService;

import java.io.IOException;

@Component
public class JWTChecker extends OncePerRequestFilter {
    @Autowired
    private JWT jwt;

    @Autowired
    private UserService userService;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Inserire token nell' Authorization Header nel formato corretto!");
        }

        String accessToken = authorizationHeader.split(" ")[1];
        jwt.verifyToken(accessToken);

        String idUtente = jwt.getIdFromToken(accessToken);

        User utenteCorrente = this.userService.findById(Long.valueOf(idUtente)).orElseThrow(() -> new NotFoundException(idUtente));

        Authentication authentication = new UsernamePasswordAuthenticationToken(utenteCorrente, null, utenteCorrente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
