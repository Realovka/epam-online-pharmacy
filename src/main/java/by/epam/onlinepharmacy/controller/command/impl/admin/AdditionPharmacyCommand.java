package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class AdditionPharmacyCommand implements Command {
    private Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        String number = request.getParameter(RequestParameter.NUMBER);
        String city = request.getParameter(RequestParameter.CITY);
        String street = request.getParameter(RequestParameter.STREET);
        String house = request.getParameter(RequestParameter.HOUSE);
        String block = request.getParameter(RequestParameter.BLOCK);
        List<Pharmacy> pharmacies;

        Map<String, String> dataPharmacy = pharmacyService.isFormValid(number, city, street, house, block);


        if (dataPharmacy.get(RequestParameter.NUMBER).isBlank()) {
            try {
                pharmacies = pharmacyService.findAllPharmacies();
            } catch (ServiceException e) {
                return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
            }
            session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
            request.setAttribute(RequestAttribute.MAP_DATA, dataPharmacy);
            request.setAttribute(RequestAttribute.PHARMACY_NUMBER_ERROR, BundleKey.PHARMACY_NUMBER_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        if (dataPharmacy.get(RequestParameter.CITY).isBlank() || dataPharmacy.get(RequestParameter.STREET).isBlank() ||
                dataPharmacy.get(RequestParameter.HOUSE).isBlank()) {
            try {
                pharmacies = pharmacyService.findAllPharmacies();
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "ServiceException in method execute ", e);
                return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
            }
            session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
            request.setAttribute(RequestAttribute.MAP_DATA, dataPharmacy);
            request.setAttribute(RequestAttribute.PHARMACY_STRING_PARAMETERS_ERROR, BundleKey.PHARMACY_STRING_PARAMETERS_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        if (dataPharmacy.get(RequestParameter.BLOCK).isBlank()) {
            try {
                pharmacies = pharmacyService.findAllPharmacies();
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "ServiceException in method execute ", e);
                return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
            }
            session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
            request.setAttribute(RequestAttribute.MAP_DATA, dataPharmacy);
            request.setAttribute(RequestAttribute.PHARMACY_BLOCK_ERROR, BundleKey.PHARMACY_BLOCK_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        try {
            pharmacyService.createPharmacy(number, city, street, house, block);
            pharmacies = pharmacyService.findAllPharmacies();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
        return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.REDIRECT);
    }
}
