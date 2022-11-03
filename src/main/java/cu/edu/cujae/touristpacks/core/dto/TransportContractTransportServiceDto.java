package cu.edu.cujae.touristpacks.core.dto;

public class TransportContractTransportServiceDto {

	private int idTransportContractTransportService;
	private TransportContractDto transportContract;
	private TransportServiceDto transportService;

	public TransportContractTransportServiceDto() {
	}

	public TransportContractTransportServiceDto(TransportContractDto transportContract,
			TransportServiceDto transportService) {
		this.transportContract = transportContract;
		this.transportService = transportService;
	}

	public TransportContractTransportServiceDto(int idTransportContractTransportService,
			TransportContractDto transportContract, TransportServiceDto transportService) {
		this.idTransportContractTransportService = idTransportContractTransportService;
		this.transportContract = transportContract;
		this.transportService = transportService;
	}

	public int getIdTransportContractTransportService() {
		return this.idTransportContractTransportService;
	}

	public void setIdTransportContractTransportService(int idTransportContractTransportService) {
		this.idTransportContractTransportService = idTransportContractTransportService;
	}

	public TransportContractDto getTransportContract() {
		return this.transportContract;
	}

	public void setTransportContract(TransportContractDto transportContract) {
		this.transportContract = transportContract;
	}

	public TransportServiceDto getTransportService() {
		return this.transportService;
	}

	public void setTransportService(TransportServiceDto transportService) {
		this.transportService = transportService;
	}

}
