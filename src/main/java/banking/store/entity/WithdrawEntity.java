package banking.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "withdraw")
public class WithdrawEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawId;
    private BigDecimal summ;
    @Enumerated(value=EnumType.STRING)
    private OperationStatus status;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch= FetchType.EAGER)
    private BillEntity bill;

    public WithdrawEntity() {
    }

    public WithdrawEntity(BigDecimal summ, OperationStatus status, BillEntity bill) {
        this.summ = summ;
        this.status = status;
        this.bill = bill;
    }

    public Long getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Long withdrawId) {
        this.withdrawId = withdrawId;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }
}
