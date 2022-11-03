package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.AlimentaryPlanDto;

public interface IAlimentaryPlanService {
    List<AlimentaryPlanDto> getAlimentaryPlans();

    AlimentaryPlanDto getAlimentaryPlanById(int alimentaryPlanId);

    AlimentaryPlanDto getAlimentaryPlanByName(String alimentaryPlanName);

    void createAlimentaryPlan(AlimentaryPlanDto alimentaryPlan);

    void updateAlimentaryPlan(AlimentaryPlanDto alimentaryPlan);

    void deleteAlimentaryPlan(int idAlimentaryPlan);
}
