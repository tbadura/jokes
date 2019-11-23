package badura.tom.jokes.model.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Implementation of The Internet Chuck Norris Database (http://www.icndb.com/api/)
 */
@Service
public class JokeServiceImpl implements JokeService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Get the random Chuck Norris joke
     *
     * @return The joke text or <code>null</code>.
     */
    @Override
    public String getJoke() {
        String endpoint = "http://api.icndb.com/jokes/random";

        try {
            URL url = new URL(endpoint);
            log.debug(getMessagePrefix() + "Reading JSON from URL: " + url.toString());

            // try with resources opening URL
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {

                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // load JSON result text into object
                JSONObject data = new JSONObject(result.toString());
                log.debug(getMessagePrefix() + "JSON result:\n" + data.toString(4));

                // parse JSON
                String type = data.optString("type");
                if (type != null && type.equals("success")) {
                    return data.getJSONObject("value").getString("joke");
                } else {
                    return null;
                }

            } catch (IOException e) {

                log.error(getMessagePrefix() + "Failed to get joke: ", e);
                return null;
            }

        } catch (MalformedURLException e) {

            log.error(getMessagePrefix() + "Failed to get joke (Bad URL): ", e);
            return null;

        }

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
