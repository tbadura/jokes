package badura.tom.jokes.service;

import badura.tom.jokes.model.service.JokeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/testApplicationContext.xml"})
public class JokeServiceTests {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JokeService jokeService;


    /**
     * Individual tests
     */
    @Test
    public void testRandomJoke() {

        log.info("///////////////////////////// BEGIN testRandomJoke()");

        log.info("*** Getting random Chuck Norris joke");
        String joke = jokeService.getJoke();

        log.info("*** The HTML encoded joke: " + joke);
        Assert.assertNotNull(joke);

        log.info("///////////////////////////// END testRandomJoke()");
    }

}
