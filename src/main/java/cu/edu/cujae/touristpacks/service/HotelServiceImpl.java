package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.core.service.IHotelChainService;
import cu.edu.cujae.touristpacks.core.service.IHotelService;
import cu.edu.cujae.touristpacks.core.service.IProvinceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    IHotelChainService hotelChainService;

    @Autowired
    IProvinceService provinceService;

    @Override
    public List<HotelDto> getHotels() throws SQLException {
        List<HotelDto> hotels = new ArrayList<>();

        HotelChainDto hotelChain1 = hotelChainService.getHotelChains().get(0);
        HotelChainDto hotelChain2 = hotelChainService.getHotelChains().get(1);

        ProvinceDto province1 = provinceService.getProvinces().get(0);
        ProvinceDto province2 = provinceService.getProvinces().get(1);

        hotels.add(new HotelDto(1, "Royalton Cayo Santa Maria", "Caibarien Villa Clara, 52600", 5, "042350900",
                "040350950", "cayosantamaria@royalton.com", 5.4, 2, 122, 1, "cayo",
                hotelChain1, province1));
        hotels.add(new HotelDto(2, "Hotel Nacional de Cuba", "Calle 21 y O, 10400", 5, "78363564", "78652558",
                "hotelnacional@grancaribe.com", 16, 0, 426, 2, "ciudad",
                hotelChain2, province2));
        return hotels;
    }

    @Override
    public HotelDto getHotelById(int hotelId) throws SQLException {
        return getHotels().stream().filter(r -> r.getIdHotel() == hotelId).findFirst().get();
    }

    @Override
    public void createHotel(HotelDto hotel) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateHotel(HotelDto hotel) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteHotel(int id) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public HotelDto getHotelByName(String hotelName) throws SQLException {
        return getHotels().stream().filter(r -> r.getHotelName().equals(hotelName)).findFirst().get();
    }

}
