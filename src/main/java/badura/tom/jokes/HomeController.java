package badura.tom.jokes;

import badura.tom.jokes.model.bean.LogRecord;
import badura.tom.jokes.model.service.JokeService;
import badura.tom.jokes.model.service.LogRecordService;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private JokeService jokeService;
    private LogRecordService logRecordService;

    /**
     * Constructor injection
     * @param jokeService Service for fetching a joke (injected by Spring)
     * @param logRecordService Service for logging a record (injected by Spring)
     */
    @Autowired
    public HomeController(JokeService jokeService, LogRecordService logRecordService) {
        this.jokeService = jokeService;
        this.logRecordService = logRecordService;
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(Locale locale, HttpServletRequest request) {
        log.debug(getMessagePrefix() + "Entering HomeController class. The client locale is {}.", locale);
        ModelAndView modelAndView = new ModelAndView("home");

        // retrieve joke
        String joke = jokeService.getJoke(); // gets joke encoded in HTML formatting

        // log activity in database
        String ipAddress = request.getRemoteAddr();
        String unescapedJoke = StringEscapeUtils.unescapeHtml(joke);
        log.info(getMessagePrefix() + "Your joke is: " + unescapedJoke);
//        logRecordService.insertRecord(new LogRecord(ipAddress, unescapedJoke));

        // pass resulting joke string to view
        modelAndView.addObject("showResult", joke);

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(HttpServletRequest request, Exception ex) {
        log.error(getMessagePrefix() + "Requested URL: " + request.getRequestURL());
        log.error(getMessagePrefix() + "Exception Raised: " + ex);

        return new ModelAndView("error");

    }

    /**
     * Get message prefix with IP address for logging
     *
     * @return message prefix with IP address and host name if {@link org.springframework.web.context.request.RequestAttributes} object
     *         is bound to current thread, empty string if no {@link org.springframework.web.context.request.RequestAttributes}
     *         object is bound to the current thread
     */
    private String getMessagePrefix() {
        try {
            String ipAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
            return "<" + ipAddress + "> ";
        }
        catch (IllegalStateException e){
            return "";
        }
    }

}
