package cu.edu.cujae.touristpacks.core.dto;

import java.time.LocalDate;
import java.util.List;

public class TransportContractDto extends ContractDto {

	private int idTransportContract;
	private ProviderDto provider;
	private List<TransportServiceDto> transportServices;

	public TransportContractDto() {
	}

	public TransportContractDto(int idContract, String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate, ProviderDto provider, List<TransportServiceDto> transportServices) {
		super(idContract, contractTitle, startDate, endDate, conciliationDate);
		this.provider = provider;
		this.transportServices = transportServices;
	}

	public TransportContractDto(int idTransportContract, int idContract, String contractTitle, LocalDate startDate,
			LocalDate endDate,
			LocalDate conciliationDate, ProviderDto provider, List<TransportServiceDto> transportServices) {
		this(idContract, contractTitle, startDate, endDate, conciliationDate, provider, transportServices);
		this.idTransportContract = idTransportContract;
	}

	public int getIdTransportContract() {
		return this.idTransportContract;
	}

	public void setIdTransportContract(int idTransportContract) {
		this.idTransportContract = idTransportContract;
	}

	public ProviderDto getProvider() {
		return this.provider;
	}

	public void setProvider(ProviderDto provider) {
		this.provider = provider;
	}

	public List<TransportServiceDto> getTransportServices() {
		return this.transportServices;
	}

	public void setTransportServices(List<TransportServiceDto> transportServices) {
		this.transportServices = transportServices;
	}

}
