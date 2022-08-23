package com.cashline.costupdate.services;


import com.cashline.costupdate.dto.CostDTO;

import java.util.List;

public interface CostService {
    List<CostDTO> getCostsForSave(List<CostDTO> newCosts, List<CostDTO> currentCosts);
}
