package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.dto.UserViewDto;
import by.epam.onlinepharmacy.entity.Status;
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

@WebServlet(urlPatterns = "/activationPharmacist")
public class ActivationPharmacistController extends HttpServlet {

    private Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<UserViewDto> inactivePharmacists = new ArrayList<>();
        try {
            userService.changePharmacistStatus(id, Status.ACTIVE);
            inactivePharmacists = userService.findInactivePharmacists();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method doGet()" + e.getMessage());
        }
        req.getSession().setAttribute("inactivePharmacists", inactivePharmacists);
        req.getRequestDispatcher("/inactivepharmacists.jsp").forward(req, resp);
    }
}
