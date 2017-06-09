package badura.tom.jokes.model.dao;

import badura.tom.jokes.model.bean.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.nativejdbc.SimpleNativeJdbcExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Implementation of {@link LogRecordDAO}
 */
@Repository
public class SQLiteLogRecordDAO extends JdbcDaoSupport implements LogRecordDAO {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private DataSource dataSource;

    @Autowired
    public SQLiteLogRecordDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    /*
     *  Custom DAO initialization
     */
    @Override
    protected void initDao() throws Exception {
        super.initDao();
        getJdbcTemplate().setNativeJdbcExtractor(new SimpleNativeJdbcExtractor());      // Allows access to the underlying native Connection
    }

    /**
     * Create the ACTIVITYLOG table.
     *
     * @throws org.springframework.dao.DataAccessException if there's a database exception.
     */
    @Override
    public void createLogTable() {

        final String CREATE_SQL = "create table if not exists ACTIVITYLOG (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " activity_date TIMESTAMP NOT NULL," +
                " ip_address text NOT NULL" +
                ")";


        log.debug(getMessagePrefix() + "Executing SQL: " + CREATE_SQL);


        getJdbcTemplate().update(CREATE_SQL);

    }


    /**
     * Inserts the log record.
     *
     * @param record {@link LogRecord} to be inserted.
     * @return true if record inserted successfully, false if not
     * @throws org.springframework.dao.DataAccessException if there's a database exception.
     * @throws IllegalArgumentException if there's a missing {@link LogRecord} object or object attributes.
     */
    @Override
    public boolean insertRecord(LogRecord record) {
        Assert.notNull(record, "Cannot insert a null log record.");
        Assert.hasText(record.getIpAddress(), "Missing IP Address in log record.");

        final String INSERT_SQL = "insert into ACTIVITYLOG (activity_date, ip_address)" +
                " values(CURRENT_TIMESTAMP, ?)";

	  	int recordsInserted = getJdbcTemplate().update(INSERT_SQL,
				record.getIpAddress());

        log.debug(getMessagePrefix() + "Number of log records inserted: " + recordsInserted);
        return (recordsInserted == 1);
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