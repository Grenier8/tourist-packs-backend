package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceTouristPackDto;

public interface ITransportServiceTouristPackService {
        List<TransportServiceTouristPackDto> getTransportServiceTouristPacks() throws SQLException;

        TransportServiceTouristPackDto getTransportServiceTouristPackById(int transportServiceTouristPackId)
                        throws SQLException;

        void createTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack)
                        throws SQLException;

        void updateTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack)
                        throws SQLException;

        void deleteTransportServiceTouristPack(int idTransportServiceTouristPack) throws SQLException;

        List<TransportServiceDto> getTransportServicesByIdTouristPack(int idTouristPack) throws SQLException;

        void deleteTransportServiceTouristPackByIdTouristPack(int idTouristPack) throws SQLException;

        void deleteTransportServiceTouristPackByIds(int idTouristPack, int idTransportService) throws SQLException;
}
