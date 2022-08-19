package com.cashline.costupdate.dao;

import com.cashline.costupdate.entities.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentCostsRepository extends JpaRepository <Cost, Long> {
}
