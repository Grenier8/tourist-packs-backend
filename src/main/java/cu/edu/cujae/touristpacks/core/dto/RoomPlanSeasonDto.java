package cu.edu.cujae.touristpacks.core.dto;

public class RoomPlanSeasonDto {

	private int idRoomPlanSeason;
	private String roomPlanSeasonName;
	private double roomReservedPrice;
	private RoomTypeDto roomType;
	private AlimentaryPlanDto alimentaryPlan;
	private SeasonDto season;

	public RoomPlanSeasonDto() {
	}

	public RoomPlanSeasonDto(String roomPlanSeasonName, double roomReservedPrice,
			RoomTypeDto roomType, AlimentaryPlanDto alimentaryPlan, SeasonDto season) {
		this.roomPlanSeasonName = roomPlanSeasonName;
		this.roomReservedPrice = roomReservedPrice;
		this.roomType = roomType;
		this.alimentaryPlan = alimentaryPlan;
		this.season = season;
	}

	public RoomPlanSeasonDto(int idRoomPlanSeason, String roomPlanSeasonName, double roomReservedPrice,
			RoomTypeDto roomType, AlimentaryPlanDto alimentaryPlan, SeasonDto season) {
		this.idRoomPlanSeason = idRoomPlanSeason;
		this.roomPlanSeasonName = roomPlanSeasonName;
		this.roomReservedPrice = roomReservedPrice;
		this.roomType = roomType;
		this.alimentaryPlan = alimentaryPlan;
		this.season = season;
	}

	public int getIdRoomPlanSeason() {
		return this.idRoomPlanSeason;
	}

	public void setIdRoomPlanSeason(int idRoomPlanSeason) {
		this.idRoomPlanSeason = idRoomPlanSeason;
	}

	public String getRoomPlanSeasonName() {
		return this.roomPlanSeasonName;
	}

	public void setRoomPlanSeasonName(String roomPlanSeasonName) {
		this.roomPlanSeasonName = roomPlanSeasonName;
	}

	public double getRoomReservedPrice() {
		return this.roomReservedPrice;
	}

	public void setRoomReservedPrice(double roomReservedPrice) {
		this.roomReservedPrice = roomReservedPrice;
	}

	public RoomTypeDto getRoomType() {
		return this.roomType;
	}

	public void setRoomType(RoomTypeDto roomType) {
		this.roomType = roomType;
	}

	public AlimentaryPlanDto getAlimentaryPlan() {
		return this.alimentaryPlan;
	}

	public void setAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) {
		this.alimentaryPlan = alimentaryPlan;
	}

	public SeasonDto getSeason() {
		return this.season;
	}

	public void setSeason(SeasonDto season) {
		this.season = season;
	}

}
