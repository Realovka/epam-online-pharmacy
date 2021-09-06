package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request)  {
        CommandResult commandResult = null;
        HttpSession session = request.getSession();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<User> user = userService.authenticationUser(login, password);
            if (user.isPresent()) {
                User userAuth = user.get();
                session.setAttribute(SessionAttribute.USER_AUTH, userAuth);
                switch (user.get().getRole()) {
//                //TODO roles
                    case PHARMACIST -> {
                        commandResult = new CommandResult(PagePath.MAIN_PHARMACIST, CommandResult.RoutingType.REDIRECT);
                    }
                    case CUSTOMER -> {
                        commandResult = new CommandResult(PagePath.MAIN_CUSTOMER, CommandResult.RoutingType.REDIRECT);
                    }
                    case ADMIN -> {
                        commandResult = new CommandResult(PagePath.MAIN_ADMIN, CommandResult.RoutingType.REDIRECT);
                    }
                }
            } else {
                request.setAttribute(RequestAttribute.LOGIN_ERROR, BundleKey.LOGIN_ERROR);
                return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }
}


