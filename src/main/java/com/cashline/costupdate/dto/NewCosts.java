package com.cashline.costupdate.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NewCosts {
    private long id;
    private String productCode;
    private int number;
    private int depart;
    private Date begin;
    private Date end;
    private long value;
}
