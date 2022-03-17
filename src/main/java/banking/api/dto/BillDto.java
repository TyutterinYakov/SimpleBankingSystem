package banking.api.dto;

import banking.store.entity.BillType;

import java.math.BigDecimal;

public class BillDto {

    private Long billId;
    private String name;
    private BillType type;
    private BigDecimal balance;
    private ClientDto clientDto;

    public BillDto() {
    }

    public BillDto(Long billId, String name, BillType type, BigDecimal balance, ClientDto clientDto) {
        this.billId = billId;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.clientDto = clientDto;
    }


    public ClientDto getClientDto() {
        return clientDto;
    }

    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
