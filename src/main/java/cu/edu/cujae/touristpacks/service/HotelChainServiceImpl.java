package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.core.service.IHotelChainService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelChainServiceImpl implements IHotelChainService {

    @Override
    public List<HotelChainDto> getHotelChains() {
        List<HotelChainDto> hotelChains = new ArrayList<>();
        hotelChains.add(new HotelChainDto(1, "Royalton Resorts"));
        hotelChains.add(new HotelChainDto(2, "Gran Caribe"));
        return hotelChains;
    }

    @Override
    public HotelChainDto getHotelChainById(int hotelChainId) {
        return getHotelChains().stream().filter(r -> r.getIdHotelChain() == hotelChainId).findFirst().get();
    }

    @Override
    public void createHotelChain(HotelChainDto hotelChain) {

    }

    @Override
    public void updateHotelChain(HotelChainDto hotelChain) {

    }

    @Override
    public void deleteHotelChain(int id) {

    }

    @Override
    public HotelChainDto getHotelChainByName(String hotelChainName) {
        return getHotelChains().stream().filter(r -> r.getHotelChainName().equals(hotelChainName)).findFirst().get();
    }
}
