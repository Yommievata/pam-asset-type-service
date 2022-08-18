package com.ikea.imc.pam.asset.type.service.service;

import com.ikea.imc.pam.asset.type.service.exception.NotFoundException;
import com.ikea.imc.pam.asset.type.service.repository.AssetTypeRepository;
import com.ikea.imc.pam.asset.type.service.repository.model.AssetType;
import com.ikea.imc.pam.asset.type.service.service.entity.AssetTypeSearchParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AssetTypeServiceV1 implements AssetTypeService {
    
    private final AssetTypeRepository assetTypeRepository;
    
    public AssetTypeServiceV1(AssetTypeRepository assetTypeRepository) {
        this.assetTypeRepository = assetTypeRepository;
    }
    
    @Override
    public Optional<AssetType> getAssetType(Long assetTypeId) {
        log.debug("Get asset type with id {}", assetTypeId);
        return assetTypeRepository.findById(assetTypeId);
    }
    
    @Override
    public List<AssetType> getAssetTypes(List<Long> assetTypeIds) {
        log.debug("Get asset types with ids {}", assetTypeIds);
        List<AssetType> assetTypes = assetTypeRepository.findAssetTypesByIds(assetTypeIds);
        Set<Long> foundAssetTypeIds = assetTypes.stream().map(AssetType::getId).collect(Collectors.toSet());
        List<Long> notFoundAssetTypeIds = assetTypeIds.stream().filter(id -> !foundAssetTypeIds.contains(id)).toList();
        
        if (!notFoundAssetTypeIds.isEmpty()) {
            String message = "Asset types with ids " + notFoundAssetTypeIds + " not found";
            log.debug(message);
            throw new NotFoundException(message);
        }
        
        return assetTypes;
    }
    
    @Override
    public List<AssetType> getAssetTypes(AssetTypeSearchParameters searchParameters) {
        log.debug("Get asset types with search parameters {}", searchParameters);
        
        if (containValues(searchParameters.technicalIds())) {
            if (containValues(searchParameters.classIds())) {
                if (containValues(searchParameters.organisationIds())) {
                    log.debug("Find all asset types with technical ids {} and class ids {} and organisation ids {}",
                        searchParameters.technicalIds(),
                        searchParameters.classIds(),
                        searchParameters.organisationIds());
                    
                    return assetTypeRepository
                        .findAssetTypesByAssetTypeTechnicalAndAssetTypeClassAndAssetTypeOrganisation(
                            searchParameters.technicalIds(),
                            searchParameters.classIds(),
                            searchParameters.organisationIds());
                }
                
                log.debug("Find all asset types with technical ids {} and class ids {}",
                    searchParameters.technicalIds(),
                    searchParameters.classIds());
                
                return assetTypeRepository.findAssetTypesByAssetTypeTechnicalAndAssetTypeClass(
                    searchParameters.technicalIds(),
                    searchParameters.classIds());
            }
            
            if (containValues(searchParameters.organisationIds())) {
                log.debug("Find all asset types with technical ids {} and organisation ids {}",
                    searchParameters.technicalIds(),
                    searchParameters.organisationIds());
                return assetTypeRepository.findAssetTypesByAssetTypeTechnicalAndAssetTypeOrganisation(
                    searchParameters.technicalIds(),
                    searchParameters.organisationIds());
            }
            
            log.debug("Find all asset types with technical ids {}", searchParameters.technicalIds());
            return assetTypeRepository.findAssetTypesByAssetTypeTechnical(searchParameters.technicalIds());
        } else if (containValues(searchParameters.classIds())) {
            if (containValues(searchParameters.organisationIds())) {
                log.debug("Find all asset types with class ids {} and organisation ids {}",
                    searchParameters.classIds(),
                    searchParameters.organisationIds());
                
                return assetTypeRepository.findAssetTypesByAssetTypeClassAndAssetTypeOrganisation(
                    searchParameters.classIds(),
                    searchParameters.organisationIds());
            }
            
            log.debug("Find all asset types with class ids {}", searchParameters.classIds());
            return assetTypeRepository.findAssetTypesByAssetTypeClass(searchParameters.classIds());
        } else if (containValues(searchParameters.organisationIds())) {
            log.debug("Find all asset types with organisation ids {}", searchParameters.organisationIds());
            return assetTypeRepository.findAssetTypesByAssetTypeOrganisation(searchParameters.organisationIds());
        } else {
            log.debug("Find all asset types");
            // Should this be blocked in the future? Could be a dangerous operation if the data grows
            return assetTypeRepository.findAll();
        }
    }
    
    private static boolean containValues(List<Long> valueList) {
        return valueList != null && !valueList.isEmpty();
    }
}
