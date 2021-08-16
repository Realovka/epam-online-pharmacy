package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.dto.UserRegDto;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/reg")
public class RegistrationController extends HttpServlet {
    private Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginRegistration = req.getParameter("loginRegistration");
        String passwordRegistration = req.getParameter("passwordRegistration");
        String firstNameRegistration = req.getParameter("firstNameRegistration");
        String lastNameRegistration = req.getParameter("lastNameRegistration");
        String emailRegistration = req.getParameter("emailRegistration");
        String telephoneRegistration = req.getParameter("telephoneRegistration");
        Role userRole = Role.valueOf(String.valueOf(req.getParameter("role")));
        UserRegDto userRegDto = new UserRegDto(loginRegistration, passwordRegistration,
                firstNameRegistration, lastNameRegistration, emailRegistration,
                telephoneRegistration, userRole);
        try {
            if (userService.createUser(userRegDto)) {
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                String errorRegistration = "Such login already exists";
                req.setAttribute("errorRegistration", errorRegistration);
                req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception is in method doPost() " + e.getMessage());
        }
    }

}
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




