package com.gennadii.overchuk.depositstore.bl;

import com.gennadii.overchuk.depositstore.Deposit;
import com.gennadii.overchuk.depositstore.DepositList;
import com.gennadii.overchuk.depositstore.Type;
import com.gennadii.overchuk.depositstore.storage.DepositStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tehnik on 26.03.2017.
 */
public class Service {
    public DepositList list() {
        DepositStorage storage = new DepositStorage();
        return storage.read();
    }

    public Double sum() {
        DepositStorage storage = new DepositStorage();
        DepositList depositList = storage.read();
        List<Deposit> list = depositList.getDeposits();
        double sumAmountOnDeposit = 0;
        for (Deposit deposit : list) {
            Double amountOnDeposit = deposit.getAmountOnDeposit();
            sumAmountOnDeposit += amountOnDeposit;
        }
        return sumAmountOnDeposit;
    }

    public Integer count() {
        DepositStorage storage = new DepositStorage();
        DepositList depositList = storage.read();
        List<Deposit> list = depositList.getDeposits();
        return list.size();
    }

    public Deposit accountInfo(Long accountId) {
        DepositStorage storage = new DepositStorage();
        DepositList depositList = storage.read();
        List<Deposit> list = depositList.getDeposits();
        for (Deposit deposit : list) {
            if (accountId.equals(deposit.getAccountId())) {
                return deposit;
            }
        }
        return new Deposit();
    }

    public DepositList infoDepositor(String depositor) {
        DepositStorage storage = new DepositStorage();
        DepositList depositList = storage.read();
        List<Deposit> list = depositList.getDeposits();
        List<Deposit> filterDeposit = new ArrayList<Deposit>();
        for (Deposit deposit : list) {
            if (depositor.equals(deposit.getDepositor())) {
                filterDeposit.add(deposit);
            }
        }
        DepositList depositList1 = new DepositList();
        depositList1.setDeposits(filterDeposit);
        return depositList1;
    }

    public DepositList showType(Type type) {
        DepositStorage storage = new DepositStorage();
        DepositList depositList = storage.read();
        List<Deposit> list = depositList.getDeposits();
        List<Deposit> listType = new ArrayList<Deposit>();
        for (Deposit deposit : list) {
            if (type.equals(deposit.getType())) {
                listType.add(deposit);
            }
        }
        DepositList depositType = new DepositList();
        depositType.setDeposits(listType);
        return depositType;
    }

    public DepositList showBank(String name) {
        DepositStorage storage = new DepositStorage();
        DepositList depositList = storage.read();
        List<Deposit> list = depositList.getDeposits();
        List<Deposit> listBank = new ArrayList<Deposit>();
        for (Deposit deposit : list) {
            if (name.equals(deposit.getName())) {
                listBank.add(deposit);
            }
        }
        DepositList depositBank = new DepositList();
        depositBank.setDeposits(listBank);
        return depositBank;
    }

    public String addInfo(Deposit inputDeposit) {
        DepositStorage storage = new DepositStorage();
        DepositList depositList = storage.read();
        List<Deposit> list = depositList.getDeposits();
        for (Deposit deposit : list) {
            if (inputDeposit.getAccountId().equals(deposit.getAccountId())) {
                return "error";
            }
        }
        if (inputDeposit.getAmountOnDeposit() <= 0) {
            return "error";
        }
        if (inputDeposit.getProfitability() <= 0) {
            return "error";
        }
        if (inputDeposit.getTimeConstraints() <= 0) {
            return "error";
        }
        list.add(inputDeposit);
        depositList.setDeposits(list);
        storage.write(depositList);
        return "OK";
    }

    public Deposit delete(Long inputAccountId) {
        DepositStorage storage = new DepositStorage();
        DepositList depositList = storage.read();
        List<Deposit> list = depositList.getDeposits();
        for (Deposit deposit : list) {
            if (inputAccountId.equals(deposit.getAccountId())) {
                list.remove(deposit);
                depositList.setDeposits(list);
                storage.write(depositList);
                return deposit;
            }
        }
        return null;
    }
}
