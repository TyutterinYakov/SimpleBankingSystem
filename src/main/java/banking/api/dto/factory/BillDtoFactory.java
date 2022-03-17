package banking.api.dto.factory;

import banking.api.dto.BillDto;
import banking.store.entity.BillEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BillDtoFactory {

    private final ClientDtoFactory clientDtoFactory;

    public BillDtoFactory(ClientDtoFactory clientDtoFactory) {
        this.clientDtoFactory = clientDtoFactory;
    }

    public BillDto createBillDto(BillEntity entity){
        return new BillDto(
                entity.getBillId(),
                entity.getName(),
                entity.getType(),
                entity.getBalance(),
                clientDtoFactory
                        .createClientDto(
                entity.getClient())
        );
    }

    public List<BillDto> createLisBillDto(List<BillEntity> entities){
        return entities
                .stream()
                .map(this::createBillDto)
                .collect(Collectors.toList());
    }
}
