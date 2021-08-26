package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerificationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        CommandResult commandResult = null;
        HttpSession session = request.getSession();
        String code = request.getParameter(RequestParameter.CODE);
        try {
            UserService userService = UserServiceImpl.getInstance();
            if (userService.updateCustomerStatus(code)) {
              commandResult = new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
            } else {
                session.setAttribute(SessionAttribute.CODE_VERIFICATION_ERROR,Message.CODE_VERIFICATION_ERROR);
                commandResult = new CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception is in VerificationCommand " , e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }
}
