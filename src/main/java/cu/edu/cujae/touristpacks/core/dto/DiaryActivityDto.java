package cu.edu.cujae.touristpacks.core.dto;

import java.time.LocalDate;

public class DiaryActivityDto {

	private int idDiaryActivity;
	private String diaryActivityName;
	private LocalDate date;
	private String description;

	public DiaryActivityDto() {
	}

	public DiaryActivityDto(String diaryActivityName, LocalDate date, String description) {
		this.diaryActivityName = diaryActivityName;
		this.date = date;
		this.description = description;
	}

	public DiaryActivityDto(int idDiaryActivity, String diaryActivityName, LocalDate date, String description) {
		this.idDiaryActivity = idDiaryActivity;
		this.diaryActivityName = diaryActivityName;
		this.date = date;
		this.description = description;
	}

	public int getIdDiaryActivity() {
		return this.idDiaryActivity;
	}

	public void setIdDiaryActivity(int idDiaryActivity) {
		this.idDiaryActivity = idDiaryActivity;
	}

	public String getDiaryActivityName() {
		return this.diaryActivityName;
	}

	public void setDiaryActivityName(String diaryActivityName) {
		this.diaryActivityName = diaryActivityName;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
