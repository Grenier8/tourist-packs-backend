package cu.edu.cujae.touristpacks.core.dto;

public class ProvinceDto {

	private int idProvince;
	private String provinceName;

	public ProvinceDto() {

	}

	public ProvinceDto(String provinceName) {
		this.provinceName = provinceName;
	}

	public ProvinceDto(int idProvince, String provinceName) {
		this.idProvince = idProvince;
		this.provinceName = provinceName;
	}

	public int getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(int idProvince) {
		if (idProvince >= 0)
			this.idProvince = idProvince;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {

		this.provinceName = provinceName;
	}

}
