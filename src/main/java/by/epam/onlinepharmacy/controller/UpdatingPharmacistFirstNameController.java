package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.dto.UserViewDto;
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

@WebServlet(urlPatterns = "/updatePharmacistFirstName")
public class UpdatingPharmacistFirstNameController extends HttpServlet {
    private Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = (long) req.getSession().getAttribute("id");
        String newFirstName = req.getParameter("newFirstName");
        List<UserViewDto> pharmacists = new ArrayList<>();
        try {
            userService.updateFirstName(id, newFirstName);
            pharmacists = userService.findAllPharmacists();
            req.getSession().setAttribute("allPharmacists", pharmacists);
            req.getRequestDispatcher("admin/allpharmacists.jsp").forward(req, resp);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method doPost()" + e.getMessage());
        }
    }
}
