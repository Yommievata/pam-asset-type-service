package com.ikea.imc.pam.asset.type.service.configuration;

import com.ikea.imc.pam.asset.type.service.client.dto.PriceItemDTO;
import com.ikea.imc.pam.asset.type.service.repository.model.PriceItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceItemMapper {
    
    public static PriceItemDTO buildPriceItemDTO(PriceItem priceItem) {
        return PriceItemDTO.builder()
            .id(priceItem.getPriceItemId())
            .businessModel(priceItem.getBusinessModel() != null ? priceItem.getBusinessModel().getName() : null)
            .businessModelSortOrder(
                priceItem.getBusinessModel() != null ? priceItem.getBusinessModel().getSortOrder() : null)
            .businessArea(priceItem.getBusinessArea() != null ? priceItem.getBusinessArea().getName() : null)
            .businessAreaSortOrder(
                priceItem.getBusinessArea() != null ? priceItem.getBusinessArea().getSortOrder() : null)
            .communication(priceItem.getPriceItemName())
            .weeks(priceItem.getWeeks())
            .costPerUnit(priceItem.getPrice())
            .costType(priceItem.getCostType())
            .build();
    }
}
