package com.techelevator.dao;

import com.techelevator.model.Neo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;

@Component
public class JdbcNeoDao implements NeoDao{

    JdbcTemplate jdbcTemplate;

    public JdbcNeoDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Neo saveNeo(Neo neo, int userId, LocalDate date) {

        String sql = "INSERT INTO neo (nasa_id, user_id, neo_date, neo_name, " +
                "potentially_hazardous, estimated_max_diameter_km, " +
                        "close_approach_date, miss_distance_km, velocity_km_per_sec) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING db_id";
        Integer dbId = jdbcTemplate.queryForObject(sql, int.class,
                neo.getId(), userId, date, neo.getName(),
                neo.isPotentiallyHazardousAsteroid(),
                neo.getEstimatedDiameterMaxKm(),
                neo.getCloseApproachDate(),
                neo.getMissDistanceKm(),
                neo.getVelocityKmPerSec());

        Neo newNeo = getNeoById(dbId);
        return newNeo;
    }

    @Override
    public Neo getNeoById(int id) {
        String sql = "SELECT db_id, nasa_id, neo_name FROM neo WHERE db_id=?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        Neo neo = new Neo();
        if (result.next()){
            mapRowToNeo(result);
        }
        return neo;
    }
    private Neo mapRowToNeo(SqlRowSet result){
        Neo neo = new Neo();
        neo.setId(result.getString("nasa_id"));
        neo.setName(result.getString("neo_name"));
        neo.setPotentiallyHazardousAsteroid(result.getBoolean("potentially_hazardous"));
        neo.setEstimatedDiameterMaxKm(result.getDouble("estimated_max_diameter_km"));
        neo.setCloseApproachDate(result.getTimestamp("close_approach_date").toLocalDateTime());
        neo.setMissDistanceKm(result.getDouble("miss_distance"));
        neo.setVelocityKmPerSec(result.getDouble("velocity_km_per_sec"));
        return neo;
    }
}
