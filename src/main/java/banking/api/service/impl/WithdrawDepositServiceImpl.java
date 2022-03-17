package banking.api.service.impl;

import banking.api.dto.DepositDto;
import banking.api.dto.WithdrawDto;
import banking.api.dto.factory.DepositDtoFactory;
import banking.api.dto.factory.WithdrawDtoFactory;
import banking.api.exception.NotFoundException;
import banking.api.service.WithdrawDepositService;
import banking.store.entity.BillEntity;
import banking.store.entity.DepositEntity;
import banking.store.entity.OperationStatus;
import banking.store.entity.WithdrawEntity;
import banking.store.repository.BillRepository;
import banking.store.repository.DepositRepository;
import banking.store.repository.WithdrawRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class WithdrawDepositServiceImpl implements WithdrawDepositService {

    private final BillRepository billDao;
    private final DepositDtoFactory depositDtoFactory;
    private final WithdrawDtoFactory withdrawDtoFactory;
    private final WithdrawRepository withdrawRepository;
    private final DepositRepository depositRepository;

    public WithdrawDepositServiceImpl(BillRepository billDao, DepositDtoFactory depositDtoFactory,
                                      WithdrawDtoFactory withdrawDtoFactory, WithdrawRepository withdrawRepository,
                                      DepositRepository depositRepository) {
        this.billDao = billDao;
        this.depositDtoFactory = depositDtoFactory;
        this.withdrawDtoFactory = withdrawDtoFactory;
        this.withdrawRepository = withdrawRepository;
        this.depositRepository = depositRepository;
    }

    @Override
    public WithdrawDto withdrawMoney(Long billId, BigDecimal summ) {
        BillEntity billSend = findBillById(billId);
        if(billSend.getBalance().compareTo(summ)!=-1) {
            billSend.setBalance(billSend.getBalance().subtract(summ));
            return generateReturnWithdrawDto(summ, billSend, OperationStatus.OKEY);
        } else {
            return generateReturnWithdrawDto(summ, billSend, OperationStatus.BAD);
        }
    }

    @Override
    @Transactional
    public DepositDto depositMoney(Long billId, BigDecimal summ) {
        BillEntity billRec =  findBillById(billId);
        billRec.setBalance(billRec.getBalance().add(summ));
        return depositDtoFactory
                .createDepositDto(
                        depositRepository.saveAndFlush(
                            new DepositEntity(
                                    summ,
                                    OperationStatus.OKEY,billRec)));
    }



    private BillEntity findBillById(Long billId){
        return billDao.findById(billId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Счет с идентификатором \"%s\" не найден",
                                billId)));
    }

    private WithdrawDto generateReturnWithdrawDto(BigDecimal summ, BillEntity billSend, OperationStatus status){
        return withdrawDtoFactory.createWithdrawDto(
                withdrawRepository.saveAndFlush(
                        new WithdrawEntity(summ, status,billSend)));
    }

}
