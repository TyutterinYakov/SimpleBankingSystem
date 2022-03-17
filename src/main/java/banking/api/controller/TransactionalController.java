package banking.api.controller;

import banking.api.dto.TransactionalDto;
import banking.api.model.TransactionalModel;
import banking.api.service.TransactionalService;
import banking.store.entity.TransactionalEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TransactionalController {

    private final TransactionalService transactionalService;

    public TransactionalController(TransactionalService transactionalService) {
        this.transactionalService = transactionalService;
    }

    public static final String LIST_TRANSACTIONAL_RECIPIENT_BY_CLIENT = "/api/clients/{clientId}/bills/transactionals";
    public static final String LIST_TRANSACTIONAL_SENDER_BY_CLIENT = "/api/clients/{clientId}/bills/transactionals";
    public static final String LIST_TRANSACTIONAL_RECIPIENT_BY_BILL = "/api/clients/bills/{billId}/transactionals";
    public static final String LIST_TRANSACTIONAL_SENDER_BY_BILL = "/api/clients/bills/{billId}/transactionals";
    public static final String CREATE_TRANSACTIONAL = "/api/clients/bills/transactionals";


    @GetMapping(LIST_TRANSACTIONAL_RECIPIENT_BY_CLIENT )
    public ResponseEntity<List<TransactionalDto>> getListRecipientTransactionalByClient(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok(transactionalService.getListRecipientTransactionalByClient(clientId));
    }

    @GetMapping(LIST_TRANSACTIONAL_SENDER_BY_CLIENT)
    public ResponseEntity<List<TransactionalDto>> getListSenderTransactionalByClient(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok(transactionalService.getListSenderTransactionalByClient(clientId));
    }

    @GetMapping(LIST_TRANSACTIONAL_SENDER_BY_BILL)
    public ResponseEntity<List<TransactionalDto>> getListTransactionalSenderByBill(@PathVariable("billId") Long billId){
        return ResponseEntity.ok(transactionalService.getListSenderTransactionalByBill(billId));
    }

    @GetMapping(LIST_TRANSACTIONAL_RECIPIENT_BY_BILL)
    public ResponseEntity<List<TransactionalDto>> getListTransactionalRecipientByBill(@PathVariable("billId") Long billId){
        return ResponseEntity.ok(transactionalService.getListRecipientTransactionalByBill(billId));
    }

    @PostMapping(CREATE_TRANSACTIONAL)
    public ResponseEntity<?> createTransactional(@RequestBody TransactionalModel transactionalModel){
        return new ResponseEntity<>(transactionalService.createTransactional(transactionalModel), HttpStatus.CREATED);
    }








}
