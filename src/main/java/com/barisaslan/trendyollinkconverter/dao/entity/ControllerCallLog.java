package com.barisaslan.trendyollinkconverter.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "controller_call_log")
public class ControllerCallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSS")
    private Date insertDateTime;

    private Integer statusCode;

    @Column(length = 2048)
    private String requestBody;

    @Column(length = 2048)
    private String responseBody;

    private String apiPath;

    private Long executionTime;

}
