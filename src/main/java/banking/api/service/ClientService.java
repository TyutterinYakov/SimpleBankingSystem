package banking.api.service;

import banking.api.dto.ClientDto;
import banking.api.model.ClientModel;

import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientModel newClient);
    ClientDto getClient(Long clientId);
    void deleteClient(Long clientId);
    List<ClientDto> getListClient();
    ClientDto updateClient(Long clientId, ClientModel updateClient);
}
