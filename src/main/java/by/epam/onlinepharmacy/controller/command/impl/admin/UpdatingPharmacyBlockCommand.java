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
import by.epam.onlinepharmacy.model.validation.PharmacyValidator;
import by.epam.onlinepharmacy.model.validation.impl.PharmacyValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UpdatingPharmacyBlockCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 15;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int currentPage = (int) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        String newBlock = request.getParameter(RequestParameter.UPDATING_PHARMACY_BLOCK);
        long id = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        PharmacyValidator pharmacyValidator = PharmacyValidatorImpl.getInstance();
        List<PharmacyDto> currentPharmacies;
        List<PharmacyDto> nextPharmacies;
        List<PharmacyDto> previousPharmacies = new ArrayList<>();

        if (!pharmacyValidator.isValidNewBlock(newBlock)) {
            request.setAttribute(RequestAttribute.UPDATING_PHARMACY_BLOCK_ERROR, BundleKey.PHARMACY_BLOCK_ERROR);
            return new CommandResult(PagePath.UPDATING_PHARMACY_BLOCK, CommandResult.RoutingType.FORWARD);
        }
        try {
            pharmacyService.updateBlock(id, newBlock);
            if (currentPage != 1) {
                previousPharmacies = pharmacyService.findListPharmacies((currentPage - 2) * RECORD_PER_PAGE);
            }
            currentPharmacies = pharmacyService.findListPharmacies((currentPage - 1) * RECORD_PER_PAGE);
            nextPharmacies = pharmacyService.findListPharmacies((currentPage) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while update block or find all current pharmacies ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.PREVIOUS_PHARMACIES, previousPharmacies);
        session.setAttribute(SessionAttribute.NEXT_PHARMACIES, nextPharmacies);
        session.setAttribute(SessionAttribute.CURRENT_PHARMACIES, currentPharmacies);
        return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.REDIRECT);
    }
}
