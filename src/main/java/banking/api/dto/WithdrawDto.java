package banking.api.dto;

import java.math.BigDecimal;

public class WithdrawDto {

    private Long withdrawId;
    private BigDecimal summ;
    private BillDto billDto;

    public WithdrawDto() {
    }

    public WithdrawDto(Long withdrawId, BigDecimal summ, BillDto billDto) {
        this.withdrawId = withdrawId;
        this.summ = summ;
        this.billDto = billDto;
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

    public BillDto getBillDto() {
        return billDto;
    }

    public void setBillDto(BillDto billDto) {
        this.billDto = billDto;
    }
}
