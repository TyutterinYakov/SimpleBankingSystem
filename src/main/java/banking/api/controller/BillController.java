package banking.api.controller;

import banking.api.dto.BillDto;
import banking.api.model.BillModel;
import banking.api.model.ClientModel;
import banking.api.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public static final String GET_LIST_BILL_BY_CLIENT = "/api/clients/{clientId}/bills";
    public static final String DELETE_BILL = "/api/clients/bills/{billId}";
    public static final String CREATE_BILL = "/api/clients/{clientId}/bills";
    public static final String UPDATE_MAIN_BILL = "/api/clients/{clientId}/bills/{billId}";




    @GetMapping(GET_LIST_BILL_BY_CLIENT)
    public ResponseEntity<List<BillDto>> getListBill(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok(billService.getListBillByClient(clientId));
    }

    @PostMapping(CREATE_BILL)
    public ResponseEntity<?> createBill(
            @PathVariable("clientId") Long clientId,
            @RequestBody BillModel billModel){
        return new ResponseEntity<>(billService.createBill(clientId, billModel), HttpStatus.CREATED);
    }

    @DeleteMapping(DELETE_BILL)
    public ResponseEntity<?> deleteBill(@PathVariable("billId") Long billId){
        billService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(UPDATE_MAIN_BILL)
    public ResponseEntity<?> updateMainBill(
            @PathVariable("billId") Long billId,
            @PathVariable("clientId") Long clientId){
        return new ResponseEntity<>(
                billService.updateMainBill(billId, clientId),HttpStatus.ACCEPTED);
    }


}
