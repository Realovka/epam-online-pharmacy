package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerificationCustomerCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        String code = request.getParameter(RequestParameter.CODE);
        try {
            if (userService.updateCustomerStatus(code)) {
              return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
            } else {
                session.setAttribute(SessionAttribute.CODE_VERIFICATION_ERROR, BundleKey.CODE_VERIFICATION_ERROR);
                return new CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
    }
}
