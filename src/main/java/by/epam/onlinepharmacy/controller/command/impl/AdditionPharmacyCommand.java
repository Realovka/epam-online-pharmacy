package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import by.epam.onlinepharmacy.validation.PharmacyValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdditionPharmacyCommand implements Command {
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

        if (!PharmacyValidator.isValidNumber(number)) {
            try {
                pharmacies = pharmacyService.findAllPharmacies();
            } catch (ServiceException e) {
                return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
            }
            session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
            request.setAttribute(RequestAttribute.PHARMACY_NUMBER_ERROR, Message.PHARMACY_NUMBER_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        if (!PharmacyValidator.isValidStringParameters(city, street, house)) {
            try {
                pharmacies = pharmacyService.findAllPharmacies();
            } catch (ServiceException e) {
                return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
            }
            session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
            request.setAttribute(RequestAttribute.PHARMACY_STRING_PARAMETERS_ERROR, Message.PHARMACY_STRING_PARAMETERS_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        if(!PharmacyValidator.isValidBlock(block)) {
            try {
                pharmacies = pharmacyService.findAllPharmacies();
            } catch (ServiceException e) {
                return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
            }
            session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
            request.setAttribute(RequestAttribute.PHARMACY_BLOCK_ERROR, Message.PHARMACY_BLOCK_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        try {
            pharmacyService.createPharmacy(number, city, street, house, block);
            pharmacies = pharmacyService.findAllPharmacies();
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
        return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.REDIRECT);
    }
}
