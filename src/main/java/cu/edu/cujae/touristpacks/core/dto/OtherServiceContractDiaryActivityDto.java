package cu.edu.cujae.touristpacks.core.dto;

public class OtherServiceContractDiaryActivityDto {

	private int idOtherServiceContractDiaryActivity;
	private double activityPrice;
	private OtherServiceContractDto otherServiceContract;
	private DiaryActivityDto diaryActivity;

	public OtherServiceContractDiaryActivityDto() {
	}

	public OtherServiceContractDiaryActivityDto(int idOtherServiceContractDiaryActivity, double activityPrice,
			OtherServiceContractDto otherServiceContract, DiaryActivityDto diaryActivity) {
		this.idOtherServiceContractDiaryActivity = idOtherServiceContractDiaryActivity;
		this.activityPrice = activityPrice;
		this.otherServiceContract = otherServiceContract;
		this.diaryActivity = diaryActivity;
	}

	public int getIdOtherServiceContractDiaryActivity() {
		return this.idOtherServiceContractDiaryActivity;
	}

	public void setIdOtherServiceContractDiaryActivity(int idOtherServiceContractDiaryActivity) {
		this.idOtherServiceContractDiaryActivity = idOtherServiceContractDiaryActivity;
	}

	public double getActivityPrice() {
		return this.activityPrice;
	}

	public void setActivityPrice(double activityPrice) {
		this.activityPrice = activityPrice;
	}

	public OtherServiceContractDto getOtherServiceContract() {
		return this.otherServiceContract;
	}

	public void setOtherServiceContract(OtherServiceContractDto otherServiceContract) {
		this.otherServiceContract = otherServiceContract;
	}

	public DiaryActivityDto getDiaryActivity() {
		return this.diaryActivity;
	}

	public void setDiaryActivity(DiaryActivityDto diaryActivity) {
		this.diaryActivity = diaryActivity;
	}

}
