package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.core.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.core.service.IHotelService;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonService;
import cu.edu.cujae.touristpacks.core.service.ITouristPackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TouristPackServiceImpl implements ITouristPackService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    IHotelService hotelService;

    @Autowired
    IRoomPlanSeasonService roomPlanSeasonService;

    @Override
    public List<TouristPackDto> getTouristPacks() throws SQLException {
        List<TouristPackDto> list = new ArrayList<>();

        String function = "{?= call select_all_turist_pack()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int idPack = resultSet.getInt(1);
            String promotionalName = resultSet.getString(2);
            int daysAmount = resultSet.getInt(3);
            int nightsAmount = resultSet.getInt(4);
            int paxAmount = resultSet.getInt(5);
            double totalCost = resultSet.getDouble(6);
            double totalPrice = resultSet.getDouble(7);
            double hotelAirportPrice = resultSet.getDouble(8);
            int idHotel = resultSet.getInt(9);
            int idRoomPlanSeason = resultSet.getInt(10);

            HotelDto hotel = hotelService.getHotelById(idHotel);
            RoomPlanSeasonDto roomPlanSeason = roomPlanSeasonService.getRoomPlanSeasonById(idRoomPlanSeason);

            TouristPackDto touristPack = new TouristPackDto(idPack, promotionalName, nightsAmount, paxAmount,
                    hotelAirportPrice, hotel, roomPlanSeason);
            list.add(touristPack);
        }

        return list;
    }

    @Override
    public TouristPackDto getTouristPackById(int idTouristPack) throws SQLException {
        TouristPackDto touristPack = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM turist_pack where id_pack = ?");

        pstmt.setInt(1, idTouristPack);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            String promotionalName = resultSet.getString(2);
            int daysAmount = resultSet.getInt(3);
            int nightsAmount = resultSet.getInt(4);
            int paxAmount = resultSet.getInt(5);
            double totalCost = resultSet.getDouble(6);
            double totalPrice = resultSet.getDouble(7);
            double hotelAirportPrice = resultSet.getDouble(8);
            int idHotel = resultSet.getInt(9);
            int idRoomPlanSeason = resultSet.getInt(10);

            HotelDto hotel = hotelService.getHotelById(idHotel);
            RoomPlanSeasonDto roomPlanSeason = roomPlanSeasonService.getRoomPlanSeasonById(idRoomPlanSeason);

            touristPack = new TouristPackDto(idTouristPack, promotionalName, nightsAmount, paxAmount,
                    hotelAirportPrice, hotel, roomPlanSeason);
        }

        return touristPack;
    }

    @Override
    public TouristPackDto getTouristPackByName(String promotionalName) throws SQLException {
        TouristPackDto touristPack = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM turist_pack where promotional_name = ?");

        pstmt.setString(1, promotionalName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idTouristPack = resultSet.getInt(1);
            int daysAmount = resultSet.getInt(3);
            int nightsAmount = resultSet.getInt(4);
            int paxAmount = resultSet.getInt(5);
            double totalCost = resultSet.getDouble(6);
            double totalPrice = resultSet.getDouble(7);
            double hotelAirportPrice = resultSet.getDouble(8);
            int idHotel = resultSet.getInt(9);
            int idRoomPlanSeason = resultSet.getInt(10);

            HotelDto hotel = hotelService.getHotelById(idHotel);
            RoomPlanSeasonDto roomPlanSeason = roomPlanSeasonService.getRoomPlanSeasonById(idRoomPlanSeason);

            touristPack = new TouristPackDto(idTouristPack, promotionalName, nightsAmount, paxAmount,
                    hotelAirportPrice, hotel, roomPlanSeason);
        }

        return touristPack;
    }

    @Override
    public void createTouristPack(TouristPackDto touristPack) throws SQLException {
        String function = "{call turist_pack_insert(?,?,?,?,?,?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setString(1, touristPack.getPromotionalName());
        statement.setInt(2, touristPack.getDaysAmount());
        statement.setInt(3, touristPack.getNightsAmount());
        statement.setInt(4, touristPack.getPaxAmount());
        statement.setDouble(5, touristPack.getHotelPrice());
        statement.setDouble(6, touristPack.getTotalPrice());
        statement.setDouble(7, touristPack.getHotelAirportPrice());
        statement.setInt(8, touristPack.getHotel().getIdHotel());
        statement.setInt(9, touristPack.getRoomPlanSeason().getIdRoomPlanSeason());
        statement.execute();

    }

    @Override
    public void updateTouristPack(TouristPackDto touristPack) throws SQLException {
        String function = "{call turist_pack_update(?,?,?,?,?,?,?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, touristPack.getIdTouristPack());
        statement.setString(2, touristPack.getPromotionalName());
        statement.setInt(3, touristPack.getDaysAmount());
        statement.setInt(4, touristPack.getNightsAmount());
        statement.setInt(5, touristPack.getPaxAmount());
        statement.setDouble(6, touristPack.getHotelPrice());
        statement.setDouble(7, touristPack.getTotalPrice());
        statement.setDouble(8, touristPack.getHotelAirportPrice());
        statement.setInt(9, touristPack.getHotel().getIdHotel());
        statement.setInt(10, touristPack.getRoomPlanSeason().getIdRoomPlanSeason());
        statement.execute();

    }

    @Override
    public void deleteTouristPack(int idTouristPack) throws SQLException {
        String function = "{call turist_pack_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idTouristPack);
        statement.execute();

    }

}
