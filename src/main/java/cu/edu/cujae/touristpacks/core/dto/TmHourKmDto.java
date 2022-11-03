package cu.edu.cujae.touristpacks.core.dto;

public class TmHourKmDto extends TransportModalityDto {

	private int idTmHourKm;
	private double costPerTravelledKm;
	private double costPerHour;
	private double costPerExtraKm;
	private double costPerExtraHour;

	public TmHourKmDto() {

	}

	public TmHourKmDto(String tmodalityName, double costPerTravelledKm, double costPerHour, double costPerExtraKm,
			double costPerExtraHour, int idTModality) {
		super(tmodalityName);
		this.costPerTravelledKm = costPerTravelledKm;
		this.costPerHour = costPerHour;
		this.costPerExtraKm = costPerExtraKm;
		this.costPerExtraHour = costPerExtraHour;
		this.idTransportModality = idTModality;
	}

	public TmHourKmDto(int idTmHourKm, String tmodalityName, double costPerTravelledKm, double costPerHour,
			double costPerExtraKm, double costPerExtraHour, int idTModality) {
		super(tmodalityName);
		this.idTmHourKm = idTmHourKm;
		this.costPerTravelledKm = costPerTravelledKm;
		this.costPerHour = costPerHour;
		this.costPerExtraKm = costPerExtraKm;
		this.costPerExtraHour = costPerExtraHour;
		this.idTransportModality = idTModality;
	}

	public TmHourKmDto(String tmodalityName, double costPerTravelledKm, double costPerHour, double costPerExtraKm,
			double costPerExtraHour) {
		super(tmodalityName);
		this.costPerTravelledKm = costPerTravelledKm;
		this.costPerHour = costPerHour;
		this.costPerExtraKm = costPerExtraKm;
		this.costPerExtraHour = costPerExtraHour;
	}

	public TmHourKmDto(int idTmHourKm, String tmodalityName, double costPerTravelledKm, double costPerHour,
			double costPerExtraKm, double costPerExtraHour) {
		super(tmodalityName);
		this.idTmHourKm = idTmHourKm;
		this.costPerTravelledKm = costPerTravelledKm;
		this.costPerHour = costPerHour;
		this.costPerExtraKm = costPerExtraKm;
		this.costPerExtraHour = costPerExtraHour;
	}

	public int getIdTmHourKm() {
		return idTmHourKm;
	}

	public void setIdTmHourKm(int idTmHourKm) {
		this.idTmHourKm = idTmHourKm;
	}

	public double getCostPerTravelledKm() {
		return costPerTravelledKm;
	}

	public void setCostPerTravelledKm(double costPerTravelledKm) {
		if (costPerTravelledKm >= 0)
			this.costPerTravelledKm = costPerTravelledKm;
	}

	public double getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(double costPerHour) {
		if (costPerHour >= 0)
			this.costPerHour = costPerHour;
	}

	public double getCostPerExtraKm() {
		return costPerExtraKm;
	}

	public void setCostPerExtraKm(double costPerExtraKm) {
		if (costPerExtraKm >= 0)
			this.costPerExtraKm = costPerExtraKm;
	}

	public double getCostPerExtraHour() {
		return costPerExtraHour;
	}

	public void setCostPerExtraHour(double costPerExtraHour) {
		if (costPerExtraHour >= 0)
			this.costPerExtraHour = costPerExtraHour;
	}

}
