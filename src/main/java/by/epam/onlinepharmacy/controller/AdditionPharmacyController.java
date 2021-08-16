package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.dto.PharmacyRegDto;
import by.epam.onlinepharmacy.dto.PharmacyViewDto;
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

@WebServlet(urlPatterns = "/addPharmacy")
public class AdditionPharmacyController extends HttpServlet {
    private Logger logger = LogManager.getLogger();
    private PharmacyService pharmacyService = new PharmacyServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String house = req.getParameter("house");
        String block = req.getParameter("block");
        PharmacyRegDto pharmacy = new PharmacyRegDto(number,city,street, house, block);
        List<PharmacyViewDto> pharmacies = new ArrayList<>();
        try {
            pharmacyService.createPharmacy(pharmacy);
            pharmacies = pharmacyService.findAllPharmacies();
        } catch (ServiceException e) {
          logger.log(Level.ERROR, "Error is in method doPost()");
        }
        req.getSession().setAttribute("allPharmacies", pharmacies);
        req.getRequestDispatcher("pages/admin/allpharmacies.jsp").forward(req, resp);
    }
}
