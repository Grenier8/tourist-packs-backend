package cu.edu.cujae.touristpacks.core.dto;

public class VehicleDto {
	private int idVehicle;
	private String plate;
	private String brand;
	private int noBaggageCapacity;
	private int baggageCapacity;
	private int totalCapacity;
	private int fabricationYear;

	public VehicleDto() {

	}

	public VehicleDto(String plate, String brand, int noBaggageCapacity, int baggageCapacity, int totalCapacity,
			int fabricationYear) {
		this.plate = plate;
		this.brand = brand;
		this.noBaggageCapacity = noBaggageCapacity;
		this.baggageCapacity = baggageCapacity;
		this.totalCapacity = totalCapacity;
		this.fabricationYear = fabricationYear;
	}

	public VehicleDto(int idVehicle, String plate, String brand, int noBaggageCapacity, int baggageCapacity,
			int totalCapacity, int fabricationYear) {
		this.idVehicle = idVehicle;
		this.plate = plate;
		this.brand = brand;
		this.noBaggageCapacity = noBaggageCapacity;
		this.baggageCapacity = baggageCapacity;
		this.totalCapacity = totalCapacity;
		this.fabricationYear = fabricationYear;
	}

	public int getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(int idVehicle) {
		if (idVehicle >= 0)
			this.idVehicle = idVehicle;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String vehiclePlate) {
		this.plate = vehiclePlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getNoBaggageCapacity() {
		return noBaggageCapacity;
	}

	public void setNoBaggageCapacity(int noBaggageCapacity) {
		if (noBaggageCapacity >= 0)
			this.noBaggageCapacity = noBaggageCapacity;
	}

	public int getBaggageCapacity() {
		return baggageCapacity;
	}

	public void setBaggageCapacity(int baggageCapacity) {
		if (baggageCapacity >= 0)
			this.baggageCapacity = baggageCapacity;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		if (totalCapacity >= 0)
			this.totalCapacity = totalCapacity;
	}

	public int getFabricationYear() {
		return fabricationYear;
	}

	public void setFabricationYear(int fabricationYear) {
		if (fabricationYear >= 0)
			this.fabricationYear = fabricationYear;
	}
}
