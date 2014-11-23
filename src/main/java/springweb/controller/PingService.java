package springweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class PingService {
    public static Log LOG = LogFactory.getLog(PingService.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(String tag) {
        LOG.info("Inserting Ping tag: " + tag);
        jdbcTemplate.update("INSERT INTO PING(TAG, TS) VALUES(?, ?)", tag, new Date());
    }

    public List<Map<String, Object>> findAllPings() {
        return jdbcTemplate.queryForList("SELECT * FROM PING ORDER BY TS");
    }
}