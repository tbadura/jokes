package badura.tom.jokes.model.service;

import badura.tom.jokes.model.bean.LogRecord;

/**
 * Log Record Service interface
 */
public interface LogRecordService {

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
