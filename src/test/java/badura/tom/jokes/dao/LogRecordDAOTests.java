package badura.tom.jokes.dao;

import badura.tom.jokes.model.bean.LogRecord;
import badura.tom.jokes.model.dao.LogRecordDAO;
import org.junit.Assert;
import org.junit.Before;
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
     * Sets up the test fixture.
     * (Called before every test case method.)
     */

    @Before
    public void setUp() {

        logRecordDAO.createLogTable();
        log.info("///////////////////////////// setUp completed");
    }



    /**
     * Individual tests
     */

    @Test
    public void testInsertWithJoke() {

        log.info("///////////////////////////// BEGIN testInsertWithJoke()");

        String ipAddress = "192.168.1.3";
        String joke = "What goes peck, peck ... BANG? A chicken in a minefield.";
        LogRecord logRecord = new LogRecord(ipAddress, joke);

        int totalRecordsBeforeInsert = logRecordDAO.getTotalRecordCount();
        log.info("Total number of log records (Before Insert): " + totalRecordsBeforeInsert);

        log.info("*** insert log record " + logRecord);
        boolean success = logRecordDAO.insertRecord(logRecord);

        int totalRecordsAfterInsert = logRecordDAO.getTotalRecordCount();
        log.info("Total number of log records (After Insert): " + totalRecordsAfterInsert);

        Assert.assertTrue(success && insertVerified(totalRecordsBeforeInsert, totalRecordsAfterInsert) );

        log.info("*** Insert successful");

        log.info("///////////////////////////// END testInsertWithJoke()");
    }

    @Test
    public void testInsertWithoutJoke() {

        log.info("///////////////////////////// BEGIN testInsertWithoutJoke()");

        String ipAddress = "192.168.1.4";
        LogRecord logRecord = new LogRecord(ipAddress, null);

        int totalRecordsBeforeInsert = logRecordDAO.getTotalRecordCount();
        log.info("Total number of log records (Before Insert): " + totalRecordsBeforeInsert);

        log.info("*** insert log record " + logRecord);
        boolean success = logRecordDAO.insertRecord(logRecord);

        int totalRecordsAfterInsert = logRecordDAO.getTotalRecordCount();
        log.info("Total number of log records (After Insert): " + totalRecordsAfterInsert);

        Assert.assertTrue(success && insertVerified(totalRecordsBeforeInsert, totalRecordsAfterInsert) );

        log.info("*** Insert successful");

        log.info("///////////////////////////// END testInsertWithoutJoke()");
    }

    private boolean insertVerified (int recordsBeforeInsert, int recordsAfterInsert) {
        final int expectedRecordsAfterInsert = recordsBeforeInsert + 1;

        return (recordsAfterInsert == expectedRecordsAfterInsert);
    }

}
