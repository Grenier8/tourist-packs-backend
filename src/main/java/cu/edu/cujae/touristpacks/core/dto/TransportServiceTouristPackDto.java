package cu.edu.cujae.touristpacks.core.dto;

public class TransportServiceTouristPackDto {

	private int idTransportServiceTouristPack;
	private TransportServiceDto transportService;
	private TouristPackDto touristPack;

	public TransportServiceTouristPackDto() {
	}

	public TransportServiceTouristPackDto(TransportServiceDto transportService, TouristPackDto touristPack) {
		this.transportService = transportService;
		this.touristPack = touristPack;
	}

	public TransportServiceTouristPackDto(int idTransportServiceTouristPack, TransportServiceDto transportService,
			TouristPackDto touristPack) {
		this.idTransportServiceTouristPack = idTransportServiceTouristPack;
		this.transportService = transportService;
		this.touristPack = touristPack;
	}

	public int getIdTransportServiceTouristPack() {
		return this.idTransportServiceTouristPack;
	}

	public void setIdTransportServiceTouristPack(int idTransportServiceTouristPack) {
		this.idTransportServiceTouristPack = idTransportServiceTouristPack;
	}

	public TransportServiceDto getTransportService() {
		return this.transportService;
	}

	public void setTransportService(TransportServiceDto transportService) {
		this.transportService = transportService;
	}

	public TouristPackDto getTouristPack() {
		return this.touristPack;
	}

	public void setTouristPack(TouristPackDto touristPack) {
		this.touristPack = touristPack;
	}

}
