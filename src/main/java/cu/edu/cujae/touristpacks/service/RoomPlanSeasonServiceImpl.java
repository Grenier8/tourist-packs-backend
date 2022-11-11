package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.AlimentaryPlanDto;
import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.core.dto.RoomTypeDto;
import cu.edu.cujae.touristpacks.core.dto.SeasonDto;
import cu.edu.cujae.touristpacks.core.service.IAlimentaryPlanService;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonService;
import cu.edu.cujae.touristpacks.core.service.IRoomTypeService;
import cu.edu.cujae.touristpacks.core.service.ISeasonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoomPlanSeasonServiceImpl implements IRoomPlanSeasonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IRoomTypeService roomTypeService;

    @Autowired
    private IAlimentaryPlanService alimentaryPlanService;

    @Autowired
    private ISeasonService seasonService;

    @Override
    public List<RoomPlanSeasonDto> getRoomPlanSeasons() throws SQLException {
        List<RoomPlanSeasonDto> list = new ArrayList<>();

        String function = "{?= call select_all_room_plan_season()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int idRoomPlanSeason = resultSet.getInt(1);
            int idRoomType = resultSet.getInt(2);
            int idSeason = resultSet.getInt(3);
            int idAlimentaryPlan = resultSet.getInt(4);
            double roomReservedPrice = resultSet.getDouble(5);
            String roomPlanSeasonName = resultSet.getString(6);

            RoomTypeDto roomType = roomTypeService.getRoomTypeById(idRoomType);
            SeasonDto season = seasonService.getSeasonById(idSeason);
            AlimentaryPlanDto alimentaryPlan = alimentaryPlanService.getAlimentaryPlanById(idAlimentaryPlan);

            RoomPlanSeasonDto roomPlanSeason = new RoomPlanSeasonDto(idRoomPlanSeason, roomPlanSeasonName,
                    roomReservedPrice, roomType,
                    alimentaryPlan, season);
            list.add(roomPlanSeason);
        }

        return list;
    }

    @Override
    public RoomPlanSeasonDto getRoomPlanSeasonById(int idRoomPlanSeason) throws SQLException {
        RoomPlanSeasonDto roomPlanSeason = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM room_plan_season where id_room_plan_season = ?");

        pstmt.setInt(1, idRoomPlanSeason);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idRoomType = resultSet.getInt(2);
            int idSeason = resultSet.getInt(3);
            int idAlimentaryPlan = resultSet.getInt(4);
            double roomReservedPrice = resultSet.getDouble(5);
            String roomPlanSeasonName = resultSet.getString(6);

            RoomTypeDto roomType = roomTypeService.getRoomTypeById(idRoomType);
            SeasonDto season = seasonService.getSeasonById(idSeason);
            AlimentaryPlanDto alimentaryPlan = alimentaryPlanService.getAlimentaryPlanById(idAlimentaryPlan);

            roomPlanSeason = new RoomPlanSeasonDto(idRoomPlanSeason, roomPlanSeasonName,
                    roomReservedPrice, roomType,
                    alimentaryPlan, season);
        }

        return roomPlanSeason;
    }

    @Override
    public RoomPlanSeasonDto getRoomPlanSeasonByName(String roomPlanSeasonName) throws SQLException {
        RoomPlanSeasonDto roomPlanSeason = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM room_plan_season where room_plan_season_name = ?");

        pstmt.setString(1, roomPlanSeasonName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idRoomPlanSeason = resultSet.getInt(1);
            int idRoomType = resultSet.getInt(2);
            int idSeason = resultSet.getInt(3);
            int idAlimentaryPlan = resultSet.getInt(4);
            double roomReservedPrice = resultSet.getDouble(5);

            RoomTypeDto roomType = roomTypeService.getRoomTypeById(idRoomType);
            SeasonDto season = seasonService.getSeasonById(idSeason);
            AlimentaryPlanDto alimentaryPlan = alimentaryPlanService.getAlimentaryPlanById(idAlimentaryPlan);

            roomPlanSeason = new RoomPlanSeasonDto(idRoomPlanSeason, roomPlanSeasonName,
                    roomReservedPrice, roomType,
                    alimentaryPlan, season);
        }

        return roomPlanSeason;
    }

    @Override
    public void createRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) throws SQLException {
        String function = "{call room_plan_season_insert(?,?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, roomPlanSeason.getRoomType().getIdRoomType());
        statement.setInt(2, roomPlanSeason.getSeason().getIdSeason());
        statement.setInt(3, roomPlanSeason.getAlimentaryPlan().getIdAlimentaryPlan());
        statement.setDouble(4, roomPlanSeason.getRoomReservedPrice());
        statement.setString(5, roomPlanSeason.getRoomPlanSeasonName());
        statement.execute();

    }

    @Override
    public void updateRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) throws SQLException {
        String function = "{call room_plan_season_update(?,?,?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, roomPlanSeason.getIdRoomPlanSeason());
        statement.setInt(2, roomPlanSeason.getRoomType().getIdRoomType());
        statement.setInt(3, roomPlanSeason.getSeason().getIdSeason());
        statement.setInt(4, roomPlanSeason.getAlimentaryPlan().getIdAlimentaryPlan());
        statement.setDouble(5, roomPlanSeason.getRoomReservedPrice());
        statement.setString(6, roomPlanSeason.getRoomPlanSeasonName());
        statement.execute();

    }

    @Override
    public void deleteRoomPlanSeason(int idRoomPlanSeason) throws SQLException {
        String function = "{call room_plan_season_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idRoomPlanSeason);
        statement.execute();

    }

}
