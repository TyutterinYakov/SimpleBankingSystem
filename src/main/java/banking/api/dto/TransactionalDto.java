package banking.api.dto;

import banking.store.entity.BillEntity;
import banking.store.entity.ClientEntity;
import banking.store.entity.OperationStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionalDto {
    private Long transactionalId;
    private OperationStatus status;
    private BigDecimal summ;
    private LocalDateTime dateOperation;
    private BillDto billSender;
    private BillDto billRecipient;
    private ClientDto clientSender;
    private ClientDto clientRecipient;

    public TransactionalDto() {
    }

    public TransactionalDto(Long transactionalId, OperationStatus status, BigDecimal summ,
                            LocalDateTime dateOperation, BillDto billSender,
                            BillDto billRecipient, ClientDto clientSender, ClientDto clientRecipient) {
        this.transactionalId = transactionalId;
        this.status = status;
        this.summ = summ;
        this.dateOperation = dateOperation;
        this.billSender = billSender;
        this.billRecipient = billRecipient;
        this.clientSender = clientSender;
        this.clientRecipient = clientRecipient;
    }


    public Long getTransactionalId() {
        return transactionalId;
    }

    public void setTransactionalId(Long transactionalId) {
        this.transactionalId = transactionalId;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public BillDto getBillSender() {
        return billSender;
    }

    public void setBillSender(BillDto billSender) {
        this.billSender = billSender;
    }

    public BillDto getBillRecipient() {
        return billRecipient;
    }

    public void setBillRecipient(BillDto billRecipient) {
        this.billRecipient = billRecipient;
    }

    public ClientDto getClientSender() {
        return clientSender;
    }

    public void setClientSender(ClientDto clientSender) {
        this.clientSender = clientSender;
    }

    public ClientDto getClientRecipient() {
        return clientRecipient;
    }

    public void setClientRecipient(ClientDto clientRecipient) {
        this.clientRecipient = clientRecipient;
    }
}
