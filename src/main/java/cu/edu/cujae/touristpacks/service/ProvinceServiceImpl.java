package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.core.service.IProvinceService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceServiceImpl implements IProvinceService {

    @Override
    public List<ProvinceDto> getProvinces() {
        List<ProvinceDto> provinces = new ArrayList<>();
        provinces.add(new ProvinceDto(1, "Villa Clara"));
        provinces.add(new ProvinceDto(2, "La Habana"));
        return provinces;
    }

    @Override
    public ProvinceDto getProvinceById(int provinceId) {
        return getProvinces().stream().filter(r -> r.getIdProvince() == provinceId).findFirst().get();
    }

    @Override
    public void createProvince(ProvinceDto province) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateProvince(ProvinceDto province) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteProvince(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public ProvinceDto getProvinceByName(String provinceName) {
        return getProvinces().stream().filter(r -> r.getProvinceName().equals(provinceName)).findFirst().get();
    }

}
