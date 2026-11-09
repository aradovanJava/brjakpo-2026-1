package hr.algebra.brjakpo._2026.project.example.repository;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcConcertRepository implements ConcertRepository{

    private JdbcTemplate jdbcTemplate;
    private static final String SELECT_CONCERTS = "SELECT * FROM CONCERT";
    private SimpleJdbcInsert simpleJdbcInsert;

    public JdbcConcertRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("CONCERT")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public List<Concert> findAll() {
        return jdbcTemplate.query(SELECT_CONCERTS, new ConcertRowMapper());
    }

    @Override
    public List<Concert> findByExample(Concert concertFilterCriteria) {
        String sql = SELECT_CONCERTS + " WHERE NAME LIKE ?";
        return jdbcTemplate.query(sql,
                new String[] { "%" + concertFilterCriteria.getName() + "%" },
                new ConcertRowMapper());
    }

    @Override
    public Optional<Concert> findById(Long id) {
        String sql = SELECT_CONCERTS + " WHERE ID = ?";
        Concert concert = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ConcertRowMapper());
        return Optional.ofNullable(concert);
    }

    @Override
    public void save(Concert concert) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("NAME", concert.getName());
        parameters.put("VENUE", concert.getVenue());
        parameters.put("START_DATE_TIME", concert.getStartDateTime());
        simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public void delete(Concert concert) {
        jdbcTemplate.update("DELETE FROM CONCERT WHERE ID = ?", concert.getId());
    }

    private class ConcertRowMapper implements RowMapper<Concert> {
        @Override
        public Concert mapRow(ResultSet rs, int rowNum) throws SQLException {
            Concert concert = new Concert();

            concert.setId(rs.getLong("ID"));
            concert.setName(rs.getString("NAME"));

            LocalDateTime dateTime = Instant.ofEpochMilli(rs.getTimestamp("START_DATE_TIME").getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            concert.setStartDateTime(dateTime);
            concert.setVenue(rs.getString("VENUE"));

            return concert;
        }
    }
}
