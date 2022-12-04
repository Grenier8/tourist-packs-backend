package cu.edu.cujae.touristpacks.core.dto;

import java.time.LocalDate;
import java.util.List;

public class HotelContractDto extends ContractDto {

	private int idHotelContract;
	private String contractDescription;
	private boolean active;
	private HotelDto hotel;
	private List<RoomPlanSeasonDto> roomPlanSeasons;

	public HotelContractDto() {

	}

	public HotelContractDto(int idContract, String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate,
			String contractDescription, HotelDto hotel, List<RoomPlanSeasonDto> roomPlanSeasons) {
		super(idContract, contractTitle, startDate, endDate, conciliationDate);
		this.contractDescription = contractDescription;
		this.hotel = hotel;
		this.roomPlanSeasons = roomPlanSeasons;
	}

	public HotelContractDto(int idHotelContract, int idContract, String contractTitle, LocalDate startDate,
			LocalDate endDate,
			LocalDate conciliationDate,
			String contractDescription, HotelDto hotel, List<RoomPlanSeasonDto> roomPlanSeasons) {
		this(idContract, contractTitle, startDate, endDate, conciliationDate, contractDescription, hotel,
				roomPlanSeasons);
		this.idHotelContract = idHotelContract;
	}

	public int getIdHotelContract() {
		return this.idHotelContract;
	}

	public void setIdHotelContract(int idHotelContract) {
		this.idHotelContract = idHotelContract;
	}

	public String getContractDescription() {
		return this.contractDescription;
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public boolean isActive() {
		return this.active;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public HotelDto getHotel() {
		return this.hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	public List<RoomPlanSeasonDto> getRoomPlanSeasons() {
		return this.roomPlanSeasons;
	}

	public void setRoomPlanSeasons(List<RoomPlanSeasonDto> roomPlanSeasons) {
		this.roomPlanSeasons = roomPlanSeasons;
	}

}
