package banking.api.service;

import banking.api.dto.DepositDto;
import banking.api.dto.WithdrawDto;

import java.math.BigDecimal;

public interface WithdrawDepositService {
    WithdrawDto withdrawMoney(Long billId, BigDecimal summ);
    DepositDto depositMoney(Long billId, BigDecimal summ);
}
