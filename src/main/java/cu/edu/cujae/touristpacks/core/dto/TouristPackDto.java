package cu.edu.cujae.touristpacks.core.dto;

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

	public TouristPackDto() {

	}

	public TouristPackDto(String promotionalName, int daysAmount, int nightsAmount, int paxAmount,
			double hotelAirportPrice, HotelDto hotel, RoomPlanSeasonDto roomPlanSeason) {
		this.promotionalName = promotionalName;
		this.daysAmount = daysAmount;
		this.nightsAmount = nightsAmount;
		this.paxAmount = paxAmount;
		this.hotelAirportPrice = hotelAirportPrice;
		this.hotel = hotel;
		this.roomPlanSeason = roomPlanSeason;

		// inicialateAtributes();
	}

	public TouristPackDto(int idTouristPack, String promotionalName, int daysAmount, int nightsAmount, int paxAmount,
			double hotelAirportPrice, HotelDto hotel, RoomPlanSeasonDto roomPlanSeason) {
		this.idTouristPack = idTouristPack;
		this.promotionalName = promotionalName;
		this.daysAmount = daysAmount;
		this.nightsAmount = nightsAmount;
		this.paxAmount = paxAmount;
		this.hotelAirportPrice = hotelAirportPrice;
		this.hotel = hotel;
		this.roomPlanSeason = roomPlanSeason;

		// inicialateAtributes();
	}

	public TouristPackDto(int idTouristPack, String promotionalName, int nightsAmount, int paxAmount,
			double hotelAirportPrice, HotelDto hotel, RoomPlanSeasonDto roomPlanSeason) {
		this.idTouristPack = idTouristPack;
		this.promotionalName = promotionalName;
		this.daysAmount = nightsAmount + 1;
		this.nightsAmount = nightsAmount;
		this.paxAmount = paxAmount;
		this.hotelAirportPrice = hotelAirportPrice;
		this.hotel = hotel;
		this.roomPlanSeason = roomPlanSeason;

	}

	// private void inicialateAtributes() {
	// try {
	// hotelName = ServicesLocator.getHotelServices().getName(idHotel);

	// RoomPlanSeasonDto dto =
	// ServicesLocator.getRoomPlanSeasonServices().get(idRoomPlanSeason);
	// roomPlanSeasonName = dto.getRoomPlanSeasonName();
	// hotelPrice = dto.getRoomReservedPrice();

	// double hotelPackPrice = nightsAmount * (hotelPrice + 0.10 * hotelPrice);
	// double transportPrice = hotelAirportPrice
	// +
	// ServicesLocator.getTransportServiceTuristPackServices().totalPackPrice(idPack);
	// double activitiesPrice =
	// ServicesLocator.getDiaryActivityTuristPackServices().totalPackPrice(idPack);

	// double auxTotal = hotelPackPrice + transportPrice + activitiesPrice;
	// totalPrice = auxTotal + 0.15 * auxTotal;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

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

}
