package com.ikea.imc.pam.asset.type.service.service;

import com.ikea.imc.pam.asset.type.service.repository.model.AssetType;
import com.ikea.imc.pam.asset.type.service.service.entity.AssetTypeSearchParameters;

import java.util.List;

public interface AssetTypeService {
    
    List<AssetType> getAssetTypes(AssetTypeSearchParameters searchParameters);
    
}
