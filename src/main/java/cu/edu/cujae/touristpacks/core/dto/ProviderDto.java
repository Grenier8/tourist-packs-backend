package cu.edu.cujae.touristpacks.core.dto;

public class ProviderDto {
	private int idProvider;
	private String providerName;

	public ProviderDto() {

	}

	public ProviderDto(String providerName) {
		this.providerName = providerName;
	}

	public ProviderDto(int idProvider, String providerName) {
		this.idProvider = idProvider;
		this.providerName = providerName;
	}

	public int getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(int idProvider) {
		if (idProvider >= 0)
			this.idProvider = idProvider;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

}
