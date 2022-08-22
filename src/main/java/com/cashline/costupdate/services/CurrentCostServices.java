package com.cashline.costupdate.services;

import com.cashline.costupdate.dto.CostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentCostServices implements CostService {

    @Override
    public List<CostDTO> getCostsForSave(List<CostDTO> newCosts, List<CostDTO> currentCosts) {
        List<CostDTO> costsForSaves = currentCosts.stream()
            .map(CostDTO::clone)
            .collect(Collectors.toList());

        int index;
        for (CostDTO newCost : newCosts) {
            final var code = newCost.getProductCode();
            final var number = newCost.getNumber();

            final var filtredCurrentCostsForSave = costsForSaves.stream()
                .filter(cost -> cost.getNumber() == number && cost.getProductCode().equals(code))
                .collect(Collectors.toList())
                .iterator();

            if(filtredCurrentCostsForSave.hasNext()) {
                while (filtredCurrentCostsForSave.hasNext()) {
                        var currentCostForSave = filtredCurrentCostsForSave.next();
                        index = costsForSaves.indexOf(currentCostForSave);
                        if (currentCostForSave.getEnd().getTime() < newCost.getBegin().getTime()) {
                            if (!costsForSaves.contains(newCost)) {
                                costsForSaves.add(newCost);
                            }
                        } else {
                            if (newCost.getValue() == currentCostForSave.getValue()) {
                                currentCostForSave.setEnd(newCost.getEnd());
                                costsForSaves.set(index, currentCostForSave);
                            } else if (currentCostForSave.getEnd().getTime() <= newCost.getEnd().getTime()) {
                                currentCostForSave.setEnd(newCost.getBegin());
                                costsForSaves.add(newCost);
                            } else if (currentCostForSave.getBegin().getTime() < newCost.getBegin().getTime() &&
                            currentCostForSave.getEnd().getTime() > newCost.getEnd().getTime()) {
                                final var newCurrent = currentCostForSave.clone();
                                newCurrent.setBegin(newCost.getEnd());
                                currentCostForSave.setEnd(newCost.getBegin());
                                costsForSaves.add(newCurrent);
                                costsForSaves.add(newCost);
                            }
                        }
                    }
            } else {
                costsForSaves.add(newCost);
            }
        }
        return costsForSaves;
    }
}
