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
    private BigDecimal balance = new BigDecimal(0);
    private String name;
    private boolean mainBill = false;
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
    private ClientEntity client;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billRecipient")
    private List<TransactionalEntity> transactionalSenders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billRecipient")
    private List<TransactionalEntity> transactionalRecipients = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bill")
    private List<WithdrawEntity> withdraws = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bill")
    private List<DepositEntity> deposits = new ArrayList<>();


    public BillEntity() {
    }

    public BillEntity(BillType type, String name, ClientEntity client) {
        this.type = type;
        this.name = name;
        this.client = client;
    }

    public List<WithdrawEntity> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(List<WithdrawEntity> withdraws) {
        this.withdraws = withdraws;
    }

    public List<DepositEntity> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<DepositEntity> deposits) {
        this.deposits = deposits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public boolean isMainBill() {
        return mainBill;
    }

    public void setMainBill(boolean mainBill) {
        this.mainBill = mainBill;
    }
}
