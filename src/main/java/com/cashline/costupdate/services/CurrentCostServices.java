package com.cashline.costupdate.services;

import com.cashline.costupdate.dto.CostDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentCostServices implements CostService {

    @Override
    public List<CostDTO> getCostsForSave(final List<CostDTO> newCosts, final List<CostDTO> currentCosts) {
        List<CostDTO> costsForSaves = new ArrayList<>(currentCosts);

        for (final CostDTO newCost : newCosts) {
            final var newCostBeginTime = newCost.getBegin().getTime();
            final var newCostEndTime = newCost.getEnd().getTime();
            final var code = newCost.getProductCode();
            final var number = newCost.getNumber();

            final var filteredCurrentCostsForSave = costsForSaves.stream()
                .filter(cost -> cost.getNumber() == number && cost.getProductCode().equals(code))
                .collect(Collectors.toList())
                .iterator();

            if (filteredCurrentCostsForSave.hasNext()) {
                while (filteredCurrentCostsForSave.hasNext()) {
                     final var currentCostForSave = filteredCurrentCostsForSave.next();
                     final var currentCostForSaveBeginTime = currentCostForSave.getBegin().getTime();
                     final var currentCostForSaveEndTime = currentCostForSave.getEnd().getTime();
                     final int index = costsForSaves.indexOf(currentCostForSave);

                     if (currentCostForSaveEndTime < newCostBeginTime) {
                         if (!costsForSaves.contains(newCost)) {
                             costsForSaves.add(newCost);
                         }
                     } else {
                         if (newCost.getValue() == currentCostForSave.getValue()) {
                             currentCostForSave.setEnd(newCost.getEnd());
                             costsForSaves.set(index, currentCostForSave);
                         } else if (currentCostForSaveEndTime <= newCostEndTime) {
                             currentCostForSave.setEnd(newCost.getBegin());
                             costsForSaves.add(newCost);
                         } else if (currentCostForSaveBeginTime <= newCostBeginTime) {
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
