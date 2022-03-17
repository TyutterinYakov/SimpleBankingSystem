package banking.api.dto.factory;

import banking.api.dto.TransactionalDto;
import banking.store.entity.TransactionalEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionalDtoFactory {

    private final BillDtoFactory billDtoFactory;
    private final ClientDtoFactory clientDtoFactory;

    public TransactionalDtoFactory(BillDtoFactory billDtoFactory, ClientDtoFactory clientDtoFactory) {
        this.billDtoFactory = billDtoFactory;
        this.clientDtoFactory = clientDtoFactory;
    }

    public TransactionalDto createTransactinalDto(TransactionalEntity entity){
        return new TransactionalDto(
                entity.getTransactionalId(),
                entity.getStatus(),
                entity.getSumm(),
                entity.getDateOperation(),
                billDtoFactory.createBillDto(
                            entity.getBillSender()),
                billDtoFactory.createBillDto(
                            entity.getBillRecipient()),
                clientDtoFactory.createClientDto(
                            entity.getClientSender()),
                clientDtoFactory.createClientDto(
                            entity.getClientRecipient()));
    }

    public List<TransactionalDto> createListTransactionalDto(List<TransactionalEntity> list){
        return list
                .stream()
                .map(this::createTransactinalDto)
                .collect(Collectors.toList());
    }

}
