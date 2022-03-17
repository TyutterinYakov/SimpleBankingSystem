package banking.api.controller;

import banking.api.dto.ClientDto;
import banking.api.model.ClientModel;
import banking.api.service.ClientService;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public static final String CREATE_CLIENT = "/api/clients";
    public static final String GET_CLIENT = "/api/clients/{clientId}";
    public static final String DELETE_CLIENT = "/api/clients/{clientId}";
    public static final String GET_LIST_CLIENT = "/api/clients";
    public static final String UPDATE_CLIENT = "/api/clients/{clientId}";


    @PostMapping(CREATE_CLIENT)
    public ResponseEntity<?> createClient(@RequestBody ClientModel newClient){
        return new ResponseEntity<>(clientService.createClient(newClient), HttpStatus.CREATED);
    }

    @GetMapping(GET_CLIENT)
    public ResponseEntity<ClientDto> getClient(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok(clientService.getClient(clientId));
    }

    @DeleteMapping(DELETE_CLIENT)
    public ResponseEntity<?> deleteClient(@PathVariable("clientId") Long clientId){
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(GET_LIST_CLIENT)
    public ResponseEntity<List<ClientDto>> getListClient(){
        return ResponseEntity.ok(clientService.getListClient());
    }

    @PutMapping(UPDATE_CLIENT)
    public ResponseEntity<?> updateClient(
            @PathVariable("clientId") Long clientId,
            @RequestBody ClientModel updateClient){

        return new ResponseEntity<>(clientService.updateClient(clientId, updateClient),HttpStatus.ACCEPTED);
    }
}
