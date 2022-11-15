package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.core.service.IHotelChainService;

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
public class HotelChainServiceImpl implements IHotelChainService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HotelChainDto> getHotelChains() throws SQLException {
        List<HotelChainDto> list = new ArrayList<>();

        String function = "{?= call select_all_hotel_chain()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int idHotelChain = resultSet.getInt(1);
            String hotelChainName = resultSet.getString(2);

            HotelChainDto dto = new HotelChainDto(idHotelChain, hotelChainName);
            list.add(dto);
        }

        return list;
    }

    @Override
    public HotelChainDto getHotelChainById(int hotelChainId) throws SQLException {
        HotelChainDto hotelChain = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM hotel_chain where id_chain = ?");

            pstmt.setInt(1, hotelChainId);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String hotelChainName = resultSet.getString(2);

                hotelChain = new HotelChainDto(hotelChainId, hotelChainName);
            }
        }

        return hotelChain;
    }

    @Override
    public void createHotelChain(HotelChainDto hotelChain) throws SQLException {
        String function = "{call hotel_chain_insert(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setString(1, hotelChain.getHotelChainName());
        statement.execute();

    }

    @Override
    public void updateHotelChain(HotelChainDto hotelChain) throws SQLException {
        String function = "{call hotel_chain_update(?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, hotelChain.getIdHotelChain());
        statement.setString(2, hotelChain.getHotelChainName());
        statement.execute();

    }

    @Override
    public void deleteHotelChain(int hotelChainId) throws SQLException {
        String function = "{call hotel_chain_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, hotelChainId);
        statement.execute();

    }

    @Override
    public HotelChainDto getHotelChainByName(String hotelChainName) throws SQLException {
        HotelChainDto hotelChain = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM hotel_chain where chain_name = ?");

        pstmt.setString(1, hotelChainName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int hotelChainId = resultSet.getInt(2);

            hotelChain = new HotelChainDto(hotelChainId, hotelChainName);
        }

        return hotelChain;
    }
}
