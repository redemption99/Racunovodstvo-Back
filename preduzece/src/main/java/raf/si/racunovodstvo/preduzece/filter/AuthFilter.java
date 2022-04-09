package raf.si.racunovodstvo.preduzece.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import raf.si.racunovodstvo.preduzece.exceptions.handler.MyExceptionHandler;
import raf.si.racunovodstvo.preduzece.responses.ErrorResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final String URL = "http://localhost:8081/auth/access";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            String authHeader = httpServletRequest.getHeader("Authorization");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authHeader);

            HttpEntity request = new HttpEntity(headers);

            // baca izuzetak ako nije ispravak token
            ResponseEntity<String> response = new RestTemplate().exchange(URL, HttpMethod.GET, request, String.class);

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        }

    }
}
