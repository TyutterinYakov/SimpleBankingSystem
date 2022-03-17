package banking.api.controller;

import banking.api.service.WithdrawDepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@CrossOrigin("*")
public class WithdrawDepositController {

    private final WithdrawDepositService withdrawDepositService;

    public WithdrawDepositController(WithdrawDepositService withdrawDepositService) {
        this.withdrawDepositService = withdrawDepositService;
    }

    public static final String WITHDRAW_MONEY = "/api/clients/bills/{billId}/withdraw";
    public static final String DEPOSIT_MONEY = "/api/clients/bills/{billId}/deposit";


    public ResponseEntity<?> withdrawMoney(@PathVariable("billId") Long billId, BigDecimal summ){
        return new ResponseEntity<>(withdrawDepositService.withdrawMoney(billId, summ), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> depositMoney(@PathVariable("billId") Long billId, BigDecimal summ){
        return new ResponseEntity<>(withdrawDepositService.depositMoney(billId, summ), HttpStatus.ACCEPTED);
    }

}
