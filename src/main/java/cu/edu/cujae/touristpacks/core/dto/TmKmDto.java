package cu.edu.cujae.touristpacks.core.dto;

public class TmKmDto extends TransportModalityDto {

	private int idTmKm;
	private double costPerKm;
	private double costPerKmGoAndBack;
	private double costPerWaitingHour;

	public TmKmDto() {

	}

	public TmKmDto(String transportModalityName, double costPerKm, double costPerKmGoAndBack, double costPerWaitingHour,
			int idTransportModality) {
		super(transportModalityName);
		this.costPerKm = costPerKm;
		this.costPerKmGoAndBack = costPerKmGoAndBack;
		this.costPerWaitingHour = costPerWaitingHour;
		this.idTransportModality = idTransportModality;
	}

	public TmKmDto(int idTmKm, String transportModalityName, double costPerKm, double costPerKmGoAndBack,
			double costPerWaitingHour, int idTransportModality) {
		super(transportModalityName);
		this.idTmKm = idTmKm;
		this.costPerKm = costPerKm;
		this.costPerKmGoAndBack = costPerKmGoAndBack;
		this.costPerWaitingHour = costPerWaitingHour;
		this.idTransportModality = idTransportModality;
	}

	public TmKmDto(String tmodalityName, double costPerKm, double costPerKmGoAndBack, double costPerWaitingHour) {
		super(tmodalityName);
		this.costPerKm = costPerKm;
		this.costPerKmGoAndBack = costPerKmGoAndBack;
		this.costPerWaitingHour = costPerWaitingHour;
	}

	public TmKmDto(int idTmKm, String tmodalityName, double costPerKm, double costPerKmGoAndBack,
			double costPerWaitingHour) {
		super(tmodalityName);
		this.idTmKm = idTmKm;
		this.costPerKm = costPerKm;
		this.costPerKmGoAndBack = costPerKmGoAndBack;
		this.costPerWaitingHour = costPerWaitingHour;
	}

	public int getIdTmKm() {
		return idTmKm;
	}

	public void setIdTmKm(int idTmKm) {
		this.idTmKm = idTmKm;
	}

	public double getCostPerKm() {
		return costPerKm;
	}

	public void setCostPerKm(double costPerKm) {
		if (costPerKm >= 0)
			this.costPerKm = costPerKm;
	}

	public double getCostPerKmGoAndBack() {
		return costPerKmGoAndBack;
	}

	public void setCostPerKmGoAndBack(double costPerKmGoAndBack) {
		if (costPerKmGoAndBack >= 0)
			this.costPerKmGoAndBack = costPerKmGoAndBack;
	}

	public double getCostPerWaitingHour() {
		return costPerWaitingHour;
	}

	public void setCostPerWaitingHour(double costPerWaitingHour) {
		if (costPerWaitingHour >= 0)
			this.costPerWaitingHour = costPerWaitingHour;
	}

}
