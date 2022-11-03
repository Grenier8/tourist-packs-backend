package cu.edu.cujae.touristpacks.core.dto;

public class RoomTypeDto {
	private int idRoomType;
	private String roomTypeName;

	public RoomTypeDto() {

	}

	public RoomTypeDto(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public RoomTypeDto(int idRoomType, String roomTypeName) {
		this.idRoomType = idRoomType;
		this.roomTypeName = roomTypeName;
	}

	public int getIdRoomType() {
		return idRoomType;
	}

	public void setIdRoomType(int idRoomType) {
		if (idRoomType >= 0)
			this.idRoomType = idRoomType;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

}
