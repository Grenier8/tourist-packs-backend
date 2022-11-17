package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ProviderDto;

public interface IProviderService {
    List<ProviderDto> getProviders() throws SQLException;

    ProviderDto getProviderById(int providerId) throws SQLException;

    ProviderDto getProviderByName(String providerName) throws SQLException;

    void createProvider(ProviderDto provider) throws SQLException;

    void updateProvider(ProviderDto provider) throws SQLException;

    void deleteProvider(int id) throws SQLException;
}
