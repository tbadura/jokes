package badura.tom.jokes.dao;

import badura.tom.jokes.model.bean.LogRecord;
import badura.tom.jokes.model.dao.LogRecordDAO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/testApplicationContext.xml"})
public class LogRecordDAOTests {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogRecordDAO logRecordDAO;
    

    /**
     * Individual tests
     */

    @Test
    public void testInsert() {

        log.info("///////////////////////////// BEGIN testInsert()");

        log.info("*** insert log record");
        String ipAddress = "192.168.1.3";
        boolean success = logRecordDAO.insertRecord(new LogRecord(ipAddress));
        Assert.assertTrue(success);

        log.info("*** Insert successful");

        log.info("///////////////////////////// END testInsert()");
    }




}
