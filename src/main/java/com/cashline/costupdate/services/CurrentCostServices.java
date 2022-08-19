package com.cashline.costupdate.services;

import com.cashline.costupdate.dto.CostsForSave;
import com.cashline.costupdate.dto.CurrentCosts;
import com.cashline.costupdate.dto.NewCosts;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentCostServices implements CostService {

    public CostsForSave getCostsForSave(List<NewCosts> newCosts, List<CurrentCosts> currentCosts) {
        var productCodes = currentCosts.stream()
            .map(CurrentCosts::getProductCode)
            .collect(Collectors.toList())
            .iterator();

        while (productCodes.hasNext()) {
           var productCode = productCodes.next();
           var newCostCodes = newCosts.stream()
               .filter(cost -> cost.getProductCode().equals(productCode))
               .collect(Collectors.toList());

           var numbers = newCostCodes.stream()
               .map(NewCosts::getNumber)
               .collect(Collectors.toSet())
               .iterator();

           while (newCostCodes.iterator().hasNext()) {
               var b = newCostCodes.stream()
                   .filter(cost -> cost.getNumber() == numbers.next());
           }
        }
        return null;
    }
}
