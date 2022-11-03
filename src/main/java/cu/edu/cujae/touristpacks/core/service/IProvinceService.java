package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ProvinceDto;

public interface IProvinceService {
    List<ProvinceDto> getProvinces() throws SQLException;

    ProvinceDto getProvinceById(int provinceId) throws SQLException;

    ProvinceDto getProvinceByName(String provinceName) throws SQLException;

    void createProvince(ProvinceDto province) throws SQLException;

    void updateProvince(ProvinceDto province) throws SQLException;

    void deleteProvince(int id) throws SQLException;
}
