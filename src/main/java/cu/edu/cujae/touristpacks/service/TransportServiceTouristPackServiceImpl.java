package cu.edu.cujae.touristpacks.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceTouristPackDto;
import cu.edu.cujae.touristpacks.core.service.ITouristPackService;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceService;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceTouristPackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceTouristPackServiceImpl implements ITransportServiceTouristPackService {

    @Autowired
    ITouristPackService touristPackService;

    @Autowired
    ITransportServiceService transportServiceService;

    @Override
    public List<TransportServiceTouristPackDto> getTransportServiceTouristPacks() throws SQLException {
        List<TransportServiceTouristPackDto> list = new ArrayList<>();

        TransportServiceDto transportService1 = transportServiceService.getTransportServices().get(0);
        TransportServiceDto transportService2 = transportServiceService.getTransportServices().get(1);

        TouristPackDto touristPack1 = touristPackService.getTouristPacks().get(0);
        TouristPackDto touristPack2 = touristPackService.getTouristPacks().get(1);

        list.add(new TransportServiceTouristPackDto(1, transportService1, touristPack1));
        list.add(new TransportServiceTouristPackDto(2, transportService2, touristPack2));

        return list;
    }

    @Override
    public TransportServiceTouristPackDto getTransportServiceTouristPackById(int transportServiceTouristPackId)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack)
            throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack)
            throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransportServiceTouristPack(int idTransportServiceTouristPack) throws SQLException {
        // TODO Auto-generated method stub

    }

}
