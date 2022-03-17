package banking.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="deposit")
public class DepositEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;
    private BigDecimal summ;
    @Enumerated(value= EnumType.STRING)
    private OperationStatus status;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch= FetchType.EAGER)
    private BillEntity bill;

    public Long getDepositId() {
        return depositId;
    }

    public DepositEntity() {
    }

    public DepositEntity(BigDecimal summ, OperationStatus status, BillEntity bill) {
        this.summ = summ;
        this.status = status;
        this.bill = bill;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
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
