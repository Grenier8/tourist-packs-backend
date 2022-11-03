package cu.edu.cujae.touristpacks.core.dto;

public class DiaryActivityTouristPackDto {

	private int idDiaryActivityTouristPack;
	private DiaryActivityDto diaryActivity;
	private TouristPackDto touristPack;

	public DiaryActivityTouristPackDto() {
	}

	public DiaryActivityTouristPackDto(DiaryActivityDto diaryActivity, TouristPackDto touristPack) {
		this.diaryActivity = diaryActivity;
		this.touristPack = touristPack;
	}

	public DiaryActivityTouristPackDto(int idDiaryActivityTouristPack, DiaryActivityDto diaryActivity,
			TouristPackDto touristPack) {
		this.idDiaryActivityTouristPack = idDiaryActivityTouristPack;
		this.diaryActivity = diaryActivity;
		this.touristPack = touristPack;
	}

	public int getIdDiaryActivityTouristPack() {
		return this.idDiaryActivityTouristPack;
	}

	public void setIdDiaryActivityTouristPack(int idDiaryActivityTouristPack) {
		this.idDiaryActivityTouristPack = idDiaryActivityTouristPack;
	}

	public DiaryActivityDto getDiaryActivity() {
		return this.diaryActivity;
	}

	public void setDiaryActivity(DiaryActivityDto diaryActivity) {
		this.diaryActivity = diaryActivity;
	}

	public TouristPackDto getTouristPack() {
		return this.touristPack;
	}

	public void setTouristPack(TouristPackDto touristPack) {
		this.touristPack = touristPack;
	}

}
