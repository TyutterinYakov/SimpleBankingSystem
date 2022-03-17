package banking.api.service.impl;

import banking.api.dto.TransactionalDto;
import banking.api.dto.factory.TransactionalDtoFactory;
import banking.api.exception.BadRequestException;
import banking.api.exception.NotFoundException;
import banking.api.model.TransactionalModel;
import banking.api.service.TransactionalService;
import banking.store.entity.BillEntity;
import banking.store.entity.ClientEntity;
import banking.store.entity.OperationStatus;
import banking.store.entity.TransactionalEntity;
import banking.store.repository.BillRepository;
import banking.store.repository.ClientRepository;
import banking.store.repository.TransactionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionalServiceImpl implements TransactionalService {

    private final TransactionalRepository transactionalDao;
    private final ClientRepository clientDao;
    private final BillRepository billDao;
    private final TransactionalDtoFactory transactionalDtoFactory;


    @Autowired
    public TransactionalServiceImpl(TransactionalRepository transactionalDao,
                                    ClientRepository clientDao, BillRepository billDao,
                                    TransactionalDtoFactory transactionalDtoFactory) {
        this.transactionalDao = transactionalDao;
        this.clientDao = clientDao;
        this.billDao = billDao;
        this.transactionalDtoFactory = transactionalDtoFactory;
    }

    @Override
    public List<TransactionalDto> getListRecipientTransactionalByClient(Long clientId) {
        return transactionalDtoFactory
                    .createListTransactionalDto(
                            findClientById(clientId).getRecipientTransactions());
    }

    @Override
    public List<TransactionalDto> getListSenderTransactionalByClient(Long clientId) {
        return transactionalDtoFactory
                .createListTransactionalDto(
                        findClientById(clientId).getSenderTransactions());
    }

    @Override
    public List<TransactionalDto> getListSenderTransactionalByBill(Long billId) {
        return transactionalDtoFactory
                .createListTransactionalDto(
                        findBillById(billId).getTransactionalSenders());
    }

    @Override
    public List<TransactionalDto> getListRecipientTransactionalByBill(Long billId) {
        return transactionalDtoFactory
                .createListTransactionalDto(
                        findBillById(billId).getTransactionalRecipients());
    }


    @Override
    @Transactional
    public TransactionalDto createTransactional(TransactionalModel transactionalModel) {
        BigDecimal summOper = transactionalModel.getSumm();
        if(transactionalModel.getBillSenderId()!=null&&transactionalModel.getBillRecipientId()!=null){
            BillEntity billRec =  findBillById(transactionalModel.getBillRecipientId());
            BillEntity billSend = findBillById(transactionalModel.getBillSenderId());
            createTransactionalBill(summOper, billSend, billRec);
        } else if(transactionalModel.getClientRecipientId()!=null&&transactionalModel.getClientSenderId()!=null){
            ClientEntity clientRec = findClientById(transactionalModel.getClientRecipientId());
            ClientEntity clientSen = findClientById(transactionalModel.getClientSenderId());
            BillEntity billRec = findMainBillByClient(clientRec);
            BillEntity billSend = findMainBillByClient(clientSen);
            createTransactionalBill(summOper, billSend, billRec);
        }
        throw new BadRequestException("Не указаны счета/клиенты");
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

    private BillEntity findMainBillByClient(ClientEntity client){
        return billDao.findByMainBillAndClient(true, client).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "У клиента \"%s\" отсутвует основной счет",
                                client.getClientId())));
    }

    @Transactional
    private TransactionalDto createTransactionalBill(BigDecimal summOper, BillEntity billSend, BillEntity billRec){
        if(summOper.compareTo(billSend.getBalance())!=-1) {
            billSend.setBalance(billSend.getBalance().subtract(summOper));
            billRec.setBalance(billRec.getBalance().add(summOper));
            return generateReturnTransactionalDto(OperationStatus.OKEY, summOper, billSend, billRec);
        } else {
            return generateReturnTransactionalDto(OperationStatus.BAD, summOper, billSend, billRec);
        }
    }


    private TransactionalDto generateReturnTransactionalDto(OperationStatus status, BigDecimal summOper,
                                                            BillEntity billSend, BillEntity billRec){
        return transactionalDtoFactory
                .createTransactinalDto(
                        transactionalDao.saveAndFlush(
                                new TransactionalEntity(
                                        summOper,
                                        billSend,
                                        billRec,
                                        status)));
    }


}
