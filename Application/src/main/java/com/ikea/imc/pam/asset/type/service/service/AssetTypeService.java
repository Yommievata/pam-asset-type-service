package com.ikea.imc.pam.asset.type.service.service;

import com.ikea.imc.pam.asset.type.service.repository.model.AssetType;
import com.ikea.imc.pam.asset.type.service.service.entity.AssetTypeSearchParameters;

import java.util.List;
import java.util.Optional;

public interface AssetTypeService {
    
    Optional<AssetType> getAssetType(Long assetTypeId);
    
    List<AssetType> getAssetTypes(List<Long> assetTypeIds);
    
    List<AssetType> getAssetTypes(AssetTypeSearchParameters searchParameters);
    
}
