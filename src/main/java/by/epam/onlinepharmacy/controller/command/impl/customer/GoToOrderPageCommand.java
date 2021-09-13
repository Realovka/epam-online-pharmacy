package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GoToOrderPageCommand implements Command {
    private Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        long id = 0;
        HttpSession session = request.getSession();
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        String pharmacyId = request.getParameter(RequestParameter.PHARMACY_ID);
        if (session.getAttribute(SessionAttribute.PHARMACY_ID) == null && pharmacyId == null) {
            List<Pharmacy> pharmacies;
            try {
                pharmacies = pharmacyService.findAllPharmacies();
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Exception in method execute ", e);
                return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
            }
            session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
            return new CommandResult(PagePath.PHARMACIES_FOR_CUSTOMER, CommandResult.RoutingType.REDIRECT);
        }

        if (pharmacyId != null) {
            id = Long.parseLong(pharmacyId);
            session.setAttribute(SessionAttribute.PHARMACY_ID, id);
        }

        if (session.getAttribute(SessionAttribute.PHARMACY_ID) != null) {
           id = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        }

        Optional<Pharmacy> pharmacy;
        //TODO see GoToAboutProductPageCommand
        try {
            pharmacy = pharmacyService.findPharmacyById(id);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.PHARMACY, pharmacy.get());
        return new CommandResult(PagePath.ORDER, CommandResult.RoutingType.REDIRECT);
    }
}
