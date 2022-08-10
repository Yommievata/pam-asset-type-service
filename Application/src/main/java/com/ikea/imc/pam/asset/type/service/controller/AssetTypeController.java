package com.ikea.imc.pam.asset.type.service.controller;

import com.ikea.imc.pam.asset.type.service.client.Paths;
import com.ikea.imc.pam.asset.type.service.client.dto.AssetTypeDTO;
import com.ikea.imc.pam.asset.type.service.configuration.AssetTypeMapper;
import com.ikea.imc.pam.asset.type.service.controller.dto.ResponseEntityFactory;
import com.ikea.imc.pam.asset.type.service.service.AssetTypeService;
import com.ikea.imc.pam.asset.type.service.service.entity.AssetTypeSearchParameters;
import com.ikea.imc.pam.common.dto.ResponseMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.ASSET_TYPE_V1_ENDPOINT)
@Slf4j
public class AssetTypeController {
    
    private final AssetTypeService assetTypeService;
    
    public AssetTypeController(AssetTypeService assetTypeService) {
        this.assetTypeService = assetTypeService;
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ResponseMessageDTO<AssetTypeDTO>> getAssetType(@PathVariable Long id) {
        return assetTypeService
            .getAssetType(id)
            .map(assetType ->
                ResponseEntityFactory.generateResponse(HttpStatus.OK, AssetTypeMapper.buildAssetTypeDTO(assetType)))
            .orElseGet(() -> {
                String message = "AssetType with id " + id + " could not be found";
                log.debug(message);
                return ResponseEntityFactory.generateResponseMessage(HttpStatus.NOT_FOUND, message);
            });
    }
    
    @GetMapping
    public ResponseEntity<ResponseMessageDTO<List<AssetTypeDTO>>> getAssetTypes(
        @RequestParam(value = Paths.TECHNICAL_ID_PARAMETER, required = false) List<Long> technicalIds,
        @RequestParam(value = Paths.CLASS_ID_PARAMETER, required = false) List<Long> classIds,
        @RequestParam(value = Paths.ORGANISATION_ID_PARAMETER, required = false) List<Long> organisationIds
    ) {
    
        AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
            .builder()
            .technicalIds(technicalIds)
            .classIds(classIds)
            .organisationIds(organisationIds)
            .build();
        
        return ResponseEntityFactory.generateResponse(
            HttpStatus.OK,
            assetTypeService.getAssetTypes(searchParameters).stream().map(AssetTypeMapper::buildAssetTypeDTO).toList()
        );
        
    }
    
}
