package org.ma5d.javamock.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SaveParam implements Serializable {
    private String timeStamp;
    private String path;
    private String responseBody;

    public String toCSV(){
        return timeStamp + "," + path + "," + responseBody;
    }
}
