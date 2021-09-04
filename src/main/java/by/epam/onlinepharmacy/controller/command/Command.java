package by.epam.onlinepharmacy.controller.command;

import by.epam.onlinepharmacy.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Command {
    CommandResult execute(HttpServletRequest request) throws ServletException, IOException;
}
