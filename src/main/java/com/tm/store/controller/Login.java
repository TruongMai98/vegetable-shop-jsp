package com.tm.store.controller;

import com.tm.store.model.dto.UserDto;
import com.tm.store.model.service.IUserService;
import com.tm.store.model.service.impls.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/login"})
public class Login extends HttpServlet {

    private IUserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("login-email");
        String password = request.getParameter("login-password");

        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setPassword(password);

        UserDto authenticatedUser = userService.login(userDto);

        if (authenticatedUser != null && BCrypt.checkpw(password, authenticatedUser.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("role", authenticatedUser.getRoles());
            session.setAttribute("user", authenticatedUser);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
