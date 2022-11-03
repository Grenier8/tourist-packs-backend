package cu.edu.cujae.touristpacks.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.service.IHotelContractService;
import cu.edu.cujae.touristpacks.core.service.IHotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelContractServiceImpl implements IHotelContractService {

    @Autowired
    IHotelService hotelService;

    @Override
    public List<HotelContractDto> getHotelContracts() throws SQLException {
        List<HotelContractDto> list = new ArrayList<>();

        HotelDto hotel1 = hotelService.getHotels().get(0);
        HotelDto hotel2 = hotelService.getHotels().get(1);

        list.add(new HotelContractDto(1, "Contr1", LocalDate.of(2010, 2, 10), LocalDate.of(2010, 2, 10),
                LocalDate.of(2010, 2, 10), "Desc1", hotel1));
        list.add(new HotelContractDto(2, "Contr2", LocalDate.of(2020, 2, 10), LocalDate.of(2020, 2, 10),
                LocalDate.of(2020, 2, 10), "Desc2", hotel2));

        return list;
    }

    @Override
    public HotelContractDto getHotelContractById(int hotelContractId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HotelContractDto getHotelContractByName(String hotelContractName) throws SQLException {
        return getHotelContracts().stream().filter(r -> r.getContractTitle().equals(hotelContractName)).findFirst()
                .get();
    }

    @Override
    public void createHotelContract(HotelContractDto hotelContract) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateHotelContract(HotelContractDto hotelContract) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteHotelContract(int idHotelContract) throws SQLException {
        // TODO Auto-generated method stub

    }

}
