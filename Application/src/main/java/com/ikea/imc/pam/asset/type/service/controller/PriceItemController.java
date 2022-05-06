package com.ikea.imc.pam.asset.type.service.controller;

import com.ikea.imc.pam.asset.type.service.client.Paths;
import com.ikea.imc.pam.asset.type.service.client.dto.CostType;
import com.ikea.imc.pam.asset.type.service.client.dto.PriceItemDTO;
import com.ikea.imc.pam.asset.type.service.configuration.PriceItemMapper;
import com.ikea.imc.pam.asset.type.service.controller.dto.ResponseEntityFactory;
import com.ikea.imc.pam.asset.type.service.service.PriceItemService;
import java.util.Collections;
import java.util.List;

import com.ikea.imc.pam.common.dto.ResponseMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.PRICE_ITEM_V1_ENDPOINT)
@Slf4j
public class PriceItemController {

    private final PriceItemService priceItemService;

    public PriceItemController(PriceItemService priceItemService) {
        this.priceItemService = priceItemService;
    }

    @GetMapping()
    public ResponseEntity<ResponseMessageDTO<List<PriceItemDTO>>> getPriceItems(
            @RequestParam(value = "cost-type", required = false) List<String> costTypes) {
        List<CostType> costTypeList =
                costTypes != null
                        ? costTypes.stream().map(CostType::getByPathVariable).toList()
                        : Collections.emptyList();
        return ResponseEntityFactory.generateResponse(
                HttpStatus.OK,
                priceItemService.getPriceItems(costTypeList).stream()
                        .map(PriceItemMapper::buildPriceItemDTO)
                        .toList());
    }
}
