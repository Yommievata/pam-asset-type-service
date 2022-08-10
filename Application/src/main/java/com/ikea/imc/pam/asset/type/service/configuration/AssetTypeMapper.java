package com.ikea.imc.pam.asset.type.service.configuration;

import com.ikea.imc.pam.asset.type.service.client.dto.AssetTypeDTO;
import com.ikea.imc.pam.asset.type.service.client.dto.AssetTypeFormatDTO;
import com.ikea.imc.pam.asset.type.service.repository.model.AssetType;
import com.ikea.imc.pam.asset.type.service.repository.model.AssetTypeAllowedAssetTypeFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssetTypeMapper {
    
    public static AssetTypeDTO buildAssetTypeDTO(AssetType assetType) {
        return AssetTypeDTO
            .builder()
            .id(assetType.getId())
            .name(assetType.getName())
            .shortName(assetType.getShortName())
            .hasTemplates(assetType.getAssetTypeTechnical() != null && assetType.getAssetTypeTechnical().getCanUseTemplate())
            .isTemplateMandatory(assetType.getAssetTypeTechnical() != null && assetType.getAssetTypeTechnical().getTemplateIsMandatory())
            .technicalId(assetType.getAssetTypeTechnical() != null ? assetType.getAssetTypeTechnical().getId() : null)
            .technicalName(assetType.getAssetTypeTechnical() != null ? assetType.getAssetTypeTechnical().getName() : null)
            .organisationId(assetType.getAssetTypeOrganisation() != null ? assetType.getAssetTypeOrganisation().getId() : null)
            .organisationName(assetType.getAssetTypeOrganisation() != null ? assetType.getAssetTypeOrganisation().getName() : null)
            .classId(assetType.getAssetTypeClass() != null ? assetType.getAssetTypeClass().getId() : null)
            .className(assetType.getAssetTypeClass() != null ? assetType.getAssetTypeClass().getName() : null)
            .functionId(assetType.getAssetTypeFunction() != null ? assetType.getAssetTypeFunction().getId() : null)
            .functionName(assetType.getAssetTypeFunction() != null ? assetType.getAssetTypeFunction().getName() : null)
            .titleId(assetType.getAssetTypeTitle() != null ? assetType.getAssetTypeTitle().getId() : null)
            .titleName(assetType.getAssetTypeTitle() != null ? assetType.getAssetTypeTitle().getName() : null)
            .formats(assetType.getAllowedAssetTypeFormats() != null
                ? assetType.getAllowedAssetTypeFormats().stream().map(allowedFormat -> buildAssetTypeFormatDTO(assetType, allowedFormat)).toList()
                : Collections.emptyList())
            .build();
    }
    
    private static AssetTypeFormatDTO buildAssetTypeFormatDTO(AssetType assetType, AssetTypeAllowedAssetTypeFormat allowedFormat) {
        if (allowedFormat.getAssetTypeFormat() == null) {
            return AssetTypeFormatDTO.builder().build();
        }
        
        String name = (assetType.getName() != null ? assetType.getName() : "")
            + " "
            + (allowedFormat.getAssetTypeFormat().getName() != null ? allowedFormat.getAssetTypeFormat().getName() : "");
        
        return AssetTypeFormatDTO
            .builder()
            .id(allowedFormat.getAssetTypeFormat().getId())
            .formatName(allowedFormat.getAssetTypeFormat().getName())
            .name(name)
            .build();
    }
}
