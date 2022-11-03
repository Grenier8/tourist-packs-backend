package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelModalityDto;

public interface IHotelModalityService {
    List<HotelModalityDto> getHotelModalities();

    HotelModalityDto getHotelModalityById(int hotelModalityId);

    HotelModalityDto getHotelModalityByName(String hotelModalityName);

    void createHotelModality(HotelModalityDto hotelModality);

    void updateHotelModality(HotelModalityDto hotelModality);

    void deleteHotelModality(int idHotelModality);
}
