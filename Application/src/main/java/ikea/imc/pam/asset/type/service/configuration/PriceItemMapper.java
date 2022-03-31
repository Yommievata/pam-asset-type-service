package ikea.imc.pam.asset.type.service.configuration;

import ikea.imc.pam.asset.type.service.client.dto.PriceItemDTO;
import ikea.imc.pam.asset.type.service.repository.model.PriceItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceItemMapper {

    public static PriceItemDTO buildPriceItemDTO(PriceItem priceItem) {
        return PriceItemDTO.builder()
                .id(priceItem.getPriceItemId())
                .communication(priceItem.getPriceItemName())
                .weeks(priceItem.getWeeks())
                .costPerUnit(priceItem.getPrice())
                .costType(priceItem.getCostType())
                .contentArea(priceItem.getContentArea())
                .contentType(priceItem.getContentType())
                .build();
    }
}
