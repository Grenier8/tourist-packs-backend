package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.AlimentaryPlanDto;
import cu.edu.cujae.touristpacks.core.service.IAlimentaryPlanService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlimentaryPlanServiceImpl implements IAlimentaryPlanService {

    @Override
    public List<AlimentaryPlanDto> getAlimentaryPlans() {
        List<AlimentaryPlanDto> list = new ArrayList<>();
        list.add(new AlimentaryPlanDto(1, "Desayuno"));
        list.add(new AlimentaryPlanDto(2, "Almuerzo"));

        return list;
    }

    @Override
    public AlimentaryPlanDto getAlimentaryPlanById(int alimentaryPlanId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AlimentaryPlanDto getAlimentaryPlanByName(String alimentaryPlanName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAlimentaryPlan(int idAlimentaryPlan) {
        // TODO Auto-generated method stub

    }

}
