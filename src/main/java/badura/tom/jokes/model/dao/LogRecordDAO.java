package badura.tom.jokes.model.dao;

import badura.tom.jokes.model.bean.LogRecord;

/**
 * Data Access Object for {@link badura.tom.jokes.model.bean.LogRecord}.
 */
public interface LogRecordDAO {

    /**
     * Create the ACTIVITYLOG table.
     *
     * @throws org.springframework.dao.DataAccessException if there's a database exception.
     */
    public void createLogTable();

    /**
     * Inserts the log record.
     *
     * @param record {@link LogRecord} to be inserted.
     * @return true if record inserted successfully, false if not
     * @throws org.springframework.dao.DataAccessException if there's a database exception.
     * @throws IllegalArgumentException if there's a missing {@link LogRecord} object or object attributes.
     */
    public boolean insertRecord(LogRecord record);
    
}
