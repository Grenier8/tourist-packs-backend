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
    private IHotelService hotelService;

    @Autowired
    private IContractService contractService;

    @Override
    public List<HotelContractDto> getHotelContracts() throws SQLException {
        List<HotelContractDto> list = new ArrayList<>();

        String function = "{?= call select_all_hotel_contract()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
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

                HotelDto hotel = hotelService.getHotelById(idHotel);
                ContractDto contract = contractService.getContractById(idContract);

                String contractTitle = contract.getContractTitle();
                LocalDate startDate = contract.getStartDate();
                LocalDate endDate = contract.getEndDate();
                LocalDate conciliationDate = contract.getConciliationDate();
                int year = conciliationDate.getYear();

                HotelContractDto hotelContract = new HotelContractDto(idHotelContract, idContract, contractTitle,
                        startDate,
                        endDate, conciliationDate, contractDescription, hotel);

                list.add(hotelContract);
            }
        }

        return list;
    }

    @Override
    public HotelContractDto getHotelContractById(int idHotelContract) throws SQLException {
        HotelContractDto hotelContract = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM hotel_contract where id_hotel_contract = ?");

            pstmt.setInt(1, idHotelContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String contractDescription = resultSet.getString(2);
                boolean active = resultSet.getBoolean(3);
                int idHotel = resultSet.getInt(4);
                int idContract = resultSet.getInt(5);

                HotelDto hotel = hotelService.getHotelById(idHotel);
                ContractDto contract = contractService.getContractById(idContract);

                String contractTitle = contract.getContractTitle();
                LocalDate startDate = contract.getStartDate();
                LocalDate endDate = contract.getEndDate();
                LocalDate conciliationDate = contract.getConciliationDate();
                int year = conciliationDate.getYear();

                hotelContract = new HotelContractDto(idHotelContract, idContract, contractTitle, startDate, endDate,
                        conciliationDate, contractDescription, hotel);
            }
        }

        return hotelContract;
    }

    @Override
    public HotelContractDto getHotelContractByTitle(String contractTitle) throws SQLException {
        HotelContractDto hotelContract = null;

        ContractDto contract = contractService.getContractByTitle(contractTitle);
        int idContract = contract.getIdContract();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
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

                hotelContract = new HotelContractDto(idHotelContract, idContract, contractTitle, startDate, endDate,
                        conciliationDate, contractDescription, hotel);
            }
        }

        return hotelContract;
    }

    @Override
    public void createHotelContract(HotelContractDto hotelContract) throws SQLException {
        contractService.createContract(hotelContract);
        int idContract = contractService.getContractByTitle(hotelContract.getContractTitle()).getIdContract();

        String function = "{call hotel_contract_insert(?,?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setString(1, hotelContract.getContractDescription());
            statement.setBoolean(2, hotelContract.getActive());
            statement.setInt(3, hotelContract.getHotel().getIdHotel());
            statement.setInt(4, idContract);
            statement.execute();
        }

    }

    @Override
    public void updateHotelContract(HotelContractDto hotelContract) throws SQLException {
        hotelContract.setIdContract(getHotelContractById(hotelContract.getIdHotelContract()).getIdContract());
        contractService.updateContract(hotelContract);

        String function = "{call hotel_contract_update(?,?,?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, hotelContract.getIdHotelContract());
            statement.setString(2, hotelContract.getContractDescription());
            statement.setBoolean(3, hotelContract.getActive());
            statement.setInt(4, hotelContract.getHotel().getIdHotel());
            statement.setInt(5, hotelContract.getIdContract());
            statement.execute();
        }

    }

    @Override
    public void deleteHotelContract(int idHotelContract) throws SQLException {
        int idContract = getHotelContractById(idHotelContract).getIdContract();

        String function = "{call hotel_contract_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idHotelContract);
            statement.execute();
        }

        contractService.deleteContract(idContract);

    }

}
