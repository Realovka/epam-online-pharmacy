package by.epam.onlinepharmacy.controller.listener;

import by.epam.onlinepharmacy.controller.command.SessionAttribute;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("locale", "ru_Ru");
//        se.getSession().setAttribute(SessionAttribute.PREVIOUS_QUERY, DEFAULT_PREVIOUS_QUERY);
    }
}
