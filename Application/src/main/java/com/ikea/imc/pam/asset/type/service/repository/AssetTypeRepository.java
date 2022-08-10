package com.ikea.imc.pam.asset.type.service.repository;

import com.ikea.imc.pam.asset.type.service.repository.model.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetTypeRepository extends JpaRepository<AssetType, Long> {

    List<AssetType> findAssetTypesByAssetTypeTechnical(@Param("technicalIds") List<Long> technicalIds);
    
    List<AssetType> findAssetTypesByAssetTypeClass(@Param("classIds") List<Long> classIds);
    
    List<AssetType> findAssetTypesByAssetTypeOrganisation(@Param("organisationIds") List<Long> organisationIds);
    
    List<AssetType> findAssetTypesByAssetTypeTechnicalAndAssetTypeClass(@Param("technicalIds") List<Long> technicalIds, @Param("classIds") List<Long> classIds);
    
    List<AssetType> findAssetTypesByAssetTypeTechnicalAndAssetTypeOrganisation(@Param("technicalIds") List<Long> technicalIds, @Param("organisationIds") List<Long> organisationIds);
    
    List<AssetType> findAssetTypesByAssetTypeClassAndAssetTypeOrganisation(@Param("classIds") List<Long> classIds, @Param("organisationIds") List<Long> organisationIds);
    
    List<AssetType> findAssetTypesByAssetTypeTechnicalAndAssetTypeClassAndAssetTypeOrganisation(@Param("technicalIds") List<Long> technicalIds, @Param("classIds") List<Long> classIds, @Param("organisationIds") List<Long> organisationIds);
}
