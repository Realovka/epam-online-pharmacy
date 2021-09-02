package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class GoToRegistrationPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
      return new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.REDIRECT);
    }
}
