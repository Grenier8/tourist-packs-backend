package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.service.IHotelService;
import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.dto.HotelHotelModalityDto;
import cu.edu.cujae.touristpacks.core.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.core.service.IHotelHotelModalityService;
import cu.edu.cujae.touristpacks.core.service.IHotelModalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HotelHotelModalityServiceImpl implements IHotelHotelModalityService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    IHotelService hotelService;

    @Autowired
    IHotelModalityService hotelModalityService;

    @Override
    public List<HotelHotelModalityDto> getHotelHotelModalities() throws SQLException {
        List<HotelHotelModalityDto> list = new ArrayList<>();

        String function = "{?= call select_all_hotel_hotel_modality()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int idHotel = resultSet.getInt(2);
            int idModality = resultSet.getInt(3);

            HotelDto hotel = hotelService.getHotelById(idHotel);
            HotelModalityDto hotelModality = hotelModalityService.getHotelModalityById(idModality);

            HotelHotelModalityDto dto = new HotelHotelModalityDto(id, hotel, hotelModality);
            list.add(dto);
        }

        return list;
    }

    @Override
    public HotelHotelModalityDto getHotelHotelModalityById(int idHotelHotelModality) throws SQLException {
        HotelHotelModalityDto hotelHotelModality = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM hotel_hotel_modality where id_hotel_hotel_modality = ?");

        pstmt.setInt(1, idHotelHotelModality);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idHotel = resultSet.getInt(2);
            int idModality = resultSet.getInt(3);

            HotelDto hotel = hotelService.getHotelById(idHotel);
            HotelModalityDto hotelModality = hotelModalityService.getHotelModalityById(idModality);

            hotelHotelModality = new HotelHotelModalityDto(idHotelHotelModality, hotel, hotelModality);

        }

        return hotelHotelModality;
    }

    @Override
    public void createHotelHotelModality(HotelHotelModalityDto hotelHotelModality) throws SQLException {
        String function = "{call hotel_hotel_modality_insert(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, hotelHotelModality.getHotel().getIdHotel());
        statement.setInt(1, hotelHotelModality.getHotelModality().getIdHotelModality());
        statement.execute();

    }

    @Override
    public void updateHotelHotelModality(HotelHotelModalityDto hotelHotelModality) throws SQLException {
        String function = "{call hotel_hotel_modality_update(?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, hotelHotelModality.getIdHotelHotelModality());
        statement.setInt(2, hotelHotelModality.getHotel().getIdHotel());
        statement.setInt(2, hotelHotelModality.getHotelModality().getIdHotelModality());
        statement.execute();

    }

    @Override
    public void deleteHotelHotelModality(int idHotelHotelModality) throws SQLException {
        String function = "{call hotel_hotel_modality_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idHotelHotelModality);
        statement.execute();

    }

}
