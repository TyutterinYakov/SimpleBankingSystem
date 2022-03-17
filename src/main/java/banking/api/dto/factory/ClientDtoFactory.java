package banking.api.dto.factory;

import banking.api.dto.ClientDto;
import banking.store.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientDtoFactory {

    public ClientDto createClientDto(ClientEntity client){
        return new ClientDto(
                client.getClientId(),
                client.getLastName(),
                client.getFirstName(),
                client.getMiddleName());
    }

    public List<ClientDto> createListClientDto(List<ClientEntity> clients){
        return clients
                .stream()
                .map(this::createClientDto)
                .collect(Collectors.toList());
    }
}
