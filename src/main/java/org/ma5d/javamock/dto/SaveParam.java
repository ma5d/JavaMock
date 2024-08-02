package org.ma5d.javamock.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SaveParam implements Serializable {
    private String timeStamp;
    private String aDomain;
    private String pathWithParam;
    private String response;

    public String[] toStringArray(){
        return new String[]{timeStamp, aDomain, pathWithParam, response};
    }
}
