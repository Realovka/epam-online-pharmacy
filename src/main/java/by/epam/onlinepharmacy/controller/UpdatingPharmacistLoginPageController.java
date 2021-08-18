package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateLogin")
public class UpdatingPharmacistLoginPageController extends HttpServlet {
    private Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.getSession().setAttribute("id", Long.parseLong(id));
        req.getRequestDispatcher("WEB-INF/pages/admin/updatingpharmacistlogin.jsp").forward(req, resp);
    }
}
