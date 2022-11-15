package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.core.service.IHotelChainService;
import cu.edu.cujae.touristpacks.core.service.IHotelService;
import cu.edu.cujae.touristpacks.core.service.IProvinceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IHotelChainService hotelChainService;

    @Autowired
    private IProvinceService provinceService;

    @Override
    public List<HotelDto> getHotels() throws SQLException {
        List<HotelDto> list = new ArrayList<>();

        String function = "{?= call select_all_hotel()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int idHotel = resultSet.getInt(1);
                String hotelName = resultSet.getString(2);
                String address = resultSet.getString(3);
                String telephoneNumber = resultSet.getString(4);
                int category = resultSet.getInt(5);
                String fax = resultSet.getString(6);
                String email = resultSet.getString(7);
                double distanceToAirport = resultSet.getDouble(8);
                double distanceToNearestCity = resultSet.getDouble(9);
                int roomsAmount = resultSet.getInt(10);
                int levelsAmount = resultSet.getInt(11);
                String localization = resultSet.getString(12);
                int idChain = resultSet.getInt(13);
                int idProvince = resultSet.getInt(14);

                HotelChainDto hotelChain = hotelChainService.getHotelChainById(idChain);
                ProvinceDto province = provinceService.getProvinceById(idProvince);

                HotelDto dto = new HotelDto(idHotel, hotelName, address, category, telephoneNumber, fax, email,
                        distanceToNearestCity, distanceToAirport, roomsAmount, levelsAmount, localization, hotelChain,
                        province);
                list.add(dto);
            }
        }

        return list;
    }

    @Override
    public HotelDto getHotelById(int idHotel) throws SQLException {
        HotelDto hotel = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM hotel where id_hotel = ?");

        pstmt.setInt(1, idHotel);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            String hotelName = resultSet.getString(2);
            String address = resultSet.getString(3);
            String telephoneNumber = resultSet.getString(4);
            int category = resultSet.getInt(5);
            String fax = resultSet.getString(6);
            String email = resultSet.getString(7);
            double distanceToAirport = resultSet.getDouble(8);
            double distanceToNearestCity = resultSet.getDouble(9);
            int roomsAmount = resultSet.getInt(10);
            int levelsAmount = resultSet.getInt(11);
            String localization = resultSet.getString(12);
            int idChain = resultSet.getInt(13);
            int idProvince = resultSet.getInt(14);

            HotelChainDto hotelChain = hotelChainService.getHotelChainById(idChain);
            ProvinceDto province = provinceService.getProvinceById(idProvince);

            hotel = new HotelDto(idHotel, hotelName, address, category, telephoneNumber, fax, email,
                    distanceToNearestCity, distanceToAirport, roomsAmount, levelsAmount, localization, hotelChain,
                    province);
        }

        return hotel;
    }

    @Override
    public void createHotel(HotelDto hotel) throws SQLException {
        String function = "{call hotel_insert(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setString(1, hotel.getHotelName());
        statement.setString(2, hotel.getAddress());
        statement.setString(3, hotel.getTelephoneNumber());
        statement.setInt(4, hotel.getCategory());
        statement.setString(5, hotel.getFax());
        statement.setString(6, hotel.getEmail());
        statement.setDouble(7, hotel.getDistanceToAirport());
        statement.setDouble(8, hotel.getDistanceToNearestCity());
        statement.setInt(9, hotel.getRoomsAmount());
        statement.setInt(10, hotel.getLevelsAmount());
        statement.setString(11, hotel.getLocalization());
        statement.setInt(12, hotel.getHotelChain().getIdHotelChain());
        statement.setInt(13, hotel.getProvince().getIdProvince());
        statement.execute();

    }

    @Override
    public void updateHotel(HotelDto hotel) throws SQLException {
        String function = "{call hotel_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, hotel.getIdHotel());
        statement.setString(2, hotel.getHotelName());
        statement.setString(3, hotel.getAddress());
        statement.setString(4, hotel.getTelephoneNumber());
        statement.setInt(5, hotel.getCategory());
        statement.setString(6, hotel.getFax());
        statement.setString(7, hotel.getEmail());
        statement.setDouble(8, hotel.getDistanceToAirport());
        statement.setDouble(9, hotel.getDistanceToNearestCity());
        statement.setInt(10, hotel.getRoomsAmount());
        statement.setInt(11, hotel.getLevelsAmount());
        statement.setString(12, hotel.getLocalization());
        statement.setInt(13, hotel.getHotelChain().getIdHotelChain());
        statement.setInt(14, hotel.getProvince().getIdProvince());
        statement.execute();

    }

    @Override
    public void deleteHotel(int idHotelChain) throws SQLException {
        String function = "{call hotel_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idHotelChain);
        statement.execute();

    }

    @Override
    public HotelDto getHotelByName(String hotelName) throws SQLException {
        HotelDto hotel = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM hotel where hotel_name = ?");

        pstmt.setString(1, hotelName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idHotel = resultSet.getInt(1);
            String address = resultSet.getString(3);
            String telephoneNumber = resultSet.getString(4);
            int category = resultSet.getInt(5);
            String fax = resultSet.getString(6);
            String email = resultSet.getString(7);
            double distanceToAirport = resultSet.getDouble(8);
            double distanceToNearestCity = resultSet.getDouble(9);
            int roomsAmount = resultSet.getInt(10);
            int levelsAmount = resultSet.getInt(11);
            String localization = resultSet.getString(12);
            int idChain = resultSet.getInt(13);
            int idProvince = resultSet.getInt(14);

            HotelChainDto hotelChain = hotelChainService.getHotelChainById(idChain);
            ProvinceDto province = provinceService.getProvinceById(idProvince);

            hotel = new HotelDto(idHotel, hotelName, address, category, telephoneNumber, fax, email,
                    distanceToNearestCity, distanceToAirport, roomsAmount, levelsAmount, localization, hotelChain,
                    province);
        }

        return hotel;
    }
}
