package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.touristpacks.core.dto.VehicleDto;
import cu.edu.cujae.touristpacks.core.service.IVehicleService;

@Service
public class VehicleServiceImpl implements IVehicleService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<VehicleDto> getVehicles() throws SQLException {
		List<VehicleDto> list = new ArrayList<>();

		String function = "{?= call select_all_vehicle()}";

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			connection.setAutoCommit(false);
			CallableStatement statement = connection.prepareCall(function);
			statement.registerOutParameter(1, Types.OTHER);
			statement.execute();

			ResultSet resultSet = (ResultSet) statement.getObject(1);

			while (resultSet.next()) {
				int idVehicle = resultSet.getInt(1);
				String plate = resultSet.getString(2);
				String brand = resultSet.getString(3);
				int noBaggageCapacity = resultSet.getInt(4);
				int baggageCapacity = resultSet.getInt(5);
				int totalCapacity = resultSet.getInt(6);
				int fabricationYear = resultSet.getInt(7);

				VehicleDto dto = new VehicleDto(idVehicle, plate, brand, noBaggageCapacity,
						baggageCapacity, totalCapacity, fabricationYear);
				list.add(dto);
			}
		}
		return list;
	}

	@Override
	public VehicleDto getVehicleById(int vehicleId) throws SQLException {
		VehicleDto vehicle = null;

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM vehicle where id_vehicle = ?");

			pstmt.setInt(1, vehicleId);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String plate = resultSet.getString(2);
				String brand = resultSet.getString(3);
				int noBaggageCapacity = resultSet.getInt(4);
				int baggageCapacity = resultSet.getInt(5);
				int totalCapacity = resultSet.getInt(6);
				int fabricationYear = resultSet.getInt(7);

				vehicle = new VehicleDto(vehicleId, plate, brand, noBaggageCapacity,
						baggageCapacity, totalCapacity, fabricationYear);
			}
		}
		return vehicle;
	}

	   @Override
	   public VehicleDto getVehicleByPlate(String vehiclePlate) throws SQLException {
	       VehicleDto vehicle = null;
    	        
	       try (Connection connection = jdbcTemplate.getDataSource().getConnection()){
	        	PreparedStatement pstmt = connection.prepareStatement(
	        			"SELECT * FROM vehicle WHERE plate = ?");

	            pstmt.setString(1, vehiclePlate);
	            ResultSet resultSet = pstmt.executeQuery();
	            
	            while (resultSet.next()) {
					int idVehicle = resultSet.getInt(1);
					String brand = resultSet.getString(3);
					int noBaggageCapacity = resultSet.getInt(4);
					int baggageCapacity = resultSet.getInt(5);
					int totalCapacity = resultSet.getInt(6);
					int fabricationYear = resultSet.getInt(7);

					vehicle = new VehicleDto(idVehicle, vehiclePlate, brand, noBaggageCapacity,
							baggageCapacity, totalCapacity, fabricationYear);
	            }
	        }

	        return vehicle;
	    }
	
	@Override
	public void createVehicle(VehicleDto vehicle) throws SQLException {
		String function = "{call vehicle_insert(?,?,?,?,?,?)}";

		try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
			statement.setString(1, vehicle.getPlate());
			statement.setString(2, vehicle.getBrand());
			statement.setInt(3, vehicle.getNoBaggageCapacity());
			statement.setInt(4, vehicle.getBaggageCapacity());
			statement.setInt(5, vehicle.getTotalCapacity());
			statement.setInt(6, vehicle.getFabricationYear());
			statement.execute();
		}
	}

	@Override
	public void updateVehicle(VehicleDto vehicle) throws SQLException {
		String function = "{call vehicle_update(?,?,?,?,?,?,?)}";

		try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
			statement.setInt(1, vehicle.getIdVehicle());
			statement.setString(2, vehicle.getPlate());
			statement.setString(3, vehicle.getBrand());
			statement.setInt(4, vehicle.getNoBaggageCapacity());
			statement.setInt(5, vehicle.getBaggageCapacity());
			statement.setInt(6, vehicle.getTotalCapacity());
			statement.setInt(7, vehicle.getFabricationYear());
			statement.execute();
		}
	}

	@Override
	public void deleteVehicle(int id) throws SQLException {
		String function = "{call vehicle_delete(?)}";

		try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
			statement.setInt(1, id);
			statement.execute();
		}
	}

}
