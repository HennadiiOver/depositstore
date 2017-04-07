package com.gennadii.overchuk.depositstore;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tehnik on 23.03.2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DepositList {
    @XmlElementWrapper(name = "deposits")
    @XmlElement(name = "depositInfo")
    private List<Deposit> deposits;

    public List<Deposit> getDeposits() {
        if (deposits == null) {
            deposits = new ArrayList<>();
        }
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepositList that = (DepositList) o;

        return deposits != null ? deposits.equals(that.deposits) : that.deposits == null;
    }

    @Override
    public int hashCode() {
        return deposits != null ? deposits.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DepositList{" + "\n" +
                "deposits=" + deposits + "\n" +
                '}';
    }
}
