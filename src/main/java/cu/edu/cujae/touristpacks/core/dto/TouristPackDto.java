package cu.edu.cujae.touristpacks.core.dto;

import java.util.List;

public class TouristPackDto {

	private int idTouristPack;
	private String promotionalName;
	private int daysAmount;
	private int nightsAmount;
	private int paxAmount;
	private double hotelPrice;
	private double totalPrice;
	private double hotelAirportPrice;
	private HotelDto hotel;
	private RoomPlanSeasonDto roomPlanSeason;
	private List<TransportServiceDto> transportServices;
	private List<DiaryActivityDto> diaryActivities;

	public TouristPackDto() {

	}

	public TouristPackDto(String promotionalName, int daysAmount, int nightsAmount, int paxAmount,
			double hotelAirportPrice, HotelDto hotel, RoomPlanSeasonDto roomPlanSeason,
			List<TransportServiceDto> transportServices, List<DiaryActivityDto> diaryActivities) {
		this.promotionalName = promotionalName;
		this.daysAmount = daysAmount;
		this.nightsAmount = nightsAmount;
		this.paxAmount = paxAmount;
		this.hotelAirportPrice = hotelAirportPrice;
		this.hotel = hotel;
		this.roomPlanSeason = roomPlanSeason;
		this.transportServices = transportServices;
		this.diaryActivities = diaryActivities;

		inicialateAtributes();
	}

	public TouristPackDto(int idTouristPack, String promotionalName, int daysAmount, int nightsAmount, int paxAmount,
			double hotelAirportPrice, HotelDto hotel, RoomPlanSeasonDto roomPlanSeason,
			List<TransportServiceDto> transportServices, List<DiaryActivityDto> diaryActivities) {
		this(promotionalName, daysAmount, nightsAmount, paxAmount, hotelAirportPrice, hotel, roomPlanSeason,
				transportServices, diaryActivities);
		this.idTouristPack = idTouristPack;

	}

	public TouristPackDto(int idTouristPack, String promotionalName, int nightsAmount, int paxAmount,
			double hotelAirportPrice, HotelDto hotel, RoomPlanSeasonDto roomPlanSeason,
			List<TransportServiceDto> transportServices, List<DiaryActivityDto> diaryActivities) {
		this(idTouristPack, promotionalName, nightsAmount + 1, nightsAmount, paxAmount, hotelAirportPrice, hotel,
				roomPlanSeason,
				transportServices, diaryActivities);

	}

	private void inicialateAtributes() {

		hotelPrice = roomPlanSeason.getRoomReservedPrice();

		double hotelPackPrice = nightsAmount * (hotelPrice + 0.10 * hotelPrice);
		double transportPrice = hotelAirportPrice + transportServicesPrice();
		double activitiesPrice = diaryActivitiesPrice();

		double auxTotal = hotelPackPrice + transportPrice + activitiesPrice;
		totalPrice = auxTotal + 0.15 * auxTotal;
	}

	private double diaryActivitiesPrice() {
		double total = 0f;
		for (DiaryActivityDto diaryActivity : diaryActivities) {
			total += 2000f / (diaryActivities.size() * 1.0);
		}
		return total;
	}

	private double transportServicesPrice() {
		double total = 0f;
		for (TransportServiceDto transportService : transportServices) {
			total += transportService.getTranspServicePrice();
		}
		return total;
	}

	public int getIdTouristPack() {
		return this.idTouristPack;
	}

	public void setIdTouristPack(int idTouristPack) {
		this.idTouristPack = idTouristPack;
	}

	public String getPromotionalName() {
		return this.promotionalName;
	}

	public void setPromotionalName(String promotionalName) {
		this.promotionalName = promotionalName;
	}

	public int getDaysAmount() {
		return this.daysAmount;
	}

	public void setDaysAmount(int daysAmount) {
		this.daysAmount = daysAmount;
	}

	public int getNightsAmount() {
		return this.nightsAmount;
	}

	public void setNightsAmount(int nightsAmount) {
		this.nightsAmount = nightsAmount;
	}

	public int getPaxAmount() {
		return this.paxAmount;
	}

	public void setPaxAmount(int paxAmount) {
		this.paxAmount = paxAmount;
	}

	public double getHotelPrice() {
		return this.hotelPrice;
	}

	public void setHotelPrice(double hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getHotelAirportPrice() {
		return this.hotelAirportPrice;
	}

	public void setHotelAirportPrice(double hotelAirportPrice) {
		this.hotelAirportPrice = hotelAirportPrice;
	}

	public HotelDto getHotel() {
		return this.hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	public RoomPlanSeasonDto getRoomPlanSeason() {
		return this.roomPlanSeason;
	}

	public void setRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) {
		this.roomPlanSeason = roomPlanSeason;
	}

	public List<TransportServiceDto> getTransportServices() {
		return this.transportServices;
	}

	public void setTransportServices(List<TransportServiceDto> transportServices) {
		this.transportServices = transportServices;
	}

	public List<DiaryActivityDto> getDiaryActivities() {
		return this.diaryActivities;
	}

	public void setDiaryActivities(List<DiaryActivityDto> diaryActivities) {
		this.diaryActivities = diaryActivities;
	}

}
