package banking.api.service.impl;

import banking.api.dto.BillDto;
import banking.api.dto.factory.BillDtoFactory;
import banking.api.exception.NotFoundException;
import banking.api.model.BillModel;
import banking.api.service.BillService;
import banking.store.entity.BillEntity;
import banking.store.entity.ClientEntity;
import banking.store.repository.BillRepository;
import banking.store.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

public class BillServiceImpl implements BillService {

    private final BillRepository billDao;
    private final ClientRepository clientDao;
    private final BillDtoFactory billDtoFactory;

    public BillServiceImpl(BillRepository billDao, ClientRepository clientDao, BillDtoFactory billDtoFactory) {
        this.billDao = billDao;
        this.clientDao = clientDao;
        this.billDtoFactory = billDtoFactory;
    }

    @Override
    public List<BillDto> getListBillByClient(Long clientId) {
        return billDtoFactory
                .createLisBillDto(
                findClientById(clientId)
                        .getBills());
    }

    @Override
    public void deleteBill(Long billId) {
        billDao.deleteById(findBillById(billId).getBillId());
    }

    @Override
    public BillDto createBill(Long clientId, BillModel billModel) {
        ClientEntity client = findClientById(clientId);
        return billDtoFactory.createBillDto(
                new BillEntity(
                        billModel.getType(),
                        billModel.getName(),
                        client));
    }

    @Override
    public BillDto updateMainBill(Long billId, Long clientId) { //Установка нового основного счета, на который будут
        BillEntity bill = findBillById(billId);                 //приходить все средства
        ClientEntity client = findClientById(clientId);
        billDao.findByMainBillAndClient(true, client).ifPresent((b)->{
            b.setMainBill(false);
        });
        bill.setMainBill(true);
        return billDtoFactory.createBillDto(bill);
    }


    private ClientEntity findClientById(Long clientId){
        return clientDao.findById(clientId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Клиент с идентификатором \"%s\" не найден",
                                clientId)));
    }

    private BillEntity findBillById(Long billId){
        return billDao.findById(billId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Счет с идентификатором \"%s\" не найден",
                                billId)));
    }

}
