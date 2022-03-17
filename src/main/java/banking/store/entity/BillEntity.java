package banking.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="bill")
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;
    private BillType type;
    private BigDecimal balance;
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
    private ClientEntity client;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billRecipient")
    private List<TransactionalEntity> transactionalSenders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billRecipient")
    private List<TransactionalEntity> transactionalRecipients = new ArrayList<>();



    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public List<TransactionalEntity> getTransactionalSenders() {
        return transactionalSenders;
    }

    public void setTransactionalSenders(List<TransactionalEntity> transactionalSenders) {
        this.transactionalSenders = transactionalSenders;
    }

    public List<TransactionalEntity> getTransactionalRecipients() {
        return transactionalRecipients;
    }

    public void setTransactionalRecipients(List<TransactionalEntity> transactionalRecipients) {
        this.transactionalRecipients = transactionalRecipients;
    }
}
