package com.gennadii.overchuk.depositstore;

import javax.xml.bind.annotation.*;

/**
 * Created by Tehnik on 28.03.2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {
    @XmlElement
    private String command;
    @XmlElement
    private Deposit params;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Deposit getParams() {
        return params;
    }

    public void setParams(Deposit params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (command != null ? !command.equals(request.command) : request.command != null) return false;
        return params != null ? params.equals(request.params) : request.params == null;
    }

    @Override
    public int hashCode() {
        int result = command != null ? command.hashCode() : 0;
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", params=" + params +
                '}';
    }
}
