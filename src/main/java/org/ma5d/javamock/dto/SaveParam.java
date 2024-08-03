package org.ma5d.javamock.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class SaveParam implements Serializable {
    private String timeStamp;
    private String domainCom;
    private String pathWithParam;
    private String response;

    public SaveParam(ResultSet resultSet) throws SQLException {
        this.timeStamp = resultSet.getString("time_stamp");
        this.domainCom = resultSet.getString("adomain");
        this.pathWithParam = resultSet.getString("path_param");
        this.response = resultSet.getString("response");
    }
}
