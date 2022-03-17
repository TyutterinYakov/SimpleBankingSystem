package banking.api.service;

import banking.api.dto.BillDto;
import banking.api.model.BillModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BillService {
    List<BillDto> getListBillByClient(Long clientId);
    void deleteBill(Long billId);
    BillDto createBill(Long clientId, BillModel billModel);
    BillDto updateMainBill(Long billId, Long clientId);
}
