package com.gennadii.overchuk.depositstore;

import javax.xml.bind.annotation.*;

/**
 * Created by Tehnik on 23.03.2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Deposit {
    @XmlElement
    private String name;
    @XmlElement
    private String country;
    @XmlElement
    private Type type;
    @XmlElement
    private String depositor;
    @XmlElement
    private Long accountId;
    @XmlElement
    private Double amountOnDeposit;
    @XmlElement
    private Integer profitability;
    @XmlElement
    private Integer timeConstraints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(Double amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public Integer getProfitability() {
        return profitability;
    }

    public void setProfitability(Integer profitability) {
        this.profitability = profitability;
    }

    public Integer getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(Integer timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (name != null ? !name.equals(deposit.name) : deposit.name != null) return false;
        if (country != null ? !country.equals(deposit.country) : deposit.country != null) return false;
        if (type != deposit.type) return false;
        if (depositor != null ? !depositor.equals(deposit.depositor) : deposit.depositor != null) return false;
        if (accountId != null ? !accountId.equals(deposit.accountId) : deposit.accountId != null) return false;
        if (amountOnDeposit != null ? !amountOnDeposit.equals(deposit.amountOnDeposit) : deposit.amountOnDeposit != null)
            return false;
        if (profitability != null ? !profitability.equals(deposit.profitability) : deposit.profitability != null)
            return false;
        return timeConstraints != null ? timeConstraints.equals(deposit.timeConstraints) : deposit.timeConstraints == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (depositor != null ? depositor.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (amountOnDeposit != null ? amountOnDeposit.hashCode() : 0);
        result = 31 * result + (profitability != null ? profitability.hashCode() : 0);
        result = 31 * result + (timeConstraints != null ? timeConstraints.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\n\nDeposit{" +
                "\n   name='" + name + '\'' +
                ",\n   country='" + country + '\'' +
                ",\n   depositor='" + depositor + '\'' +
                ",\n   accountId=" + accountId +
                ",\n   amountOnDeposit=" + amountOnDeposit +
                ",\n   profitability=" + profitability +
                ",\n   timeConstraints=" + timeConstraints +
                "}";
    }
}
