package banking.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transactional")
public class TransactionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionalId;
    @Enumerated(value=EnumType.STRING)
    private OperationStatus status;
    private BigDecimal summ;
    private LocalDateTime dateOperation = LocalDateTime.now();
    @ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
    private BillEntity billSender;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
    private BillEntity billRecipient;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
    private ClientEntity clientSender;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
    private ClientEntity clientRecipient;


    public TransactionalEntity() {
    }

    public TransactionalEntity(BigDecimal summ, BillEntity billSender, BillEntity billRecipient,OperationStatus status) {
        this.summ = summ;
        this.billSender = billSender;
        this.billRecipient = billRecipient;
        this.status = status;
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

    public BillEntity getBillSender() {
        return billSender;
    }

    public void setBillSender(BillEntity billSender) {
        this.billSender = billSender;
    }

    public BillEntity getBillRecipient() {
        return billRecipient;
    }

    public void setBillRecipient(BillEntity billRecipient) {
        this.billRecipient = billRecipient;
    }

    public ClientEntity getClientSender() {
        return clientSender;
    }

    public void setClientSender(ClientEntity clientSender) {
        this.clientSender = clientSender;
    }

    public ClientEntity getClientRecipient() {
        return clientRecipient;
    }

    public void setClientRecipient(ClientEntity clientRecipient) {
        this.clientRecipient = clientRecipient;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }
}
