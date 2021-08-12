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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/allPharmacists")
public class AllPharmacistsController extends HttpServlet {

    private Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> pharmacists = new ArrayList<>();
        try {
            pharmacists = userService.findAllPharmacists();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception is in method doGet() " + e.getMessage());
        }
        req.getSession().setAttribute("allPharmacists", pharmacists);
        req.getRequestDispatcher("/verificationpharmacists.jsp").forward(req, resp);
    }
}
