package banking.api.service;

import banking.api.dto.TransactionalDto;
import banking.api.model.TransactionalModel;

import java.util.List;

public interface TransactionalService {

    List<TransactionalDto> getListRecipientTransactionalByClient(Long clientId);
    List<TransactionalDto> getListSenderTransactionalByClient(Long clientId);
    List<TransactionalDto> getListSenderTransactionalByBill(Long billId);
    List<TransactionalDto> getListRecipientTransactionalByBill(Long billId);
    TransactionalDto createTransactional(TransactionalModel transactionalModel);

}
