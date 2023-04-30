package com.tm.store.fillter;

import com.tm.store.model.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter("/cart")
public class AuthorizationFilter implements Filter {

    private static final String LOGIN_PAGE_URI = "/login.jsp";
    private static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isAdmin = false;

        if (isLoggedIn && session.getAttribute("role") instanceof Set) {
            Set<Role> roles = (Set<Role>) session.getAttribute("role");
            for (Role role : roles) {
                if (ROLE_SUPER_ADMIN.equals(role.getName())) {
                    isAdmin = true;
                    break;
                }
            }
        }

        String requestURI = httpRequest.getRequestURI();
        boolean isLoginPage = requestURI.endsWith(LOGIN_PAGE_URI);
        boolean isLoginRequest = requestURI.equals(LOGIN_PAGE_URI + "/login");

        if (isLoggedIn && (isAdmin || isLoginRequest || isLoginPage)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE_URI);
        }
    }
}
