package cu.edu.cujae.touristpacks.core.dto;

public class TravelDto {

	private int idTravel;
	private String travelName;
	private TmTravelDto tmodalityTravel;

	public TravelDto() {

	}

	public TravelDto(String travelName, TmTravelDto tmodalityTravel) {
		this.travelName = travelName;
		this.tmodalityTravel = tmodalityTravel;

	}

	public TravelDto(int idTravel, String travelName, TmTravelDto tmodalityTravel) {
		this.idTravel = idTravel;
		this.travelName = travelName;
		this.tmodalityTravel = tmodalityTravel;

	}

	public int getIdTravel() {
		return idTravel;
	}

	public void setIdTravel(int idTravel) {
		if (idTravel >= 0)
			this.idTravel = idTravel;
	}

	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}

	public TmTravelDto getTmodalityTravel() {
		return this.tmodalityTravel;
	}

	public void setTmodalityTravel(TmTravelDto tmodalityTravel) {
		this.tmodalityTravel = tmodalityTravel;
	}

}
