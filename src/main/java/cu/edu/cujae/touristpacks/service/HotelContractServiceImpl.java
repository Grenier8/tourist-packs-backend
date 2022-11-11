package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ContractDto;
import cu.edu.cujae.touristpacks.core.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.service.IContractService;
import cu.edu.cujae.touristpacks.core.service.IHotelContractService;
import cu.edu.cujae.touristpacks.core.service.IHotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HotelContractServiceImpl implements IHotelContractService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    IHotelService hotelService;

    @Autowired
    IContractService contractService;

    @Override
    public List<HotelContractDto> getHotelContracts() throws SQLException {
        List<HotelContractDto> list = new ArrayList<>();

        String function = "{?= call select_all_hotel_contract()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int idHotelContract = resultSet.getInt(1);
            String contractDescription = resultSet.getString(2);
            boolean active = resultSet.getBoolean(3);
            int idHotel = resultSet.getInt(4);
            int idContract = resultSet.getInt(5);

            ContractDto contract = contractService.getContractById(idContract);

            String contractTitle = contract.getContractTitle();
            LocalDate startDate = contract.getStartDate();
            LocalDate endDate = contract.getEndDate();
            LocalDate conciliationDate = contract.getConciliationDate();
            int year = conciliationDate.getYear();

            HotelContractDto hotelContract = new HotelContractDto(idHotelContract, contractTitle, startDate, endDate,
                    conciliationDate, year, contractDescription, active, idHotel, idContract);

            list.add(hotelContract);
        }

        return list;
    }

    @Override
    public HotelContractDto getHotelContractById(int idHotelContract) throws SQLException {
        HotelContractDto hotelContract = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM hotel_contract where id_hotel_contract = ?");

        pstmt.setInt(1, idHotelContract);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            String contractDescription = resultSet.getString(2);
            boolean active = resultSet.getBoolean(3);
            int idHotel = resultSet.getInt(4);
            int idContract = resultSet.getInt(5);

            ContractDto contract = contractService.getContractById(idContract);

            String contractTitle = contract.getContractTitle();
            LocalDate startDate = contract.getStartDate();
            LocalDate endDate = contract.getEndDate();
            LocalDate conciliationDate = contract.getConciliationDate();
            int year = conciliationDate.getYear();

            hotelContract = new HotelContractDto(idHotelContract, contractTitle, startDate, endDate,
                    conciliationDate, year, contractDescription, active, idHotel, idContract);
        }

        return hotelContract;
    }

    @Override
    public HotelContractDto getHotelContractByTitle(String contractTitle) throws SQLException {
        HotelContractDto hotelContract = null;

        ContractDto contract = contractService.getContractByTitle(contractTitle);
        int idContract = contract.getIdContract();

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM hotel_contract WHERE id_contract = ?");

        pstmt.setInt(1, idContract);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idHotelContract = resultSet.getInt(1);
            String contractDescription = resultSet.getString(2);
            boolean active = resultSet.getBoolean(3);
            int idHotel = resultSet.getInt(4);

            LocalDate startDate = contract.getStartDate();
            LocalDate endDate = contract.getEndDate();
            LocalDate conciliationDate = contract.getConciliationDate();
            int year = conciliationDate.getYear();

            HotelDto hotel = hotelService.getHotelById(idHotel);

            hotelContract = new HotelContractDto(idHotelContract, contractTitle, startDate, endDate,
                    conciliationDate, contractDescription, hotel, idContract);
        }

        return hotelContract;
    }

    @Override
    public void createHotelContract(HotelContractDto hotelContract) throws SQLException {
        contractService.createContract(hotelContract);
        int idContract = contractService.getContractByTitle(hotelContract.getContractTitle()).getIdContract();

        String function = "{call hotel_contract_insert(?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setString(2, hotelContract.getContractDescription());
        statement.setBoolean(3, hotelContract.getActive());
        statement.setInt(4, hotelContract.getHotel().getIdHotel());
        statement.setInt(5, idContract);
        statement.execute();

    }

    @Override
    public void updateHotelContract(HotelContractDto hotelContract) throws SQLException {
        contractService.updateContract(hotelContract);

        String function = "{call hotel_contract_update(?,?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(2, hotelContract.getIdHotelContract());
        statement.setString(3, hotelContract.getContractDescription());
        statement.setBoolean(4, hotelContract.getActive());
        statement.setInt(5, hotelContract.getHotel().getIdHotel());
        statement.setInt(6, hotelContract.getIdContract());
        statement.execute();

    }

    @Override
    public void deleteHotelContract(int idHotelContract) throws SQLException {
        String function = "{call hotel_contract_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idHotelContract);
        statement.execute();

    }

}
