package com.gennadii.overchuk.depositstore.controller;

import com.gennadii.overchuk.depositstore.*;
import com.gennadii.overchuk.depositstore.bl.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.*;

/**
 * Created by Tehnik on 28.03.2017.
 */
public class Controller {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(3128);
            System.out.println("Waiting for a client...");
            while (true) {

                final Socket socket = ss.accept();

                System.out.println("Got a client.");

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        InputStream socketInputStream = null;
                        OutputStream socketOutputStream = null;
                        try {

                            socketInputStream = socket.getInputStream();
                            socketOutputStream = socket.getOutputStream();

                            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class, DepositList.class, Deposit.class);
                            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                            Service commandService = new Service();

                            while (true) {
                                String streamToString = Utils.convertStreamToString(socketInputStream);
                                StringReader reader = new StringReader(streamToString);
                                Request request = (Request) unmarshaller.unmarshal(reader);

                                switch (request.getCommand()) {
                                    case "List":
                                        DepositList depositList = commandService.list();
                                        Marshaller marshaller = jaxbContext.createMarshaller();

                                        StringWriter stringWriter = new StringWriter();
                                        marshaller.marshal(depositList, stringWriter);
                                        String xml = stringWriter.toString();
                                        Utils.convertStringToStream(socketOutputStream, xml);
                                        break;
                                    case "Sum":
                                        Double sum = commandService.sum();
                                        Utils.convertStringToStream(socketOutputStream, String.valueOf(sum));

                                        break;
                                    case "Count":
                                        Integer count = commandService.count();
                                        Utils.convertStringToStream(socketOutputStream, String.valueOf(count));

                                        break;

                                    case "Get account ID":

                                        Deposit accountInfo = commandService.accountInfo(request.getParams().getAccountId());
                                        Marshaller marshaller1 = jaxbContext.createMarshaller();

                                        StringWriter stringWriter1 = new StringWriter();
                                        marshaller1.marshal(accountInfo, stringWriter1);
                                        Utils.convertStringToStream(socketOutputStream, stringWriter1.toString());

                                        break;

                                    case "Get Depositor":

                                        DepositList infoDepositor = commandService.infoDepositor(request.getParams().getDepositor());
                                        Marshaller marshaller2 = jaxbContext.createMarshaller();

                                        StringWriter stringWriter2 = new StringWriter();
                                        marshaller2.marshal(infoDepositor, stringWriter2);
                                        Utils.convertStringToStream(socketOutputStream, stringWriter2.toString());

                                        break;

                                    case "Get Type Doposit":
                                        DepositList showType = commandService.showType(request.getParams().getType());
                                        Marshaller marshaller3 = jaxbContext.createMarshaller();

                                        StringWriter stringWriter3 = new StringWriter();
                                        marshaller3.marshal(showType, stringWriter3);
                                        Utils.convertStringToStream(socketOutputStream, stringWriter3.toString());

                                        break;

                                    case "Get Bank Name":

                                        DepositList nameBank = commandService.showBank(request.getParams().getName());
                                        Marshaller marshaller4 = jaxbContext.createMarshaller();

                                        StringWriter stringWriter4 = new StringWriter();
                                        marshaller4.marshal(nameBank, stringWriter4);
                                        Utils.convertStringToStream(socketOutputStream, stringWriter4.toString());

                                        break;

                                    case "Add info for deposit":
                                        String addDeposit = commandService.addInfo(request.getParams());
                                        Utils.convertStringToStream(socketOutputStream, addDeposit);

                                        break;
                                    case "Get AccountId for delete":
                                        Deposit accountDelete = commandService.delete(request.getParams().getAccountId());
                                        Marshaller marshaller6 = jaxbContext.createMarshaller();

                                        StringWriter stringWriter6 = new StringWriter();
                                        marshaller6.marshal(accountDelete, stringWriter6);
                                        Utils.convertStringToStream(socketOutputStream,
                                                stringWriter6.toString());

                                        break;

                                    default:
                                        throw new IllegalArgumentException("Invalid request");
                                }

                            }

                        } catch (Exception x) {
                            x.printStackTrace();
                        } finally {
                            try {
                                if (socketInputStream != null) {
                                    socketInputStream.close();
                                }
                                if (socketOutputStream != null) {
                                    socketOutputStream.close();
                                }
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    }

                }).start();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (ss != null) {
                ss.close();
            }
        }
    }
}