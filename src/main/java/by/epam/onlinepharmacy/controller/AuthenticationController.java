package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.entity.User;
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
import java.util.Optional;

@WebServlet(urlPatterns = "/auth")
public class AuthenticationController extends HttpServlet {
    private Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginAuthorization = req.getParameter("loginAuthorization");
        String passwordAuthorization = req.getParameter("passwordAuthorization");
        User userAuth = new User.Builder()
                .setLogin(loginAuthorization)
                .setPassword(passwordAuthorization)
                .build();
        Optional<User> user = Optional.empty();
        try {
            user = userService.authenticationUser(userAuth);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception is in method doPost() " , e);
        }
        if (user.isPresent()) {
            req.getSession().setAttribute("authUser", user.get());
            switch (user.get().getRole()) {
                //TODO roles
                case PHARMACIST -> req.getRequestDispatcher("/pharmacistmain.jsp").forward(req, resp);
                case CUSTOMER -> req.getRequestDispatcher("/customermain.jsp").forward(req, resp);
                case ADMIN -> req.getRequestDispatcher("/mainAdmin").forward(req, resp);
            }
        } else {
            String errorAuthentication = "Login or password are incorrect";
            req.setAttribute("errorAuthentication", errorAuthentication);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
