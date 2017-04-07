package com.gennadii.overchuk.depositstore.client;

import com.gennadii.overchuk.depositstore.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.*;

/**
 * Created by Tehnik on 28.03.2017.
 */
public class Client {
    private Socket cs;
    private InputStream socketInputStream;
    private OutputStream socketOutputStream;
    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void init() throws IOException, JAXBException {
        cs = new Socket("localhost", 3128);
        socketInputStream = cs.getInputStream();
        socketOutputStream = cs.getOutputStream();
        jaxbContext = JAXBContext.newInstance(Request.class, DepositList.class, Deposit.class);
        marshaller = jaxbContext.createMarshaller();
        unmarshaller = jaxbContext.createUnmarshaller();
    }

    public DepositList list() {
        try {
            Request request = new Request();
            request.setCommand("List");
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(request, stringWriter);
            String xml = stringWriter.toString();
            Utils.convertStringToStream(socketOutputStream, xml);

            String streamToString = Utils.convertStreamToString(socketInputStream);
            StringReader reader = new StringReader(streamToString);
            DepositList depositList = (DepositList) unmarshaller.unmarshal(reader);
            return depositList;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sum() {
        try {
            Request request = new Request();
            request.setCommand("Sum");
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(request, stringWriter);
            String xml = stringWriter.toString();
            Utils.convertStringToStream(socketOutputStream, xml);

            String streamToString = Utils.convertStreamToString(socketInputStream);

            return streamToString;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String count() {
        try {
            Request request = new Request();
            request.setCommand("Count");
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(request, stringWriter);
            Utils.convertStringToStream(socketOutputStream, stringWriter.toString());

            String streamToString = Utils.convertStreamToString(socketInputStream);
            return streamToString;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Deposit accountInfo(Long accountId) {
        try {
            Request request = new Request();
            request.setCommand("Get account ID");
            Deposit inputAccountId = new Deposit();
            inputAccountId.setAccountId(accountId);
            request.setParams(inputAccountId);

            StringWriter stringWriter1 = new StringWriter();
            marshaller.marshal(request,stringWriter1);
            Utils.convertStringToStream(socketOutputStream,stringWriter1.toString());

            String streamToString = Utils.convertStreamToString(socketInputStream);
            StringReader reader = new StringReader(streamToString);

            return (Deposit) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DepositList infoDepositor(String depositor) {
        try {
            Request request = new Request();
            request.setCommand("Get Depositor");
            Deposit inputDepositor = new Deposit();
            inputDepositor.setDepositor(depositor);
            request.setParams(inputDepositor);

            StringWriter stringWriter2 = new StringWriter();
            marshaller.marshal(request, stringWriter2);
            Utils.convertStringToStream(socketOutputStream,stringWriter2.toString());

            String streamToString = Utils.convertStreamToString(socketInputStream);
            StringReader reader = new StringReader(streamToString);
            return (DepositList) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DepositList showType(Type type) {
        try {
            Request request = new Request();
            request.setCommand("Get Type Doposit");
            Deposit
                    inputType = new Deposit();
            inputType.setType(type);
            request.setParams(inputType);

            StringWriter stringWriter3 = new StringWriter();
            marshaller.marshal(request, stringWriter3);
            Utils.convertStringToStream(socketOutputStream, stringWriter3.toString());

            String streamToString = Utils.convertStreamToString(socketInputStream);
            StringReader reader = new StringReader(streamToString);
            return (DepositList) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DepositList showBank(String name) {
        try {
            Request request = new Request();
            request.setCommand("Get Bank Name");
            Deposit inputName = new Deposit();
            inputName.setName(name);
            request.setParams(inputName);

            StringWriter stringWriter4 = new StringWriter();
            marshaller.marshal(request, stringWriter4);
            Utils.convertStringToStream(socketOutputStream,stringWriter4.toString());

            String streamToString = Utils.convertStreamToString(socketInputStream);
            StringReader reader = new StringReader(streamToString);
            return (DepositList) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String addInfo(Deposit inputDeposit) {
        try {
            Request request = new Request();
            request.setCommand("Add info for deposit");

            request.setParams(inputDeposit);
            StringWriter stringWriter1 = new StringWriter();
            marshaller.marshal(request,stringWriter1);
            Utils.convertStringToStream(socketOutputStream, stringWriter1.toString());

            return Utils.convertStreamToString(socketInputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Deposit delete(Long inputAccountId) {
        try {
            Request request = new Request();
            request.setCommand("Get AccountId for delete");
            Deposit accountDelete = new Deposit();
            accountDelete.setAccountId(inputAccountId);
            request.setParams(accountDelete);

            StringWriter stringWriter5 = new StringWriter();
            marshaller.marshal(request, stringWriter5);
            Utils.convertStringToStream(socketOutputStream,stringWriter5.toString());

            String streamToString = Utils.convertStreamToString(socketInputStream);
            StringReader reader = new StringReader(streamToString);
            return (Deposit) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;

    }
}