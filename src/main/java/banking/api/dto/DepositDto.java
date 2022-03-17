package banking.api.dto;

import java.math.BigDecimal;

public class DepositDto {

    private Long depositId;
    private BigDecimal summ;
    private BillDto billDto;

    public DepositDto() {
    }

    public DepositDto(Long depositId, BigDecimal summ, BillDto billDto) {
        this.depositId = depositId;
        this.summ = summ;
        this.billDto = billDto;
    }

    public Long getDepositId() {
        return depositId;
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

    public BillDto getBillDto() {
        return billDto;
    }

    public void setBillDto(BillDto billDto) {
        this.billDto = billDto;
    }
}
