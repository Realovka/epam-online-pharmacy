package by.epam.onlinepharmacy.controller.command;

import by.epam.onlinepharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandResult execute(HttpServletRequest request) throws ServiceException;
}
