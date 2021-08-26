package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

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
                session.setAttribute(SessionAttribute.LOGIN_ERROR, Message.LOGIN_ERROR);
                commandResult = new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception is in LoginCommand ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }
}


