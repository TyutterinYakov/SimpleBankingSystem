package banking.store.entity;

import banking.api.model.ClientModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String lastName;
    private String firstName;
    private String middleName;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    private List<BillEntity> bills = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clientSender")
    private List<TransactionalEntity> senderTransactions = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clientRecipient")
    private List<TransactionalEntity> recipientTransactions = new ArrayList<>();

    public ClientEntity() {
    }

    public ClientEntity(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<BillEntity> getBills() {
        return bills;
    }

    public void setBills(List<BillEntity> bills) {
        this.bills = bills;
    }

    public List<TransactionalEntity> getSenderTransactions() {
        return senderTransactions;
    }

    public void setSenderTransactions(List<TransactionalEntity> senderTransactions) {
        this.senderTransactions = senderTransactions;
    }

    public List<TransactionalEntity> getRecipientTransactions() {
        return recipientTransactions;
    }

    public void setRecipientTransactions(List<TransactionalEntity> recipientTransactions) {
        this.recipientTransactions = recipientTransactions;
    }

}
