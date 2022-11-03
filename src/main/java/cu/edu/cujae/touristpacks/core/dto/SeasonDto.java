package cu.edu.cujae.touristpacks.core.dto;

import java.time.LocalDate;

public class SeasonDto {
	private int idSeason;
	private String seasonName;
	private LocalDate startSeason;
	private LocalDate endSeason;
	private String seasonDescription;

	public SeasonDto() {

	}

	public SeasonDto(String seasonName, LocalDate startSeason, LocalDate endSeason, String seasonDescription) {
		this.seasonName = seasonName;
		this.startSeason = startSeason;
		this.endSeason = endSeason;
		this.seasonDescription = seasonDescription;
	}

	public SeasonDto(int idSeason, String seasonName, LocalDate startSeason, LocalDate endSeason,
			String seasonDescription) {
		this.idSeason = idSeason;
		this.seasonName = seasonName;
		this.startSeason = startSeason;
		this.endSeason = endSeason;
		this.seasonDescription = seasonDescription;
	}

	public int getIdSeason() {
		return idSeason;
	}

	public void setIdSeason(int idSeason) {
		if (idSeason >= 0)
			this.idSeason = idSeason;
	}

	public LocalDate getStartSeason() {
		return startSeason;
	}

	public void setStartSeason(LocalDate startSeason) {
		this.startSeason = startSeason;
	}

	public LocalDate getEndSeason() {
		return endSeason;
	}

	public void setEndSeason(LocalDate endSeason) {
		this.endSeason = endSeason;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public String getSeasonDescription() {
		return seasonDescription;
	}

	public void setSeasonDescription(String seasonDescription) {
		this.seasonDescription = seasonDescription;
	}

	@Override
	public String toString() {
		return "SeasonDto{" +
				", seasonName='" + seasonName + '\'' +
				", startSeason=" + startSeason +
				", endSeason=" + endSeason +
				", seasonDescription='" + seasonDescription + '\'' +
				'}';
	}

}
