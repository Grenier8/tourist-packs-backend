package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.MayorDto;

public interface IMayorService {

    public List<MayorDto> getMayors() throws SQLException;

    public MayorDto getMayorById(int id) throws SQLException;

    public void createMayor(MayorDto minor) throws SQLException;

    public void deleteMayor(int id) throws SQLException;

    public void updateMayor(MayorDto minor) throws SQLException;

    public MayorDto getMayorByName(String minorName) throws SQLException;

}
