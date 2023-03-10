package com.ikea.imc.pam.asset.type.service.controller;

import com.ikea.imc.pam.asset.type.service.repository.model.AssetType;
import com.ikea.imc.pam.asset.type.service.service.AssetTypeService;
import com.ikea.imc.pam.asset.type.service.service.entity.AssetTypeSearchParameters;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeControllerTest {
    
    private static final Long ASSET_TYPE_ID = 123L;
    
    @Mock
    private AssetTypeService assetTypeService;
    
    @InjectMocks
    private AssetTypeController controller;
    
    @Nested
    class GetAssetTypeTest {
        
        @Test
        void foundAssetType() {
            
            // Given
            AssetType assetType = generateAssetType();
            when(assetTypeService.getAssetType(ASSET_TYPE_ID)).thenReturn(Optional.of(assetType));
            
            // When
            var response = controller.getAssetType(ASSET_TYPE_ID);
            
            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            
            var responseMessageDTO = response.getBody();
            assertEquals(200, responseMessageDTO.getStatusCode());
            assertNotNull(responseMessageDTO.getData());
            
            var dto = responseMessageDTO.getData();
            assertEquals(ASSET_TYPE_ID, dto.id());
            
        }
        
        @Test
        void notFoundAssetType() {
    
            // Given
            when(assetTypeService.getAssetType(ASSET_TYPE_ID)).thenReturn(Optional.empty());
    
            // When
            var response = controller.getAssetType(ASSET_TYPE_ID);
    
            // Then
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertNotNull(response.getBody());
    
            var responseMessageDTO = response.getBody();
            assertEquals(404, responseMessageDTO.getStatusCode());
            assertNotNull(responseMessageDTO.getMessage());

        }
    }
    
    @Nested
    class GetAssetTypesTest {
        
        private static final Long TECHNICAL_ID = 111L;
        private static final Long CLASS_ID = 222L;
        private static final Long ORGANISATION_ID = 333L;
        
        @Test
        void withAllParameters() {
            
            // Given
            List<Long> technicalIds = List.of(TECHNICAL_ID);
            List<Long> classIds = List.of(CLASS_ID);
            List<Long> organisationIds = List.of(ORGANISATION_ID);
            AssetTypeSearchParameters expectedParameters = AssetTypeSearchParameters
                .builder()
                .technicalIds(technicalIds)
                .classIds(classIds)
                .organisationIds(organisationIds)
                .build();
            when(assetTypeService.getAssetTypes(expectedParameters)).thenReturn(List.of(generateAssetType()));
            
            // When
            var response = controller.getAssetTypes(technicalIds, classIds, organisationIds, null);
            
            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            
        }
        
        @Test
        void withoutParameters() {
            
            // Given
            AssetTypeSearchParameters expectedParameters = AssetTypeSearchParameters.builder().build();
            when(assetTypeService.getAssetTypes(expectedParameters)).thenReturn(List.of(generateAssetType()));
    
            // When
            var response = controller.getAssetTypes(null, null, null, null);
    
            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            
        }
    
        @Test
        void findByIds() {
        
            // Given
            List<Long> assetTypeIds = List.of(ASSET_TYPE_ID);
            when(assetTypeService.getAssetTypes(assetTypeIds)).thenReturn(List.of(generateAssetType()));
        
            // When
            var response = controller.getAssetTypes(null, null, null, assetTypeIds);
        
            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
        
            var responseMessage = response.getBody();
            assertEquals(200, responseMessage.getStatusCode());
            assertNotNull(responseMessage.getData());
        
            var dtoList = responseMessage.getData();
            assertEquals(1, dtoList.size());
            assertEquals(ASSET_TYPE_ID, dtoList.get(0).id());
        }
        
        @Test
        void responseIsOK() {
    
            // Given
            AssetTypeSearchParameters expectedParameters = AssetTypeSearchParameters.builder().build();
            when(assetTypeService.getAssetTypes(expectedParameters)).thenReturn(List.of(generateAssetType()));
    
            // When
            var response = controller.getAssetTypes(null, null, null, null);
    
            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            
            var responseMessage = response.getBody();
            assertEquals(200, responseMessage.getStatusCode());
            assertNotNull(responseMessage.getData());
            
            var dtoList = responseMessage.getData();
            assertEquals(1, dtoList.size());
            assertEquals(ASSET_TYPE_ID, dtoList.get(0).id());
        }
        
    }
    
    private static AssetType generateAssetType() {
        return AssetType.builder().id(ASSET_TYPE_ID).build();
    }
}
