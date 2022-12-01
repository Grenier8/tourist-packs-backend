package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonHotelContractDto;
import cu.edu.cujae.touristpacks.core.service.IHotelContractService;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonHotelContractService;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoomPlanSeasonHotelContractServiceImpl implements IRoomPlanSeasonHotelContractService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    IRoomPlanSeasonService roomPlanSeasonService;

    @Autowired
    IHotelContractService hotelContractService;

    @Override
    public List<RoomPlanSeasonHotelContractDto> getRoomPlanSeasonHotelContracts() throws SQLException {
        List<RoomPlanSeasonHotelContractDto> list = new ArrayList<>();

        String function = "{?= call select_all_room_plan_season_hotel_contract()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idHotelContract = resultSet.getInt(2);
                int idRoomPlanSeason = resultSet.getInt(3);

                HotelContractDto hotelContract = hotelContractService.getHotelContractById(idHotelContract);
                RoomPlanSeasonDto roomPlanSeason = roomPlanSeasonService.getRoomPlanSeasonById(idRoomPlanSeason);

                RoomPlanSeasonHotelContractDto dto = new RoomPlanSeasonHotelContractDto(id, roomPlanSeason,
                        hotelContract);
                list.add(dto);
            }
        }

        return list;
    }

    @Override
    public RoomPlanSeasonHotelContractDto getRoomPlanSeasonHotelContractById(int idRoomPlanSeasonHotelContract)
            throws SQLException {
        RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM room_plan_season_hotel_contract where id_room_plan_season_hotel_contract = ?");

            pstmt.setInt(1, idRoomPlanSeasonHotelContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idHotelContract = resultSet.getInt(2);
                int idRoomPlanSeason = resultSet.getInt(3);

                HotelContractDto hotelContract = hotelContractService.getHotelContractById(idHotelContract);
                RoomPlanSeasonDto roomPlanSeason = roomPlanSeasonService.getRoomPlanSeasonById(idRoomPlanSeason);

                roomPlanSeasonHotelContract = new RoomPlanSeasonHotelContractDto(idRoomPlanSeasonHotelContract,
                        roomPlanSeason, hotelContract);
            }
        }

        return roomPlanSeasonHotelContract;
    }

    @Override
    public void createRoomPlanSeasonHotelContract(RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract)
            throws SQLException {

        String function = "{call room_plan_season_hotel_contract_insert(?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, roomPlanSeasonHotelContract.getHotelContract().getIdHotelContract());
            statement.setInt(2, roomPlanSeasonHotelContract.getRoomPlanSeason().getIdRoomPlanSeason());
            statement.execute();
        }
    }

    @Override
    public void updateRoomPlanSeasonHotelContract(RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract)
            throws SQLException {
        String function = "{call room_plan_season_hotel_contract_update(?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, roomPlanSeasonHotelContract.getIdRoomPlanSeasonHotelContract());
            statement.setInt(2, roomPlanSeasonHotelContract.getHotelContract().getIdHotelContract());
            statement.setInt(3, roomPlanSeasonHotelContract.getRoomPlanSeason().getIdRoomPlanSeason());
            statement.execute();
        }

    }

    @Override
    public void deleteRoomPlanSeasonHotelContract(int idRoomPlanSeasonHotelContract) throws SQLException {
        String function = "{call room_plan_season_hotel_contract_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idRoomPlanSeasonHotelContract);
            statement.execute();
        }

    }

    @Override
    public List<RoomPlanSeasonDto> getRoomPlanSeasonsByIdHotelContract(int idHotelContract) throws SQLException {
        List<RoomPlanSeasonDto> list = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM room_plan_season_hotel_contract where id_hotel_contract = ?");

            pstmt.setInt(1, idHotelContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idRoomPlanSeason = resultSet.getInt(3);

                list.add(roomPlanSeasonService.getRoomPlanSeasonById(idRoomPlanSeason));

            }
        }
        return list;
    }

    @Override
    public void deleteRoomPlanSeasonHotelContractByIds(int idHotelContract, int idRoomPlanSeason) throws SQLException {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM room_plan_season_hotel_contract where id_hotel_contract = ? AND id_room_plan_season = ?");

            pstmt.setInt(1, idHotelContract);
            pstmt.setInt(2, idRoomPlanSeason);

            pstmt.executeUpdate();

        }

    }

    @Override
    public void deleteRoomPlanSeasonHotelContractByIdHotelContract(int idHotelContract) throws SQLException {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM room_plan_season_hotel_contract where id_hotel_contract = ?");

            pstmt.setInt(1, idHotelContract);

            pstmt.executeUpdate();

        }

    }

}
