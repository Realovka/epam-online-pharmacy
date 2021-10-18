package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.BundleKey;
import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestAttribute;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.dto.PharmacyDto;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Addition pharmacy command.
 */
public class AdditionPharmacyCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 15;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        String number = request.getParameter(RequestParameter.NUMBER);
        String city = request.getParameter(RequestParameter.CITY);
        String street = request.getParameter(RequestParameter.STREET);
        String house = request.getParameter(RequestParameter.HOUSE);
        String block = request.getParameter(RequestParameter.BLOCK);

        Map<String, String> dataPharmacy = pharmacyService.isFormValid(number, city, street, house, block);


        if (dataPharmacy.get(RequestParameter.NUMBER).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, dataPharmacy);
            request.setAttribute(RequestAttribute.PHARMACY_NUMBER_ERROR, BundleKey.PHARMACY_NUMBER_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        if (dataPharmacy.get(RequestParameter.CITY).isBlank() || dataPharmacy.get(RequestParameter.STREET).isBlank() ||
                dataPharmacy.get(RequestParameter.HOUSE).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, dataPharmacy);
            request.setAttribute(RequestAttribute.PHARMACY_STRING_PARAMETERS_ERROR, BundleKey.PHARMACY_STRING_PARAMETERS_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        if (dataPharmacy.get(RequestParameter.BLOCK).isEmpty()) {
            request.setAttribute(RequestAttribute.MAP_DATA, dataPharmacy);
            request.setAttribute(RequestAttribute.PHARMACY_BLOCK_ERROR, BundleKey.PHARMACY_BLOCK_ERROR);
            return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
        }

        int currentPage;
        List<PharmacyDto> currentPharmacies;
        List<PharmacyDto> previousPharmacies;
        List<PharmacyDto> nextPharmacies = new ArrayList<>();
        try {
            currentPharmacies = pharmacyService.createPharmacy(number, city, street, house, block);
            currentPage = pharmacyService.findCurrentPage();
            previousPharmacies = pharmacyService.findListPharmacies((currentPage - 2) * RECORD_PER_PAGE);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find  current pharmacies ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.CURRENT_PHARMACIES, currentPharmacies);
        session.setAttribute(SessionAttribute.PREVIOUS_PHARMACIES, previousPharmacies);
        session.setAttribute(SessionAttribute.NEXT_PHARMACIES, nextPharmacies);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.REDIRECT);
    }
}
