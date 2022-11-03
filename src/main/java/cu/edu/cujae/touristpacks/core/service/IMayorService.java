package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.MayorDto;

public interface IMayorService {

    public List<MayorDto> getMayors();

    public MayorDto getMayorById(Integer id);

    public void createMayor(MayorDto minor);

    public void deleteMayor(Integer id);

}
