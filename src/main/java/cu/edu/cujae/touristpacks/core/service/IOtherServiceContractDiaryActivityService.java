package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDiaryActivityDto;

public interface IOtherServiceContractDiaryActivityService {
        List<OtherServiceContractDiaryActivityDto> getOtherServiceContractDiaryActivities() throws SQLException;

        OtherServiceContractDiaryActivityDto getOtherServiceContractDiaryActivityById(
                        int otherServiceContractDiaryActivityId) throws SQLException;

        void createOtherServiceContractDiaryActivity(
                        OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity) throws SQLException;

        void updateOtherServiceContractDiaryActivity(
                        OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity) throws SQLException;

        void deleteOtherServiceContractDiaryActivity(int idOtherServiceContractDiaryActivity) throws SQLException;

        List<DiaryActivityDto> getDiaryActivitiesByIdOtherServiceContract(int idOtherServiceContract)
                        throws SQLException;

        void deleteOtherServiceContractDiaryActivityByIdOtherServiceContract(int idOtherServiceContract)
                        throws SQLException;

        void deleteHotelDiaryActivityByIds(int idOtherServiceContract, int idDiaryActivity) throws SQLException;
}
