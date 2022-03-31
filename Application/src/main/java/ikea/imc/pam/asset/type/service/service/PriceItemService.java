package ikea.imc.pam.asset.type.service.service;

import ikea.imc.pam.asset.type.service.client.dto.CostType;
import ikea.imc.pam.asset.type.service.repository.model.PriceItem;
import java.util.List;

public interface PriceItemService {

    List<PriceItem> getPriceItems(List<CostType> costTypes);

    List<PriceItem> getAllPriceItems();
}
