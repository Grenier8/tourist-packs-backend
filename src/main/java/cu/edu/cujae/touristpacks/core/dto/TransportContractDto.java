package cu.edu.cujae.touristpacks.core.dto;

import java.time.LocalDate;

public class TransportContractDto extends ContractDto {

	private int idTransportContract;
	private ProviderDto provider;

	public TransportContractDto() {
		super();
	}

	public TransportContractDto(String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate, ProviderDto provider) {
		super(contractTitle, startDate, endDate, conciliationDate);
		this.provider = provider;
	}

	public TransportContractDto(int idTransportContract, String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate, ProviderDto provider) {
		super(contractTitle, startDate, endDate, conciliationDate);
		this.idTransportContract = idTransportContract;
		this.provider = provider;
	}

	public TransportContractDto(String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate, int idTransportContract, ProviderDto provider) {
		super(contractTitle, startDate, endDate, conciliationDate);
		this.idTransportContract = idTransportContract;
		this.provider = provider;
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

}
