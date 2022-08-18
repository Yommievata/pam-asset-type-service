package com.ikea.imc.pam.asset.type.service.service;

import com.ikea.imc.pam.asset.type.service.exception.NotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeServiceV1Test {
    
    private static final Long ASSET_TYPE_ID = 123L;
    
    @Mock
    private AssetTypeRepository assetTypeRepository;
    
    @InjectMocks
    private AssetTypeServiceV1 service;
    
    @Nested
    class GetAssetTypeTest {
        
        @Test
        void foundAssetType() {
            
            // Given
            AssetType assetType = generateAssetType();
            when(assetTypeRepository.findById(ASSET_TYPE_ID)).thenReturn(Optional.of(assetType));
            
            // When
            Optional<AssetType> response = service.getAssetType(ASSET_TYPE_ID);
            
            // Then
            assertTrue(response.isPresent());
            assertEquals(ASSET_TYPE_ID, response.get().getId());
            
        }
        
        @Test
        void assetTypeNotFound() {
    
            // Given
            when(assetTypeRepository.findById(ASSET_TYPE_ID)).thenReturn(Optional.empty());
    
            // When
            Optional<AssetType> response = service.getAssetType(ASSET_TYPE_ID);
    
            // Then
            assertTrue(response.isEmpty());
        }
        
    }
    
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
    
    @Nested
    class GetAssetTypesByIdsTest {
        
        private static final Long ASSET_TYPE_ID_2 = 4321L;
        
        @Test
        void twoAssetTypeIds() {
            
            // Given
            List<Long> assetTypeIds = List.of(ASSET_TYPE_ID, ASSET_TYPE_ID_2);
            List<AssetType> foundAssetTypes = List.of(
                generateAssetType(),
                AssetType.builder().id(ASSET_TYPE_ID_2).build());
            when(assetTypeRepository.findAssetTypesByIds(assetTypeIds)).thenReturn(foundAssetTypes);
            
            // When
            var response = service.getAssetTypes(assetTypeIds);
            
            // Then
            assertEquals(2, response.size());
            assertEquals(ASSET_TYPE_ID, response.get(0).getId());
            assertEquals(ASSET_TYPE_ID_2, response.get(1).getId());
        }
        
        @Test
        void oneAssetTypeIsMissing() {
    
            // Given
            List<Long> assetTypeIds = List.of(ASSET_TYPE_ID, ASSET_TYPE_ID_2);
            List<AssetType> foundAssetTypes = List.of(generateAssetType());
            when(assetTypeRepository.findAssetTypesByIds(assetTypeIds)).thenReturn(foundAssetTypes);
    
            // When
            NotFoundException exception =
                assertThrows(NotFoundException.class, () -> service.getAssetTypes(assetTypeIds));
    
            // Then
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().contains(ASSET_TYPE_ID_2.toString()));
            assertFalse(exception.getMessage().contains(ASSET_TYPE_ID.toString()));
            
        }
        
        @Test
        void allAssetTypesAreMissing() {
            
            // Given
            List<Long> assetTypeIds = List.of(ASSET_TYPE_ID, ASSET_TYPE_ID_2);
            List<AssetType> foundAssetTypes = Collections.emptyList();
            when(assetTypeRepository.findAssetTypesByIds(assetTypeIds)).thenReturn(foundAssetTypes);
    
            // When
            NotFoundException exception = assertThrows(NotFoundException.class, () -> service.getAssetTypes(assetTypeIds));
    
            // Then
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().contains(ASSET_TYPE_ID_2.toString()));
            assertTrue(exception.getMessage().contains(ASSET_TYPE_ID.toString()));
    
        }
    }
    
    private static AssetType generateAssetType() {
        return AssetType.builder().id(ASSET_TYPE_ID).build();
    }
    
}
