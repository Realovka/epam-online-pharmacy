package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
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

@WebServlet(urlPatterns = "/allPharmacies")
public class AllPharmaciesController extends HttpServlet {
    private Logger logger = LogManager.getLogger();
    private PharmacyService pharmacyService = new PharmacyServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pharmacy> pharmacies = new ArrayList<>();
        try {
            pharmacies = pharmacyService.findAllPharmacies();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception is in method doGet() ", e);
        }
        req.getSession().setAttribute("allPharmacies", pharmacies);
        req.getRequestDispatcher("WEB-INF/pages/admin/allpharmacies.jsp").forward(req, resp);
    }
}
