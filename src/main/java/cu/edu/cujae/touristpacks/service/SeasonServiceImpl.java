package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.SeasonDto;
import cu.edu.cujae.touristpacks.core.service.ISeasonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonServiceImpl implements ISeasonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SeasonDto> getSeasons() throws SQLException {
        List<SeasonDto> list = new ArrayList<>();

        String function = "{?= call select_all_season()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int idSeason = resultSet.getInt(1);
            String seasonName = resultSet.getString(2);
            LocalDate startSeason = resultSet.getDate(3).toLocalDate();
            LocalDate endSeason = resultSet.getDate(4).toLocalDate();
            String seasonDescription = resultSet.getString(5);

            SeasonDto dto = new SeasonDto(idSeason, seasonName, startSeason, endSeason, seasonDescription);
            list.add(dto);
        }

        return list;
    }

    @Override
    public SeasonDto getSeasonById(int idSeason) throws SQLException {
        SeasonDto season = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM season where id_season = ?");

        pstmt.setInt(1, idSeason);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            String seasonName = resultSet.getString(2);
            LocalDate startSeason = resultSet.getDate(3).toLocalDate();
            LocalDate endSeason = resultSet.getDate(4).toLocalDate();
            String seasonDescription = resultSet.getString(5);

            season = new SeasonDto(idSeason, seasonName, startSeason, endSeason, seasonDescription);
        }

        return season;
    }

    @Override
    public SeasonDto getSeasonByName(String seasonName) throws SQLException {
        SeasonDto season = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM season where season_name = ?");

        pstmt.setString(1, seasonName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idSeason = resultSet.getInt(1);
            LocalDate startSeason = resultSet.getDate(3).toLocalDate();
            LocalDate endSeason = resultSet.getDate(4).toLocalDate();
            String seasonDescription = resultSet.getString(5);

            season = new SeasonDto(idSeason, seasonName, startSeason, endSeason, seasonDescription);
        }

        return season;
    }

    @Override
    public void createSeason(SeasonDto season) throws SQLException {
        String function = "{call season_insert(?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setString(1, season.getSeasonName());
        statement.setDate(2, Date.valueOf(season.getStartSeason()));
        statement.setDate(3, Date.valueOf(season.getEndSeason()));
        statement.setString(4, season.getSeasonDescription());

        statement.execute();
    }

    @Override
    public void updateSeason(SeasonDto season) throws SQLException {
        String function = "{call season_update(?,?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, season.getIdSeason());
        statement.setString(2, season.getSeasonName());
        statement.setDate(3, Date.valueOf(season.getStartSeason()));
        statement.setDate(4, Date.valueOf(season.getEndSeason()));
        statement.setString(5, season.getSeasonDescription());
        statement.execute();
    }

    @Override
    public void deleteSeason(int idSeason) throws SQLException {
        String function = "{call season_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idSeason);
        statement.execute();
    }

}
