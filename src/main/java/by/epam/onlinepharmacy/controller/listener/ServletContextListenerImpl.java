package by.epam.onlinepharmacy.controller.listener;

import by.epam.onlinepharmacy.model.connection.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;

/**
 * The type Servlet context listener.
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private Timer timer = new Timer();
    private static final long DELAY_MILLISECOND = 1000L;
    private static final long PERIOD_MILLISECOND_PER_DAY = 86400000L;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
        DeleteOrderTimerTask timerTask = new DeleteOrderTimerTask();
        timer.scheduleAtFixedRate(timerTask, DELAY_MILLISECOND, PERIOD_MILLISECOND_PER_DAY);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      ConnectionPool.getInstance().destroyPool();
    }
}
