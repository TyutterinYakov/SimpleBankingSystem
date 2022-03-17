package banking.api.model;


import java.math.BigDecimal;

public class TransactionalModel {

    //Так много полей, чтобы перевод можно было производить со счета на счет или от клиента клиента(но у клиентов должны
    // быть указаны основые счета mainBill = true!!!! Больше одного основного счета быть не может, поэтому при исправлениях
    // в бд будьте осторожны)))

    private BigDecimal summ;
    private Long billSenderId;
    private Long billRecipientId;
    private Long clientSenderId;
    private Long clientRecipientId;

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public Long getBillSenderId() {
        return billSenderId;
    }

    public void setBillSenderId(Long billSenderId) {
        this.billSenderId = billSenderId;
    }

    public Long getBillRecipientId() {
        return billRecipientId;
    }

    public void setBillRecipientId(Long billRecipientId) {
        this.billRecipientId = billRecipientId;
    }

    public Long getClientSenderId() {
        return clientSenderId;
    }

    public void setClientSenderId(Long clientSenderId) {
        this.clientSenderId = clientSenderId;
    }

    public Long getClientRecipientId() {
        return clientRecipientId;
    }

    public void setClientRecipientId(Long clientRecipientId) {
        this.clientRecipientId = clientRecipientId;
    }
}
