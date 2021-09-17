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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdatingPharmacyBlockCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 5;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        int currentPage = 1;
        String currentPageParam = request.getParameter(RequestParameter.CURRENT_PAGE);
        int currentPageParse = Integer.parseInt(currentPageParam);
        String newBlock = request.getParameter(RequestParameter.UPDATING_PHARMACY_BLOCK);
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        PharmacyValidator pharmacyValidator = PharmacyValidatorImpl.getInstance();
        List<Pharmacy> pharmacies;

        if (!pharmacyValidator.isValidNewBlock(newBlock)) {
            request.setAttribute(RequestAttribute.UPDATING_PHARMACY_BLOCK_ERROR, BundleKey.PHARMACY_BLOCK_ERROR);
            return new CommandResult(PagePath.UPDATING_PHARMACY_BLOCK, CommandResult.RoutingType.FORWARD);
        }
        try {
            pharmacyService.updateBlock(id, newBlock);
            pharmacies = pharmacyService.findAllPharmacies((currentPageParse - 1) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while update block or find all pharmacies ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
        return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.REDIRECT);
    }
}
