package com.ikea.imc.pam.asset.type.service.service;

import com.ikea.imc.pam.asset.type.service.repository.AssetTypeRepository;
import com.ikea.imc.pam.asset.type.service.repository.model.AssetType;
import com.ikea.imc.pam.asset.type.service.service.entity.AssetTypeSearchParameters;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeServiceV1Test {
    
    private static final Long ASSET_TYPE_ID = 123L;
    
    @Mock
    private AssetTypeRepository assetTypeRepository;
    
    @InjectMocks
    private AssetTypeServiceV1 service;
    
    @Nested
    class GetAssetTypesTest {
    
        private static final Long TECHNICAL_ID = 111L;
        private static final Long CLASS_ID = 222L;
        private static final Long ORGANISATION_ID = 333L;
        
        @Test
        void allSearchParametersAreNull() {
            
            // Given
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters.builder().build();
            when(assetTypeRepository.findAll()).thenReturn(List.of(generateAssetType()));
            
            // When
            var response = service.getAssetTypes(searchParameters);
            
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        }
        
        @Test
        void allSearchParametersAreEmpty() {
    
            // Given
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
                .builder()
                .technicalIds(Collections.emptyList())
                .classIds(Collections.emptyList())
                .organisationIds(Collections.emptyList())
                .build();
            when(assetTypeRepository.findAll()).thenReturn(List.of(generateAssetType()));
    
            // When
            var response = service.getAssetTypes(searchParameters);
    
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        
        }
        
        @Test
        void findByTechnicalId() {
            
            // Given
            List<Long> technicalIds = List.of(TECHNICAL_ID);
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
                .builder()
                .technicalIds(technicalIds)
                .build();
            when(assetTypeRepository.findAssetTypesByAssetTypeTechnical(technicalIds))
                .thenReturn(List.of(generateAssetType()));
    
            // When
            var response = service.getAssetTypes(searchParameters);
    
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        }
        
        @Test
        void findByClassId() {
    
            // Given
            List<Long> classIds = List.of(CLASS_ID);
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
                .builder()
                .classIds(classIds)
                .build();
            when(assetTypeRepository.findAssetTypesByAssetTypeClass(classIds)).thenReturn(List.of(generateAssetType()));
    
            // When
            var response = service.getAssetTypes(searchParameters);
    
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        
        }
        
        @Test
        void findByOrganisationId() {
    
            // Given
            List<Long> organisationIds = List.of(ORGANISATION_ID);
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
                .builder()
                .organisationIds(organisationIds)
                .build();
            when(assetTypeRepository.findAssetTypesByAssetTypeOrganisation(organisationIds))
                .thenReturn(List.of(generateAssetType()));
    
            // When
            var response = service.getAssetTypes(searchParameters);
    
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        
        }
        
        @Test
        void findByTechincalIdAndClassId() {
    
            // Given
            List<Long> technicalIds = List.of(TECHNICAL_ID);
            List<Long> classIds = List.of(CLASS_ID);
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
                .builder()
                .technicalIds(technicalIds)
                .classIds(classIds)
                .build();
            when(assetTypeRepository.findAssetTypesByAssetTypeTechnicalAndAssetTypeClass(technicalIds, classIds))
                .thenReturn(List.of(generateAssetType()));
    
            // When
            var response = service.getAssetTypes(searchParameters);
    
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        
        }
        
        @Test
        void findByTechnicalIdAndOrganisationId() {
    
            // Given
            List<Long> technicalIds = List.of(TECHNICAL_ID);
            List<Long> organisationIds = List.of(ORGANISATION_ID);
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
                .builder()
                .technicalIds(technicalIds)
                .organisationIds(organisationIds)
                .build();
            when(assetTypeRepository.findAssetTypesByAssetTypeTechnicalAndAssetTypeOrganisation(technicalIds, organisationIds))
                .thenReturn(List.of(generateAssetType()));
    
            // When
            var response = service.getAssetTypes(searchParameters);
    
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        
        }
        
        @Test
        void findByClassIdAndOrganisationId() {
    
            // Given
            List<Long> classIds = List.of(CLASS_ID);
            List<Long> organisationIds = List.of(ORGANISATION_ID);
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
                .builder()
                .classIds(classIds)
                .organisationIds(organisationIds)
                .build();
            when(assetTypeRepository.findAssetTypesByAssetTypeClassAndAssetTypeOrganisation(classIds, organisationIds))
                .thenReturn(List.of(generateAssetType()));
    
            // When
            var response = service.getAssetTypes(searchParameters);
    
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        
        }
        
        @Test
        void findByTechnicalIdAndClassIdAndOrganisationId() {
    
            // Given
            List<Long> technicalIds = List.of(TECHNICAL_ID);
            List<Long> classIds = List.of(CLASS_ID);
            List<Long> organisationIds = List.of(ORGANISATION_ID);
            AssetTypeSearchParameters searchParameters = AssetTypeSearchParameters
                .builder()
                .technicalIds(technicalIds)
                .classIds(classIds)
                .organisationIds(organisationIds)
                .build();
            when(assetTypeRepository.findAssetTypesByAssetTypeTechnicalAndAssetTypeClassAndAssetTypeOrganisation(
                technicalIds,
                classIds,
                organisationIds)).thenReturn(List.of(generateAssetType()));
    
            // When
            var response = service.getAssetTypes(searchParameters);
    
            // Then
            assertEquals(1, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
        
        }
    }
    
    private static AssetType generateAssetType() {
        return AssetType.builder().id(ASSET_TYPE_ID).build();
    }
    
}
