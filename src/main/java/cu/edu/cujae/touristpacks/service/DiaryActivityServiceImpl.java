package cu.edu.cujae.touristpacks.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.core.service.IDiaryActivityService;

import org.springframework.stereotype.Service;

@Service
public class DiaryActivityServiceImpl implements IDiaryActivityService {

    @Override
    public List<DiaryActivityDto> getDiaryActivities() {
        List<DiaryActivityDto> list = new ArrayList<>();
        list.add(new DiaryActivityDto(1, "Act1", LocalDate.of(2020, 1, 20), "Descr"));
        list.add(new DiaryActivityDto(2, "Act2", LocalDate.of(2022, 1, 20), "Descr2"));

        return list;
    }

    @Override
    public DiaryActivityDto getDiaryActivityById(int diaryActivityId) {
        // TODO
        return null;
    }

    @Override
    public DiaryActivityDto getDiaryActivityByName(String diaryActivityName) {
        return getDiaryActivities().stream().filter(r -> r.getDiaryActivityName().equals(diaryActivityName)).findFirst()
                .get();
    }

    @Override
    public void createDiaryActivity(DiaryActivityDto diaryActivity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateDiaryActivity(DiaryActivityDto diaryActivity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteDiaryActivity(int idDiaryActivity) {
        // TODO Auto-generated method stub

    }

}
