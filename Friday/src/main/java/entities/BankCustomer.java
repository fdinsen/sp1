package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "BankCustomer.deleteAllRows", query = "DELETE from BankCustomer")
public class BankCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String accountNumber;
    private double balance;
    private int customerRating;
    private String internalInfo;
    
    public BankCustomer() {
    }

    public BankCustomer(String firstName, String lastName, String accountNumber, double balance, int customerRating, String internalInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerRating = customerRating;
        this.internalInfo = internalInfo;
    }

    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(int customerRating) {
        this.customerRating = customerRating;
    }

    public String getInternalInfo() {
        return internalInfo;
    }

    public void setInternalInfo(String internalInfo) {
        this.internalInfo = internalInfo;
    }
    
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj instanceof BankCustomer) {
            BankCustomer other = (BankCustomer) obj;
            return (this.accountNumber.equals(other.accountNumber)  && 
                    this.balance == other.balance &&
                    this.customerRating == other.customerRating &&
                    this.firstName.equals(other.firstName) &&
                    this.lastName.equals(other.lastName) &&
                    this.internalInfo.equals(other.internalInfo) );
        }
        return false;
    }
}
