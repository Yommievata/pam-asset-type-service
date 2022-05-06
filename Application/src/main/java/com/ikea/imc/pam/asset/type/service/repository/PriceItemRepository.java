package com.ikea.imc.pam.asset.type.service.repository;

import com.ikea.imc.pam.asset.type.service.repository.model.PriceItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceItemRepository extends JpaRepository<PriceItem, Long> {

    List<PriceItem> getPriceItemsByCostType(@Param("costTypes") List<String> costTypes);
}
