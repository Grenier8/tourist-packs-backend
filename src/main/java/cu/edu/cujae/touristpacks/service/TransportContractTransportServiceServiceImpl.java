package cu.edu.cujae.touristpacks.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportContractDto;
import cu.edu.cujae.touristpacks.core.dto.TransportContractTransportServiceDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.core.service.ITransportContractService;
import cu.edu.cujae.touristpacks.core.service.ITransportContractTransportServiceService;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportContractTransportServiceServiceImpl implements ITransportContractTransportServiceService {

    @Autowired
    private ITransportContractService tContractservice;

    @Autowired
    private ITransportServiceService tServiceservice;

    @Override
    public List<TransportContractTransportServiceDto> getTransportContractTransportServices() throws SQLException {
        List<TransportContractDto> tContract = tContractservice.getTransportContracts();
        List<TransportServiceDto> tService = tServiceservice.getTransportServices();

        List<TransportContractTransportServiceDto> contracts = new ArrayList<>();
        contracts.add(new TransportContractTransportServiceDto(1, tContract.get(0), tService.get(0)));
        contracts.add(new TransportContractTransportServiceDto(2, tContract.get(1), tService.get(1)));
        return contracts;
    }

    @Override
    public TransportContractTransportServiceDto getTransportContractTransportServiceById(
            int transportContractTransportServiceId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTransportContractTransportService(
            TransportContractTransportServiceDto transportContractTransportService) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTransportContractTransportService(
            TransportContractTransportServiceDto transportContractTransportService) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransportContractTransportService(int idTransportContractTransportService) throws SQLException {
        // TODO Auto-generated method stub

    }

}
