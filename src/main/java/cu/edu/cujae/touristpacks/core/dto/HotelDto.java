package cu.edu.cujae.touristpacks.core.dto;

import java.util.List;

public class HotelDto {

	private int idHotel;
	private String hotelName;
	private String address;
	private int category;
	private String telephoneNumber;
	private String fax;
	private String email;
	private double distanceToNearestCity;
	private double distanceToAirport;
	private int roomsAmount;
	private int levelsAmount;
	private String localization;
	private HotelChainDto hotelChain;
	private ProvinceDto province;
	private List<HotelModalityDto> hotelModalities;

	public HotelDto() {
	}

	public HotelDto(String hotelName, String address, int category, String telephoneNumber, String fax, String email,
			double distanceToNearestCity, double distanceToAirport, int roomsAmount, int levelsAmount,
			String localization, HotelChainDto hotelChain, ProvinceDto province, List<HotelModalityDto> hotelModalities) {
		this.hotelName = hotelName;
		this.address = address;
		this.category = category;
		this.telephoneNumber = telephoneNumber;
		this.fax = fax;
		this.email = email;
		this.distanceToNearestCity = distanceToNearestCity;
		this.distanceToAirport = distanceToAirport;
		this.roomsAmount = roomsAmount;
		this.levelsAmount = levelsAmount;
		this.localization = localization;
		this.hotelChain = hotelChain;
		this.province = province;
		this.hotelModalities = hotelModalities;

	}

	public HotelDto(int idHotel, String hotelName, String address, int category, String telephoneNumber, String fax,
			String email,
			double distanceToNearestCity, double distanceToAirport, int roomsAmount, int levelsAmount,
			String localization, HotelChainDto hotelChain, ProvinceDto province, List<HotelModalityDto> hotelModalities) {
		this.idHotel = idHotel;
		this.hotelName = hotelName;
		this.address = address;
		this.category = category;
		this.telephoneNumber = telephoneNumber;
		this.fax = fax;
		this.email = email;
		this.distanceToNearestCity = distanceToNearestCity;
		this.distanceToAirport = distanceToAirport;
		this.roomsAmount = roomsAmount;
		this.levelsAmount = levelsAmount;
		this.localization = localization;
		this.hotelChain = hotelChain;
		this.province = province;
		this.hotelModalities = hotelModalities;

	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		if (idHotel >= 0)
			this.idHotel = idHotel;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getDistanceToNearestCity() {
		return distanceToNearestCity;
	}

	public void setDistanceToNearestCity(double distanceToNearestCity) {
		if (distanceToNearestCity >= 0)
			this.distanceToNearestCity = distanceToNearestCity;
	}

	public double getDistanceToAirport() {
		return distanceToAirport;
	}

	public void setDistanceToAirport(double distanceToAirport) {
		if (distanceToAirport >= 0)
			this.distanceToAirport = distanceToAirport;
	}

	public int getRoomsAmount() {
		return roomsAmount;
	}

	public void setRoomsAmount(int roomsAmount) {
		if (roomsAmount >= 0)
			this.roomsAmount = roomsAmount;
	}

	public int getLevelsAmount() {
		return levelsAmount;
	}

	public void setLevelsAmount(int levelsAmount) {
		if (levelsAmount >= 0)
			this.levelsAmount = levelsAmount;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public HotelChainDto getHotelChain() {
		return this.hotelChain;
	}

	public void setHotelChain(HotelChainDto hotelChain) {
		this.hotelChain = hotelChain;
	}

	public ProvinceDto getProvince() {
		return this.province;
	}

	public void setProvince(ProvinceDto province) {
		this.province = province;
	}


	public List<HotelModalityDto> getHotelModalities() {
		return this.hotelModalities;
	}

	public void setHotelModalities(List<HotelModalityDto> hotelModalities) {
		this.hotelModalities = hotelModalities;
	}

}
