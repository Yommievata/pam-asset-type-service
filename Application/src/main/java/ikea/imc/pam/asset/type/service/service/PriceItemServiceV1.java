package ikea.imc.pam.asset.type.service.service;

import ikea.imc.pam.asset.type.service.client.dto.CostType;
import ikea.imc.pam.asset.type.service.repository.PriceItemRepository;
import ikea.imc.pam.asset.type.service.repository.model.PriceItem;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PriceItemServiceV1 implements PriceItemService {

    private final PriceItemRepository priceItemRepository;

    public PriceItemServiceV1(PriceItemRepository priceItemRepository) {
        this.priceItemRepository = priceItemRepository;
    }

    @Override
    public List<PriceItem> getPriceItems(List<CostType> costTypes) {
        log.debug("Get price items with cost types {}", costTypes);

        if (costTypes == null || costTypes.isEmpty()) {
            log.debug("No cost types defined, retrieving all price items");
            return getAllPriceItems();
        }

        return priceItemRepository.getPriceItemsByCostType(
                costTypes.stream().map(CostType::value).collect(Collectors.toList()));
    }

    @Override
    public List<PriceItem> getAllPriceItems() {
        log.debug("Get all price items");
        return priceItemRepository.findAll();
    }
}
