package cu.edu.cujae.touristpacks.core.dto;

import java.time.LocalDate;

public class HotelContractDto extends ContractDto {

	private int idHotelContract;
	private String contractDescription;
	private boolean active;
	private HotelDto hotel;

	public HotelContractDto() {

	}

	public HotelContractDto(String contractTitle, LocalDate startDate, LocalDate endDate, LocalDate conciliationDate,
			String contractDescription, HotelDto hotel, int idContract) {
		super(contractTitle, startDate, endDate, conciliationDate);
		this.contractDescription = contractDescription;
		this.hotel = hotel;
		this.idContract = idContract;
	}

	public HotelContractDto(String contractTitle, LocalDate startDate, LocalDate endDate, LocalDate conciliationDate,
			String contractDescription, HotelDto hotel) {
		super(contractTitle, startDate, endDate, conciliationDate);
		this.contractDescription = contractDescription;
		this.hotel = hotel;
	}

	public HotelContractDto(int idHotelContract, String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate,
			String contractDescription, HotelDto hotel) {
		super(contractTitle, startDate, endDate, conciliationDate);
		this.idHotelContract = idHotelContract;
		this.contractDescription = contractDescription;
		this.hotel = hotel;
	}

	public HotelContractDto(int idHotelContract, String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate,
			String contractDescription, HotelDto hotel, int idContract) {
		super(contractTitle, startDate, endDate, conciliationDate);
		this.idHotelContract = idHotelContract;
		this.contractDescription = contractDescription;
		this.hotel = hotel;
		this.idContract = idContract;
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

}
