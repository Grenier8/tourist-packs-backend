package cu.edu.cujae.touristpacks.core.dto;

public class TransportModalityDto {

	protected int idTransportModality;
	protected String transportModalityName;
	private String transportModalityType;

	public TransportModalityDto() {
	}

	public TransportModalityDto(String transportModalityName) {
		this.transportModalityName = transportModalityName;
	}

	public TransportModalityDto(int idTransportModality, String transportModalityName) {
		this.idTransportModality = idTransportModality;
		this.transportModalityName = transportModalityName;
	}

	public int getIdTransportModality() {
		return this.idTransportModality;
	}

	public void setIdTransportModality(int idTransportModality) {
		this.idTransportModality = idTransportModality;
	}

	public String getTransportModalityName() {
		return this.transportModalityName;
	}

	public void setTransportModalityName(String transportModalityName) {
		this.transportModalityName = transportModalityName;
	}

	public String getTransportModalityType() {
		return this.transportModalityType;
	}

	public void setTransportModalityType(String transportModalityType) {
		this.transportModalityType = transportModalityType;
	}

}
