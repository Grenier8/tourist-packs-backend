package cu.edu.cujae.touristpacks.core.dto;

import java.time.LocalDate;
import java.util.List;

public class OtherServiceContractDto extends ContractDto {

	private int idOtherServiceContract;
	private String contractDescription;
	private double costPerPax;
	private ServiceTypeDto serviceType;
	private ProvinceDto province;
	private List<DiaryActivityDto> diaryActivities;

	public OtherServiceContractDto() {
	}

	public OtherServiceContractDto(int idOtherServiceContract, int idContract, String contractTitle,
			LocalDate startDate, LocalDate endDate, LocalDate conciliationDate, String contractDescription,
			double costPerPax,
			ServiceTypeDto serviceType, ProvinceDto province, List<DiaryActivityDto> diaryActivities) {
		super(idContract, contractTitle, startDate, endDate, conciliationDate);

		this.idOtherServiceContract = idOtherServiceContract;
		this.contractDescription = contractDescription;
		this.costPerPax = costPerPax;
		this.serviceType = serviceType;
		this.province = province;
		this.diaryActivities = diaryActivities;
	}

	public OtherServiceContractDto(int idContract, String contractTitle,
			LocalDate startDate, LocalDate endDate, LocalDate conciliationDate, String contractDescription,
			double costPerPax,
			ServiceTypeDto serviceType, ProvinceDto province, List<DiaryActivityDto> diaryActivities) {
		super(idContract, contractTitle, startDate, endDate, conciliationDate);

		this.contractDescription = contractDescription;
		this.costPerPax = costPerPax;
		this.serviceType = serviceType;
		this.province = province;
		this.diaryActivities = diaryActivities;
	}

	public int getIdOtherServiceContract() {
		return this.idOtherServiceContract;
	}

	public void setIdOtherServiceContract(int idOtherServiceContract) {
		this.idOtherServiceContract = idOtherServiceContract;
	}

	public String getContractDescription() {
		return this.contractDescription;
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public double getCostPerPax() {
		return this.costPerPax;
	}

	public void setCostPerPax(double costPerPax) {
		this.costPerPax = costPerPax;
	}

	public ServiceTypeDto getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceTypeDto serviceType) {
		this.serviceType = serviceType;
	}

	public ProvinceDto getProvince() {
		return this.province;
	}

	public void setProvince(ProvinceDto province) {
		this.province = province;
	}

	public List<DiaryActivityDto> getDiaryActivities() {
		return this.diaryActivities;
	}

	public void setDiaryActivities(List<DiaryActivityDto> diaryActivities) {
		this.diaryActivities = diaryActivities;
	}

}
