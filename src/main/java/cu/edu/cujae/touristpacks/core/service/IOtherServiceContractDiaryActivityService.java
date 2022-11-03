package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDiaryActivityDto;

public interface IOtherServiceContractDiaryActivityService {
        List<OtherServiceContractDiaryActivityDto> getOtherServiceContractDiaryActivities();

        OtherServiceContractDiaryActivityDto getOtherServiceContractDiaryActivityById(
                        int otherServiceContractDiaryActivityId);

        void createOtherServiceContractDiaryActivity(
                        OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity);

        void updateOtherServiceContractDiaryActivity(
                        OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity);

        void deleteOtherServiceContractDiaryActivity(int idOtherServiceContractDiaryActivity);
}
