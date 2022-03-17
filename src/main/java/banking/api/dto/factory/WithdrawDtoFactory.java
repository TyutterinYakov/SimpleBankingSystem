package banking.api.dto.factory;

import banking.api.dto.WithdrawDto;
import banking.store.entity.WithdrawEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WithdrawDtoFactory {

    private final BillDtoFactory billDtoFactory;

    public WithdrawDtoFactory(BillDtoFactory billDtoFactory) {
        this.billDtoFactory = billDtoFactory;
    }

    public WithdrawDto createWithdrawDto(WithdrawEntity entity){
        return new WithdrawDto(
                entity.getWithdrawId(),
                entity.getSumm(),
                billDtoFactory
                        .createBillDto(
                entity.getBill()));
    }

    public List<WithdrawDto> createListWithdrawDto(List<WithdrawEntity> list){
        return list
                .stream()
                .map(this::createWithdrawDto)
                .collect(Collectors.toList());
    }
}
