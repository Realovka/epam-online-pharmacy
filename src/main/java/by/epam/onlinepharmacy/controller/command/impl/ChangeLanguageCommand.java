package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import org.apache.logging.log4j.core.net.UrlConnectionFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
//TODO this class
public class ChangeLanguageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest httpServletRequest) {
        String contextPath = httpServletRequest.getContextPath();
        String uri = httpServletRequest.getRequestURI();
        int startIndex = uri.indexOf(contextPath) + contextPath.length();
        String substring = uri.substring(startIndex);
        String queryParameters = httpServletRequest.getQueryString();
        String queryLine = queryParameters == null ? substring : substring + "?" + queryParameters;
//        URL url = null;
//        try {
//             url = new URL(request.getRequestURL().toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        String locale = (String) httpServletRequest.getSession().getAttribute("locale");
        if (locale.equals("ru_Ru")) {
            httpServletRequest.getSession().setAttribute("locale", "en_En");
        } else {
            httpServletRequest.getSession().setAttribute("locale", "ru_Ru");
        }
        return new CommandResult(queryLine, CommandResult.RoutingType.REDIRECT);
    }
}
