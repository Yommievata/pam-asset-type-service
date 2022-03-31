package ikea.imc.pam.asset.type.service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ikea.imc.pam.asset.type.service.client.dto.CostType;
import ikea.imc.pam.asset.type.service.client.dto.PriceItemDTO;
import ikea.imc.pam.asset.type.service.repository.model.PriceItem;
import ikea.imc.pam.asset.type.service.service.PriceItemService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class PriceItemControllerTest {

    private static final Long PRICE_ITEM_ID_1 = 1L;
    private static final Long PRICE_ITEM_ID_2 = 2L;
    private static final String PRICE_ITEM_NAME = "name of price item";
    private static final String PRICE_ITEM_ASSET_TYPE_NAME = "asset type name";
    private static final String PRICE_ITEM_ASSET_FORMAT_NAME = "asset format name";
    private static final String PRICE_ITEM_CONTENT_TYPE_NAME = "content type name";
    private static final String PRICE_ITEM_SERVICE_TYPE_NAME = "service type name";
    private static final boolean PRICE_ITEM_TEMPLATE = false;
    private static final boolean PRICE_ITEM_IS_NEW = false;
    private static final Double PRICE_ITEM_PRICE = 20.0;
    private static final Long PRICE_ITEM_PCOPT_DATA_MAPPING_ID = 10L;
    private static final String PRICE_ITEM_BUSINESS_MODEL = "business model";
    private static final Integer PRICE_ITEM_FIELD_3 = 3;
    private static final String PRICE_ITEM_IMC_RESPONSIBLE_CC = "imc responsible cc";
    private static final String PRICE_ITEM_AREA = "area";
    private static final String PRICE_ITEM_IMC_ASSIGNMENT_AREA = "imc assignment area";
    private static final String PRICE_ITEM_IMC_ASSIGNMENT_GROUP = "imc assignment group";
    private static final String PRICE_ITEM_IMC_ASSIGNMENT = "imc assignment";
    private static final String PRICE_ITEM_IMC_SUB_ASSIGNMENT = "imc sub assignment";
    private static final String PRICE_ITEM_CONTENT_AREA = "content area";
    private static final String PRICE_ITEM_CONTENT_TYPE = "content type";
    private static final String PRICE_ITEM_COST_TYPE = CostType.FIXED_PRICE.value();
    private static final String PRICE_ITEM_IOS_AREA = "ios area";
    private static final String PRICE_ITEM_R_AND_PC_CONTACT = "r&pc contact";
    private static final String PRICE_ITEM_IMC_AREA = "imc area";
    private static final String PRICE_ITEM_ORDER_SYSTEM = "order system";
    private static final String PRICE_ITEM_CONTENT = "content";
    private static final Integer PRICE_ITEM_WEEKS = 10;

    @Mock PriceItemService priceItemService;

    @InjectMocks PriceItemController controller;

    @Nested
    class GetPriceItemsTest {

        @Captor ArgumentCaptor<List<CostType>> costTypeCaptor;

        @Test
        void getPriceItemsWithoutCostTypes() {

            // Given
            when(priceItemService.getPriceItems(List.of()))
                    .thenReturn(List.of(generatePriceItem(PRICE_ITEM_ID_1), generatePriceItem(PRICE_ITEM_ID_2)));

            // When
            var response = controller.getPriceItems(null);

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            var responseMessage = response.getBody();
            assertEquals(200, responseMessage.getStatusCode());
            assertNotNull(responseMessage.getData());
            var priceItems = responseMessage.getData();
            assertEquals(2, priceItems.size());
            PriceItemDTO dto1 = priceItems.get(0);
            assertEquals(PRICE_ITEM_ID_1, dto1.getId());
            assertEquals(PRICE_ITEM_ID_2, priceItems.get(1).getId());
            assertEquals(PRICE_ITEM_NAME, dto1.getCommunication());
            assertEquals(PRICE_ITEM_WEEKS, dto1.getWeeks());
            assertEquals(PRICE_ITEM_PRICE, dto1.getCostPerUnit());
            assertEquals(PRICE_ITEM_COST_TYPE, dto1.getCostType());
            assertEquals(PRICE_ITEM_CONTENT_AREA, dto1.getContentArea());
            assertEquals(PRICE_ITEM_CONTENT_TYPE, dto1.getContentType());
        }

        @Test
        void getPriceItemsWithCostTypes() {

            // Given
            when(priceItemService.getPriceItems(costTypeCaptor.capture()))
                    .thenReturn(List.of(generatePriceItem(PRICE_ITEM_ID_1), generatePriceItem(PRICE_ITEM_ID_2)));

            // When
            var response =
                    controller.getPriceItems(
                            List.of(
                                    CostType.FIXED_PRICE.pathVariable(),
                                    CostType.HOURLY_PRICE.pathVariable(),
                                    CostType.QUOTATION_BASED.pathVariable()));

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            var responseMessage = response.getBody();
            assertEquals(200, responseMessage.getStatusCode());
            assertNotNull(responseMessage.getData());
            var priceItems = responseMessage.getData();
            assertEquals(2, priceItems.size());
            assertEquals(PRICE_ITEM_ID_1, priceItems.get(0).getId());
            assertEquals(PRICE_ITEM_ID_2, priceItems.get(1).getId());
            assertEquals(3, costTypeCaptor.getValue().size());
            assertEquals(CostType.FIXED_PRICE, costTypeCaptor.getValue().get(0));
            assertEquals(CostType.HOURLY_PRICE, costTypeCaptor.getValue().get(1));
            assertEquals(CostType.QUOTATION_BASED, costTypeCaptor.getValue().get(2));
        }

        @Test
        void getPriceItemsEmptyList() {

            // Given
            when(priceItemService.getPriceItems(any())).thenReturn(Collections.emptyList());

            // When
            var response =
                    controller.getPriceItems(
                            List.of(CostType.FIXED_PRICE.pathVariable(), CostType.HOURLY_PRICE.pathVariable()));

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            var responseMessage = response.getBody();
            assertEquals(200, responseMessage.getStatusCode());
            assertNotNull(responseMessage.getData());
            var priceItems = responseMessage.getData();
            assertEquals(0, priceItems.size());
        }
    }

    private PriceItem generatePriceItem(Long id) {
        return PriceItem.builder()
                .priceItemId(id)
                .priceItemName(PRICE_ITEM_NAME)
                .assetTypeName(PRICE_ITEM_ASSET_TYPE_NAME)
                .assetFormatName(PRICE_ITEM_ASSET_FORMAT_NAME)
                .contentTypeName(PRICE_ITEM_CONTENT_TYPE_NAME)
                .serviceTypeName(PRICE_ITEM_SERVICE_TYPE_NAME)
                .template(PRICE_ITEM_TEMPLATE)
                .isNew(PRICE_ITEM_IS_NEW)
                .price(PRICE_ITEM_PRICE)
                .pcoptDataMappingId(PRICE_ITEM_PCOPT_DATA_MAPPING_ID)
                .businessModel(PRICE_ITEM_BUSINESS_MODEL)
                .field3(PRICE_ITEM_FIELD_3)
                .imcResponsibleCc(PRICE_ITEM_IMC_RESPONSIBLE_CC)
                .area(PRICE_ITEM_AREA)
                .imcAssignmentArea(PRICE_ITEM_IMC_ASSIGNMENT_AREA)
                .imcAssignmentGroup(PRICE_ITEM_IMC_ASSIGNMENT_GROUP)
                .imcAssignment(PRICE_ITEM_IMC_ASSIGNMENT)
                .imcSubAssignment(PRICE_ITEM_IMC_SUB_ASSIGNMENT)
                .contentArea(PRICE_ITEM_CONTENT_AREA)
                .contentType(PRICE_ITEM_CONTENT_TYPE)
                .costType(PRICE_ITEM_COST_TYPE)
                .iosArea(PRICE_ITEM_IOS_AREA)
                .rAndPcContact(PRICE_ITEM_R_AND_PC_CONTACT)
                .imcArea(PRICE_ITEM_IMC_AREA)
                .orderSystem(PRICE_ITEM_ORDER_SYSTEM)
                .content(PRICE_ITEM_CONTENT)
                .weeks(PRICE_ITEM_WEEKS)
                .build();
    }
}
