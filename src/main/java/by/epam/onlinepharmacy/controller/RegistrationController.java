package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.dto.UserRegDto;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.service.UserService;
import by.epam.onlinepharmacy.service.impl.UserServiceImpl;
import by.epam.onlinepharmacy.verification.MailSender;


import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    private UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        String loginRegistration = req.getParameter("loginRegistration");
        String passwordRegistration = req.getParameter("passwordRegistration");
        String firstNameRegistration = req.getParameter("firstNameRegistration");
        String lastNameRegistration = req.getParameter("lastNameRegistration");
        String emailRegistration = req.getParameter("emailRegistration");
        String telephoneRegistration = req.getParameter("telephoneRegistration");
        Role userRole = Role.valueOf(req.getParameter("role"));
        UserRegDto userRegDto = new UserRegDto(loginRegistration, passwordRegistration,
                firstNameRegistration, lastNameRegistration, emailRegistration, telephoneRegistration, userRole);
        userService.createUser(userRegDto);
//
//        Properties properties = new Properties();
//        try {
//            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties"));
////            properties.load(new FileReader("E://epam//onlinepharmacy//src//main//resources//mail.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String mailTo = "testpostepam@gmail.com";
//        String subject = "hello";
//        String body = "Hello";
//        MailSender sender = new MailSender(mailTo, subject, body, properties);
//        sender.send();

//        if (UserRegistrationService.userRegistration(userRegistration, connection)) {
//            String errorRegistration = "Such login is used";
//            req.getSession().setAttribute("errorRegistration", errorRegistration);
//            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
//        } else {
        resp.sendRedirect("/verification.jsp");
    }
}


