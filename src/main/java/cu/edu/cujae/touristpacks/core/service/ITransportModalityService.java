package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportModalityDto;

public interface ITransportModalityService {
    List<TransportModalityDto> getTransportModalities() throws SQLException;

    TransportModalityDto getTransportModalityById(int transportModalityId) throws SQLException;

    TransportModalityDto getTransportModalityByName(String transportModalityName) throws SQLException;

    void createTransportModality(TransportModalityDto transportModality) throws SQLException;

    void updateTransportModality(TransportModalityDto transportModality) throws SQLException;

    void deleteTransportModality(int idTransportModality) throws SQLException;
}
