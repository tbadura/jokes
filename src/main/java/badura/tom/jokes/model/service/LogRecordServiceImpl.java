package badura.tom.jokes.model.service;

import badura.tom.jokes.model.bean.LogRecord;
import badura.tom.jokes.model.dao.LogRecordDAO;
import org.springframework.stereotype.Service;

/**
 * Log Record Service layer implementation
 */
@Service
public class LogRecordServiceImpl implements LogRecordService {

    private LogRecordDAO logRecordDAO;

    public LogRecordServiceImpl(LogRecordDAO logRecordDAO) {
        this.logRecordDAO = logRecordDAO;
    }
    

    /**
     * Create the ACTIVITY_LOG table.
     *
     * @throws org.springframework.dao.DataAccessException if there's a database exception.
     */
    @Override
    public void createLogTable() {

        logRecordDAO.createLogTable();
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

        return logRecordDAO.insertRecord(record);
	}
}
