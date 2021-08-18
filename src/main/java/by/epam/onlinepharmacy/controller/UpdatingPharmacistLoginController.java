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

@WebServlet(urlPatterns = "/updatePharmacistLogin")
public class UpdatingPharmacistLoginController extends HttpServlet {
    private Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = (long) req.getSession().getAttribute("id");
        String newLogin = req.getParameter("newLogin");
        List<User> pharmacists = new ArrayList<>();
        try {
            if(userService.updateLogin(id, newLogin)) {
                pharmacists = userService.findAllPharmacists();
                req.getSession().setAttribute("allPharmacists", pharmacists);
                req.getRequestDispatcher("pages/admin/allpharmacists.jsp").forward(req, resp);
            } else {
                req.setAttribute("errorUpdatingLogin", "Such login already exists");
                req.getRequestDispatcher("WEB-INF/pages/admin/updatingpharmacistlogin.jsp").forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method doPost()", e);
        }
    }
}
