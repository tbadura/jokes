package badura.tom.jokes.model.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

/**
 * Implementation of the chucknorris.io JSON API
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
        String endpoint = "https://api.chucknorris.io/jokes/random";

        // Set headers to act like a browser/client the API trusts
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("User-Agent", "Mozilla/5.0"); // Important for many public APIs

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Use exchange to send the headers along with the GET request
            ResponseEntity<String> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.GET,
                    entity,
                    String.class);

            String jsonResponse = response.getBody();

            if (jsonResponse != null && jsonResponse.trim().startsWith("{")) {
                JSONObject data = new JSONObject(jsonResponse);
                return data.getString("value");
            } else {
                log.error(getMessagePrefix() + "Invalid JSON received: " + jsonResponse);
                return null;
            }

        } catch (Exception e) {
            log.error(getMessagePrefix() + "Request failed: " + e.getMessage());
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
