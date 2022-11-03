package cu.edu.cujae.touristpacks.core.dto;

public class AlimentaryPlanDto {

	private int idAlimentaryPlan;
	private String alimentaryPlanName;

	public AlimentaryPlanDto() {

	}

	public AlimentaryPlanDto(String alimentaryPlanName) {
		this.alimentaryPlanName = alimentaryPlanName;
	}

	public AlimentaryPlanDto(int idAlimentaryPlan, String alimentaryPlanName) {
		this.idAlimentaryPlan = idAlimentaryPlan;
		this.alimentaryPlanName = alimentaryPlanName;
	}

	public int getIdAlimentaryPlan() {
		return this.idAlimentaryPlan;
	}

	public void setIdAlimentaryPlan(int idAlimentaryPlan) {
		this.idAlimentaryPlan = idAlimentaryPlan;
	}

	public String getAlimentaryPlanName() {
		return this.alimentaryPlanName;
	}

	public void setAlimentaryPlanName(String alimentaryPlanName) {
		this.alimentaryPlanName = alimentaryPlanName;
	}

}
