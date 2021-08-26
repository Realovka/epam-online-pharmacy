package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
      return new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.REDIRECT);
    }
}
