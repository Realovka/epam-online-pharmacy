package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import by.epam.onlinepharmacy.model.validation.PharmacyValidator;
import by.epam.onlinepharmacy.model.validation.impl.PharmacyValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UpdatingPharmacyNumberCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 5;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        int currentPage = 1;
        String currentPageParam = request.getParameter(RequestParameter.CURRENT_PAGE);
        int currentPageParse = Integer.parseInt(currentPageParam);
        String newNumber = request.getParameter(RequestParameter.UPDATING_PHARMACY_NUMBER);
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        PharmacyValidator pharmacyValidator = PharmacyValidatorImpl.getInstance();
        List<Pharmacy> pharmacies;

        if (!pharmacyValidator.isValidNumber(newNumber)) {
            request.setAttribute(RequestAttribute.UPDATING_PHARMACY_NUMBER_ERROR, BundleKey.PHARMACY_NUMBER_ERROR);
            return new CommandResult(PagePath.UPDATING_PHARMACY_NUMBER, CommandResult.RoutingType.FORWARD);
        }
        try {
            pharmacyService.updateNumber(id, newNumber);
            pharmacies = pharmacyService.findAllPharmacies((currentPageParse - 1) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while update number or find all pharmacies ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
        return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.REDIRECT);
    }
}
