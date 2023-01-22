//package com.example.gestionpfe.Security.Administrateur;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class AdminCustomAuthSuccessHandler implements AuthenticationSuccessHandler{
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        AdminPrincipal user = (AdminPrincipal) authentication.getPrincipal();
//        String email = user.getUsername();
//        String domain = email.substring(email.indexOf("@") + 1);
//
//        if (domain.equals("gmail.com")) {
//            response.sendRedirect("/gmail");
//        } else if (domain.equals("yahoo.com")) {
//            response.sendRedirect("/yahoo");
//        } else {
//            response.sendRedirect("/other");
//        }
//    }
//}
