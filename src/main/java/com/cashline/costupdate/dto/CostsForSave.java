package com.cashline.costupdate.dto;

import com.cashline.costupdate.entities.Cost;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CostsForSave {
    private List<Cost> costs;
}
