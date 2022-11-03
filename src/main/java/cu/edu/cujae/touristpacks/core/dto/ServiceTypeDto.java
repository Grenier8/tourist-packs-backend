package cu.edu.cujae.touristpacks.core.dto;

public class ServiceTypeDto {

	private int idServiceType;
	private String serviceTypeName;

	public ServiceTypeDto() {

	}

	public ServiceTypeDto(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public ServiceTypeDto(int idServiceType, String serviceTypeName) {
		this.idServiceType = idServiceType;
		this.serviceTypeName = serviceTypeName;
	}

	public int getIdServiceType() {
		return idServiceType;
	}

	public void setIdServiceType(int idServiceType) {
		if (idServiceType >= 0)
			this.idServiceType = idServiceType;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

}
