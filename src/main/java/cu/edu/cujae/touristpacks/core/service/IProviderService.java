package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ProviderDto;

public interface IProviderService {
    List<ProviderDto> getProviders();

    ProviderDto getProviderById(int providerId);

    ProviderDto getProviderByName(String providerName);

    void createProvider(ProviderDto provider);

    void updateProvider(ProviderDto provider);

    void deleteProvider(int id);
}
