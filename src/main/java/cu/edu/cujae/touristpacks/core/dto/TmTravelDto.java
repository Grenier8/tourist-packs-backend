package cu.edu.cujae.touristpacks.core.dto;

public class TmTravelDto extends TransportModalityDto {

	private int idTmTravel;
	private String travelDescription;
	private double costTravel;
	private double costTravelGoAndBack;

	public TmTravelDto() {

	}

	public TmTravelDto(String tmodalityName, String travelDescription, double costTravel, double costTravelGoAndBack,
			int idTModality) {
		super(tmodalityName);
		this.travelDescription = travelDescription;
		this.costTravel = costTravel;
		this.costTravelGoAndBack = costTravelGoAndBack;
		this.idTransportModality = idTModality;
	}

	public TmTravelDto(int idTmTravel, String tmodalityName, String travelDescription, double costTravel,
			double costTravelGoAndBack, int idTModality) {
		super(tmodalityName);
		this.idTmTravel = idTmTravel;
		this.travelDescription = travelDescription;
		this.costTravel = costTravel;
		this.costTravelGoAndBack = costTravelGoAndBack;
		this.idTransportModality = idTModality;
	}

	public TmTravelDto(String tmodalityName, String travelDescription, double costTravel, double costTravelGoAndBack) {
		super(tmodalityName);
		this.travelDescription = travelDescription;
		this.costTravel = costTravel;
		this.costTravelGoAndBack = costTravelGoAndBack;
	}

	public TmTravelDto(int idTmTravel, String tmodalityName, String travelDescription, double costTravel,
			double costTravelGoAndBack) {
		super(tmodalityName);
		this.idTmTravel = idTmTravel;
		this.travelDescription = travelDescription;
		this.costTravel = costTravel;
		this.costTravelGoAndBack = costTravelGoAndBack;
	}

	public int getIdTmTravel() {
		return idTmTravel;
	}

	public void setIdTmTravel(int idTmTravel) {
		this.idTmTravel = idTmTravel;
	}

	public String getTravelDescription() {
		return travelDescription;
	}

	public void setTravelDescription(String travelDescription) {
		this.travelDescription = travelDescription;
	}

	public double getCostTravel() {
		return costTravel;
	}

	public void setCostTravel(double costTravel) {
		if (costTravel >= 0)
			this.costTravel = costTravel;
	}

	public double getCostTravelGoAndBack() {
		return costTravelGoAndBack;
	}

	public void setCostTravelGoAndBack(double costTravelGoAndBack) {
		if (costTravelGoAndBack >= 0)
			this.costTravelGoAndBack = costTravelGoAndBack;
	}

}
