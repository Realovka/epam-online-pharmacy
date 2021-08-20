package by.epam.onlinepharmacy.controller;

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

@WebServlet(urlPatterns = "/regCustomer")
public class VerificationCustomerController extends HttpServlet {

    private Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        try {
            if (userService.updateCustomerStatus(code)) {
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                req.setAttribute("errorVerification", "Please, check your activation code");
                req.getRequestDispatcher("/regcustomer.jsp").forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception is in method doPost() " , e);
        }

    }
}
