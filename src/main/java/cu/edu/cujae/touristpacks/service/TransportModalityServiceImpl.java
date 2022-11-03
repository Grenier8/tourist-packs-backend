package cu.edu.cujae.touristpacks.service;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.core.service.ITransportModalityService;

import org.springframework.stereotype.Service;

@Service
public class TransportModalityServiceImpl implements ITransportModalityService {

    @Override
    public List<TransportModalityDto> getTransportModalities() {
        List<TransportModalityDto> list = new ArrayList<>();
        list.add(new TransportModalityDto(1, "Tm1"));
        list.add(new TransportModalityDto(2, "Tm2"));

        return list;
    }

    @Override
    public TransportModalityDto getTransportModalityById(int transportModalityId) {
        return getTransportModalities().stream().filter(r -> r.getIdTransportModality() == transportModalityId)
                .findFirst().get();
    }

    @Override
    public TransportModalityDto getTransportModalityByName(String transportModalityName) {
        return getTransportModalities().stream().filter(r -> r.getTransportModalityName().equals(transportModalityName))
                .findFirst().get();
    }

    @Override
    public void createTransportModality(TransportModalityDto transportModality) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTransportModality(TransportModalityDto transportModality) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransportModality(int idTransportModality) {
        // TODO Auto-generated method stub

    }

}
