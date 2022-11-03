package cu.edu.cujae.touristpacks.core.dto;

public class HotelHotelModalityDto {

	private int idHotelHotelModality;
	private HotelDto hotel;
	private HotelModalityDto hotelModality;

	public HotelHotelModalityDto() {
	}

	public HotelHotelModalityDto(HotelDto hotel, HotelModalityDto hotelModality) {
		this.hotel = hotel;
		this.hotelModality = hotelModality;
	}

	public HotelHotelModalityDto(int idHotelHotelModality, HotelDto hotel, HotelModalityDto hotelModality) {
		this.idHotelHotelModality = idHotelHotelModality;
		this.hotel = hotel;
		this.hotelModality = hotelModality;
	}

	public int getIdHotelHotelModality() {
		return this.idHotelHotelModality;
	}

	public void setIdHotelHotelModality(int idHotelHotelModality) {
		this.idHotelHotelModality = idHotelHotelModality;
	}

	public HotelDto getHotel() {
		return this.hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	public HotelModalityDto getHotelModality() {
		return this.hotelModality;
	}

	public void setHotelModality(HotelModalityDto hotelModality) {
		this.hotelModality = hotelModality;
	}

}
