package cu.edu.cujae.touristpacks.core.dto;

public class RoomPlanSeasonHotelContractDto {

	private int idRoomPlanSeasonHotelContract;
	private RoomPlanSeasonDto roomPlanSeason;
	private HotelContractDto hotelContract;

	public RoomPlanSeasonHotelContractDto() {
	}

	public RoomPlanSeasonHotelContractDto(RoomPlanSeasonDto roomPlanSeason, HotelContractDto hotelContract) {
		this.roomPlanSeason = roomPlanSeason;
		this.hotelContract = hotelContract;
	}

	public RoomPlanSeasonHotelContractDto(int idRoomPlanSeasonHotelContract, RoomPlanSeasonDto roomPlanSeason,
			HotelContractDto hotelContract) {
		this.idRoomPlanSeasonHotelContract = idRoomPlanSeasonHotelContract;
		this.roomPlanSeason = roomPlanSeason;
		this.hotelContract = hotelContract;
	}

	public int getIdRoomPlanSeasonHotelContract() {
		return this.idRoomPlanSeasonHotelContract;
	}

	public void setIdRoomPlanSeasonHotelContract(int idRoomPlanSeasonHotelContract) {
		this.idRoomPlanSeasonHotelContract = idRoomPlanSeasonHotelContract;
	}

	public RoomPlanSeasonDto getRoomPlanSeason() {
		return this.roomPlanSeason;
	}

	public void setRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) {
		this.roomPlanSeason = roomPlanSeason;
	}

	public HotelContractDto getHotelContract() {
		return this.hotelContract;
	}

	public void setHotelContract(HotelContractDto hotelContract) {
		this.hotelContract = hotelContract;
	}

}