package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.AlimentaryPlanDto;

public interface IAlimentaryPlanService {
    List<AlimentaryPlanDto> getAlimentaryPlans() throws SQLException;

    AlimentaryPlanDto getAlimentaryPlanById(int alimentaryPlanId) throws SQLException;

    AlimentaryPlanDto getAlimentaryPlanByName(String alimentaryPlanName) throws SQLException;

    void createAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) throws SQLException;

    void updateAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) throws SQLException;

    void deleteAlimentaryPlan(int idAlimentaryPlan) throws SQLException;
}
