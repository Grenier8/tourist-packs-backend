package cu.edu.cujae.touristpacks.core.dto;

public class HotelChainDto {

	private int idHotelChain;
	private String hotelChainName;

	public HotelChainDto() {

	}

	public HotelChainDto(int idHotelChain, String hotelChainName) {
		this.idHotelChain = idHotelChain;
		this.hotelChainName = hotelChainName;
	}

	public HotelChainDto(String hotelChainName) {
		this.hotelChainName = hotelChainName;
	}

	public int getIdHotelChain() {
		return this.idHotelChain;
	}

	public void setIdHotelChain(int idHotelChain) {
		this.idHotelChain = idHotelChain;
	}

	public String getHotelChainName() {
		return this.hotelChainName;
	}

	public void setHotelChainName(String hotelChainName) {
		this.hotelChainName = hotelChainName;
	}

}
