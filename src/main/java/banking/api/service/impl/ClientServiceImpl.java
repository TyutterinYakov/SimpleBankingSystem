package banking.api.service.impl;

import banking.api.dto.ClientDto;
import banking.api.dto.factory.ClientDtoFactory;
import banking.api.exception.NotFoundException;
import banking.api.model.ClientModel;
import banking.api.service.ClientService;
import banking.store.entity.ClientEntity;
import banking.store.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientDao;
    private final ClientDtoFactory clientDtoFactory;

    @Autowired
    public ClientServiceImpl(ClientRepository clientDao, ClientDtoFactory clientDtoFactory) {
        this.clientDao = clientDao;
        this.clientDtoFactory = clientDtoFactory;
    }

    @Override
    public ClientDto createClient(ClientModel newClient) {
        return clientDtoFactory.createClientDto(
                clientDao.saveAndFlush(
                new ClientEntity(
                        newClient.getLastName(),
                        newClient.getFirstName(),
                        newClient.getMiddleName()))
        );
    }

    @Override
    public ClientDto getClient(Long clientId) {
        return clientDtoFactory
                .createClientDto(
                        findClientById(clientId));
    }

    @Override
    public void deleteClient(Long clientId) {
        clientDao.deleteById(findClientById(clientId).getClientId());
    }

    @Override
    public List<ClientDto> getListClient() {
        return clientDtoFactory
                .createListClientDto(clientDao.findAll());
    }

    @Override
    @Transactional
    public ClientDto updateClient(Long clientId, ClientModel updateClient) {
        ClientEntity client = findClientById(clientId);
        client.setFirstName(updateClient.getFirstName());
        client.setLastName(updateClient.getLastName());
        client.setMiddleName(updateClient.getMiddleName());
        return clientDtoFactory.createClientDto(client);
    }


    private ClientEntity findClientById(Long clientId){
        return clientDao.findById(clientId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Клиент с идентификатором \"%s\" не найден",
                                clientId)));
    }


}
