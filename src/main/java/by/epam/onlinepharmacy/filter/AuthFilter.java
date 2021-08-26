//package by.epam.onlinepharmacy.filter;
//
//import by.epam.onlinepharmacy.entity.User;
//import by.epam.onlinepharmacy.exception.ServiceException;
//import by.epam.onlinepharmacy.model.service.UserService;
//import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
//@WebFilter(value = "/auth")
//public class AuthFilter extends UtilFilter {
//    private Logger logger = LogManager.getLogger();
//    private UserService userService = new UserServiceImpl();
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        String loginAuthorization = req.getParameter("loginAuthorization");
//        String passwordAuthorization = req.getParameter("passwordAuthorization");
//        User userAuth = new User.Builder()
//                .setLogin(loginAuthorization)
//                .setPassword(passwordAuthorization)
//                .build();
//        try {
//            Optional<User> user = userService.authenticationUser(userAuth);
//            user.ifPresentOrElse(userFromDb -> {
//                        req.getSession().setAttribute("authUser", user.get());
//                        try {
//                            switch (user.get().getRole()) {
//                                //TODO roles
//                                case PHARMACIST -> req.getRequestDispatcher("/pharmacist_main.jsp").forward(req, resp);
//                                case CUSTOMER -> req.getRequestDispatcher("/customer_main.jsp").forward(req, resp);
//                                case ADMIN -> req.getRequestDispatcher("/mainAdmin").forward(req, resp);
//                            }
//                        }
//                            catch (ServletException e) {
//                                logger.log(Level.ERROR, "ServletException when user authenticated " + e.getMessage());
//                            } catch (IOException e) {
//                            logger.log(Level.ERROR, "IOException when user authenticated " + e.getMessage());
//                            }
//                        }
//                    ,
//                    () -> {
//                        String errorAuthentication = "Login or password is incorrect";
//                        req.setAttribute("errorAuthentication", errorAuthentication);
//                        try {
//                            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//                        } catch (ServletException e) {
//                            logger.log(Level.ERROR, "ServletException when login or password isn't in DB " + e.getMessage());
//                        } catch (IOException e) {
//                            logger.log(Level.ERROR, "IOException when login or password isn't in DB " + e.getMessage());
//                        }
//                    }
//            );
//        } catch (ServiceException e) {
//            logger.log(Level.ERROR, "Exception is in method doPost() " + e.getMessage());
//        }
//        chain.doFilter(request, response);
//    }
//}
