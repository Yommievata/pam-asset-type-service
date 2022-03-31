package ikea.imc.pam.asset.type.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ikea.imc.pam.asset.type.service.client.dto.CostType;
import ikea.imc.pam.asset.type.service.repository.PriceItemRepository;
import ikea.imc.pam.asset.type.service.repository.model.PriceItem;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceItemServiceV1Test {

    private static final Long PRICE_ITEM_ID_1 = 1L;
    private static final Long PRICE_ITEM_ID_2 = 2L;

    @Mock private PriceItemRepository priceItemRepository;

    @InjectMocks private PriceItemServiceV1 service;

    @Nested
    class GetPriceItemsByCostTypeTest {

        @Captor ArgumentCaptor<List<String>> costTypeCaptor;

        @Test
        void getPriceItemsWithCostTypes() {

            // Given
            List<PriceItem> priceItems =
                    List.of(
                            generatePriceItem(PRICE_ITEM_ID_1, CostType.FIXED_PRICE),
                            generatePriceItem(PRICE_ITEM_ID_2, CostType.HOURLY_PRICE));
            when(priceItemRepository.getPriceItemsByCostType(any())).thenReturn(priceItems);

            // When
            List<PriceItem> priceItemsResponse =
                    service.getPriceItems(List.of(CostType.FIXED_PRICE, CostType.HOURLY_PRICE));

            // Then
            assertEquals(2, priceItemsResponse.size());
            assertEquals(PRICE_ITEM_ID_1, priceItemsResponse.get(0).getPriceItemId());
            assertEquals(PRICE_ITEM_ID_2, priceItemsResponse.get(1).getPriceItemId());
            assertEquals(CostType.FIXED_PRICE.value(), priceItemsResponse.get(0).getCostType());
            assertEquals(CostType.HOURLY_PRICE.value(), priceItemsResponse.get(1).getCostType());
        }

        @Test
        void getPriceItemsWithCostTypesValidateInput() {

            // Given
            List<PriceItem> priceItems =
                    List.of(
                            generatePriceItem(PRICE_ITEM_ID_1, CostType.FIXED_PRICE),
                            generatePriceItem(PRICE_ITEM_ID_2, CostType.HOURLY_PRICE));
            when(priceItemRepository.getPriceItemsByCostType(costTypeCaptor.capture())).thenReturn(priceItems);

            // When
            service.getPriceItems(List.of(CostType.FIXED_PRICE, CostType.HOURLY_PRICE));

            // Then
            assertEquals(2, costTypeCaptor.getValue().size());
            assertEquals(CostType.FIXED_PRICE.value(), costTypeCaptor.getValue().get(0));
            assertEquals(CostType.HOURLY_PRICE.value(), costTypeCaptor.getValue().get(1));
        }

        @Test
        void getPriceItemsWithoutCostTypes() {

            // Given
            List<PriceItem> priceItems =
                    List.of(
                            generatePriceItem(PRICE_ITEM_ID_1, CostType.FIXED_PRICE),
                            generatePriceItem(PRICE_ITEM_ID_2, CostType.HOURLY_PRICE));
            when(priceItemRepository.findAll()).thenReturn(priceItems);

            // When
            List<PriceItem> priceItemsResponse = service.getPriceItems(List.of());

            // Then
            assertEquals(2, priceItemsResponse.size());
            assertEquals(PRICE_ITEM_ID_1, priceItemsResponse.get(0).getPriceItemId());
            assertEquals(PRICE_ITEM_ID_2, priceItemsResponse.get(1).getPriceItemId());
            assertEquals(CostType.FIXED_PRICE.value(), priceItemsResponse.get(0).getCostType());
            assertEquals(CostType.HOURLY_PRICE.value(), priceItemsResponse.get(1).getCostType());
        }

        @Test
        void getPriceItemsNothingFound() {

            // Given
            when(priceItemRepository.getPriceItemsByCostType(any())).thenReturn(List.of());

            // When
            List<PriceItem> priceItemsResponse = service.getPriceItems(List.of(CostType.QUOTATION_BASED));

            // Then
            assertEquals(0, priceItemsResponse.size());
        }
    }

    @Nested
    class GetAllPriceItemsTest {

        @Test
        void getPriceItems() {

            // Given
            List<PriceItem> priceItems =
                    List.of(
                            generatePriceItem(PRICE_ITEM_ID_1, CostType.FIXED_PRICE),
                            generatePriceItem(PRICE_ITEM_ID_2, CostType.HOURLY_PRICE));
            when(priceItemRepository.findAll()).thenReturn(priceItems);

            // When
            List<PriceItem> priceItemsResponse = service.getAllPriceItems();

            // Then
            assertEquals(2, priceItemsResponse.size());
            assertEquals(PRICE_ITEM_ID_1, priceItemsResponse.get(0).getPriceItemId());
            assertEquals(PRICE_ITEM_ID_2, priceItemsResponse.get(1).getPriceItemId());
            assertEquals(CostType.FIXED_PRICE.value(), priceItemsResponse.get(0).getCostType());
            assertEquals(CostType.HOURLY_PRICE.value(), priceItemsResponse.get(1).getCostType());
        }

        @Test
        void getPriceItemsNothingFound() {

            // Given
            when(priceItemRepository.findAll()).thenReturn(List.of());

            // When
            List<PriceItem> priceItemsResponse = service.getAllPriceItems();

            // Then
            assertEquals(0, priceItemsResponse.size());
        }
    }

    private PriceItem generatePriceItem(Long id, CostType costType) {
        return PriceItem.builder().priceItemId(id).costType(costType.value()).build();
    }
}
