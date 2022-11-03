package cu.edu.cujae.touristpacks.core.dto;

public class HotelModalityDto {
	private int idHotelModality;
	private String hotelModalityName;

	public HotelModalityDto() {

	}

	public HotelModalityDto(String hotelModalityName) {
		this.hotelModalityName = hotelModalityName;
	}

	public HotelModalityDto(int idHotelModality, String hotelModalityName) {
		this.idHotelModality = idHotelModality;
		this.hotelModalityName = hotelModalityName;
	}

	public int getIdHotelModality() {
		return this.idHotelModality;
	}

	public void setIdHotelModality(int idHotelModality) {
		this.idHotelModality = idHotelModality;
	}

	public String getHotelModalityName() {
		return hotelModalityName;
	}

	public void setHotelModalityName(String hotelModalityName) {
		this.hotelModalityName = hotelModalityName;
	}

}
