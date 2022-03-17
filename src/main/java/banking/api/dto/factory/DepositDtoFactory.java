package banking.api.dto.factory;

import banking.api.dto.DepositDto;
import banking.store.entity.DepositEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepositDtoFactory {
    private final BillDtoFactory billDtoFactory;

    public DepositDtoFactory(BillDtoFactory billDtoFactory) {
        this.billDtoFactory = billDtoFactory;
    }

    public DepositDto createDepositDto(DepositEntity entity){
        return new DepositDto(
                entity.getDepositId(),
                entity.getSumm(),
                billDtoFactory
                        .createBillDto(
                                entity.getBill()));
    }

    public List<DepositDto> createListDepositDto(List<DepositEntity> list){
        return list
                .stream()
                .map(this::createDepositDto)
                .collect(Collectors.toList());
    }
}
