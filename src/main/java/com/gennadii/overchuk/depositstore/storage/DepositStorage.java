package com.gennadii.overchuk.depositstore.storage;

import com.gennadii.overchuk.depositstore.Deposit;
import com.gennadii.overchuk.depositstore.DepositList;
import com.gennadii.overchuk.depositstore.Type;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tehnik on 23.03.2017.
 */
public class DepositStorage {

    public static final String PATH = "deposit.xml";

    public void write(DepositList depositList) {
        try {
            FileWriter writer = new FileWriter(PATH, false);
            JAXBContext jaxbContext = JAXBContext.newInstance(DepositList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.marshal(depositList, writer);
            writer.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DepositList read() {
        try {
            FileReader reader = new FileReader(PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(DepositList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (DepositList) unmarshaller.unmarshal(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new DepositList();
    }
}

