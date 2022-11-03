package cu.edu.cujae.touristpacks.core.dto;

public class TransportServiceDto {

	private int idTransportService;
	private String transportServiceName;
	private VehicleDto vehicle;
	private TransportModalityDto tmodality;
	private double transpServicePrice;

	public TransportServiceDto() {
	}

	public TransportServiceDto(String transportServiceName, VehicleDto vehicle,
			TransportModalityDto tmodality, double transpServicePrice) {
		this.transportServiceName = transportServiceName;
		this.vehicle = vehicle;
		this.tmodality = tmodality;
		this.transpServicePrice = transpServicePrice;
	}

	public TransportServiceDto(int idTransportService, String transportServiceName, VehicleDto vehicle,
			TransportModalityDto tmodality, double transpServicePrice) {
		this.idTransportService = idTransportService;
		this.transportServiceName = transportServiceName;
		this.vehicle = vehicle;
		this.tmodality = tmodality;
		this.transpServicePrice = transpServicePrice;
	}

	public int getIdTransportService() {
		return this.idTransportService;
	}

	public void setIdTransportService(int idTransportService) {
		this.idTransportService = idTransportService;
	}

	public String getTransportServiceName() {
		return this.transportServiceName;
	}

	public void setTransportServiceName(String transportServiceName) {
		this.transportServiceName = transportServiceName;
	}

	public VehicleDto getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(VehicleDto vehicle) {
		this.vehicle = vehicle;
	}

	public TransportModalityDto getTmodality() {
		return this.tmodality;
	}

	public void setTmodality(TransportModalityDto tmodality) {
		this.tmodality = tmodality;
	}

	public double getTranspServicePrice() {
		return this.transpServicePrice;
	}

	public void setTranspServicePrice(double transpServicePrice) {
		this.transpServicePrice = transpServicePrice;
	}

}
