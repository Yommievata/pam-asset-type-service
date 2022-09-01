package com.ikea.imc.pam.asset.type.service.configuration;

import com.ikea.imc.pam.asset.type.service.repository.model.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssetTypeMapperTest {
    
    private static final Long ASSET_TYPE_ID = 1L;
    private static final Long ASSET_TYPE_PARENT_ID = 2L;
    private static final String ASSET_TYPE_NAME = "asset type name";
    private static final String ASSET_TYPE_SHORT_NAME = "ATN TEST";
    private static final Boolean ASSET_TYPE_DIGITAL_ASSET = true;
    private static final Boolean ASSET_TYPE_ACTIVE = true;
    
    private static final Long ASSET_TYPE_FUNCTION_ID = 10L;
    private static final String ASSET_TYPE_FUNCTION_NAME = "asset type function name";
    private static final String ASSET_TYPE_FUNCTION_DEFINITION = "asset type function definition";
    
    private static final Long ASSET_TYPE_ORGANISATION_ID = 11L;
    private static final String ASSET_TYPE_ORGANISATION_NAME = "asset type organisation name";
    private static final String ASSET_TYPE_ORGANISATION_DEFINITION = "asset type organisation definition";
    
    private static final Long ASSET_TYPE_TECHNICAL_ID = 13L;
    private static final String ASSET_TYPE_TECHNICAL_NAME = "asset type technical name";
    private static final String ASSET_TYPE_TECHNICAL_DEFINITION = "asset type technical definition";
    private static final Boolean ASSET_TYPE_TECHNICAL_TEMPLATE_IS_MANDATORY = true;
    private static final Boolean ASSET_TYPE_TECHNICAL_CAN_USE_TEMPLATE = true;
    
    private static final Long ASSET_TYPE_CLASS_ID = 15L;
    private static final String ASSET_TYPE_CLASS_NAME = "asset type class name";
    private static final String ASSET_TYPE_CLASS_DEFINITION = "asset type class definition";
    
    private static final Long ASSET_TYPE_TITLE_ID = 16L;
    private static final String ASSET_TYPE_TITLE_NAME = "asset type title name";
    private static final String ASSET_TYPE_TITLE_DEFINITION = "asset type title definition";
    
    private static final Long ASSET_TYPE_ALLOWED_ASSET_TYPE_FORMAT_ID = 17L;
    private static final Long ASSET_TYPE_ALLOWED_ASSET_TYPE_FORMAT_ASSET_TYPE_ID = ASSET_TYPE_ID;
    private static final Boolean ASSET_TYPE_ALLOWED_ASSET_TYPE_FORMAT_DEFAULT_PACKAGE = true;
    private static final Boolean ASSET_TYPE_ALLOWED_ASSET_TYPE_FORMAT_DELETE = false;
    
    private static final Long ASSET_TYPE_FORMAT_ID = 18L;
    private static final String ASSET_TYPE_FORMAT_NAME = "asset type format name";
    private static final String ASSET_TYPE_FORMAT_DEFINITION = "asset type format definition";
    private static final Integer ASSET_TYPE_FORMAT_MAX_NUMBER_OF_CHARACTERS = 100;
    private static final Boolean ASSET_TYPE_FORMAT_ACTIVE = true;
    
    private static final Long ASSET_TYPE_FORMAT_CLASS_ID = 19L;
    private static final String ASSET_TYPE_FORMAT_CLASS_NAME = "asset type format class name";
    private static final String ASSET_TYPE_FORMAT_CLASS_DEFINITION = "asset type format class definition";
    
    @Nested
    class BuildAssetTypeDTOTest {
        
        @Test
        void allValuesAreNull() {
            
            // Given
            AssetType assetType = AssetType.builder().build();
            
            // When
            var dto = AssetTypeMapper.buildAssetTypeDTO(assetType);
            
            // Then
            assertNull(dto.id());
            assertNull(dto.name());
            assertNull(dto.shortName());
            assertFalse(dto.hasTemplates());
            assertFalse(dto.isTemplateMandatory());
            assertNull(dto.technicalId());
            assertNull(dto.technicalName());
            assertNull(dto.organisationId());
            assertNull(dto.organisationName());
            assertNull(dto.classId());
            assertNull(dto.className());
            assertNull(dto.functionId());
            assertNull(dto.functionName());
            assertNull(dto.titleId());
            assertNull(dto.titleName());
            assertEquals(0, dto.formats().size());
        }
        
        @Test
        void assetTypeNameIsNull() {
            
            // Given
            AssetType assetType = getAssetType().name(null).build();
    
            // When
            var dto = AssetTypeMapper.buildAssetTypeDTO(assetType);
    
            // Then
            assertEquals(ASSET_TYPE_ID, dto.id());
            assertEquals(1, dto.formats().size());
    
            var formatDTO = dto.formats().get(0);
            assertEquals(ASSET_TYPE_FORMAT_ID, formatDTO.id());
            assertEquals(ASSET_TYPE_FORMAT_NAME, formatDTO.formatName());
            assertEquals(" " + ASSET_TYPE_FORMAT_NAME, formatDTO.name());
        }
        
        @Test
        void assetTypeFormatNameIsNull() {
            
            // Given
            AssetTypeFormat assetTypeFormat = getAssetTypeFormat().name(null).build();
            AssetTypeAllowedAssetTypeFormat allowedAssetTypeFormat = getAssetTypeAllowedAssetTypeFormat()
                .assetTypeFormat(assetTypeFormat)
                .build();
            
            AssetType assetType = getAssetType().allowedAssetTypeFormats(List.of(allowedAssetTypeFormat)).build();
    
            // When
            var dto = AssetTypeMapper.buildAssetTypeDTO(assetType);
    
            // Then
            assertEquals(ASSET_TYPE_ID, dto.id());
            assertEquals(1, dto.formats().size());
    
            var formatDTO = dto.formats().get(0);
            assertEquals(ASSET_TYPE_FORMAT_ID, formatDTO.id());
            assertNull(formatDTO.formatName());
            assertEquals(ASSET_TYPE_NAME + " ", formatDTO.name());
        }
        
        @Test
        void oneAssetTypeFormatIsNull() {
            
            // Given
            AssetTypeFormat assetTypeFormat = getAssetTypeFormat().build();
            AssetTypeAllowedAssetTypeFormat allowedAssetTypeFormat = getAssetTypeAllowedAssetTypeFormat()
                .assetTypeFormat(assetTypeFormat)
                .build();
            AssetTypeAllowedAssetTypeFormat nullAllowedAssetTypeFormat = getAssetTypeAllowedAssetTypeFormat()
                .assetTypeFormat(null)
                .build();
    
            AssetType assetType = getAssetType()
                .allowedAssetTypeFormats(List.of(allowedAssetTypeFormat, nullAllowedAssetTypeFormat)).build();
    
            // When
            var dto = AssetTypeMapper.buildAssetTypeDTO(assetType);
    
            // Then
            assertEquals(ASSET_TYPE_ID, dto.id());
            assertEquals(2, dto.formats().size());
    
            var formatDTO1 = dto.formats().get(0);
            assertEquals(ASSET_TYPE_FORMAT_ID, formatDTO1.id());
            assertEquals(ASSET_TYPE_FORMAT_NAME, formatDTO1.formatName());
            assertEquals(ASSET_TYPE_NAME + " " + ASSET_TYPE_FORMAT_NAME, formatDTO1.name());
    
            var formatDTO2 = dto.formats().get(1);
            assertNull(formatDTO2.id());
            assertNull(formatDTO2.formatName());
            assertNull(formatDTO2.name());
    
        }
        
        @Test
        void allValuesAreSet() {
            
            // Given
            AssetType assetType = getAssetType().build();
            
            // When
            var dto = AssetTypeMapper.buildAssetTypeDTO(assetType);
            
            // Then
            assertEquals(ASSET_TYPE_ID, dto.id());
            assertEquals(ASSET_TYPE_NAME, dto.name());
            assertEquals(ASSET_TYPE_SHORT_NAME, dto.shortName());
            assertEquals(ASSET_TYPE_TECHNICAL_CAN_USE_TEMPLATE, dto.hasTemplates());
            assertEquals(ASSET_TYPE_TECHNICAL_TEMPLATE_IS_MANDATORY, dto.isTemplateMandatory());
            assertEquals(ASSET_TYPE_TECHNICAL_ID, dto.technicalId());
            assertEquals(ASSET_TYPE_TECHNICAL_NAME, dto.technicalName());
            assertEquals(ASSET_TYPE_ORGANISATION_ID, dto.organisationId());
            assertEquals(ASSET_TYPE_ORGANISATION_NAME, dto.organisationName());
            assertEquals(ASSET_TYPE_CLASS_ID, dto.classId());
            assertEquals(ASSET_TYPE_CLASS_NAME, dto.className());
            assertEquals(ASSET_TYPE_FUNCTION_ID, dto.functionId());
            assertEquals(ASSET_TYPE_FUNCTION_NAME, dto.functionName());
            assertEquals(ASSET_TYPE_TITLE_ID, dto.titleId());
            assertEquals(ASSET_TYPE_TITLE_NAME, dto.titleName());
            assertEquals(1, dto.formats().size());
            
            var formatDTO = dto.formats().get(0);
            assertEquals(ASSET_TYPE_FORMAT_ID, formatDTO.id());
            assertEquals(ASSET_TYPE_FORMAT_NAME, formatDTO.formatName());
            assertEquals(ASSET_TYPE_NAME + " " + ASSET_TYPE_FORMAT_NAME, formatDTO.name());
        }
        
    }
    
    private static AssetType.AssetTypeBuilder getAssetType() {
        return AssetType
            .builder()
            .id(ASSET_TYPE_ID)
            .parent(AssetType.builder().id(ASSET_TYPE_PARENT_ID).build())
            .name(ASSET_TYPE_NAME)
            .shortName(ASSET_TYPE_SHORT_NAME)
            .assetTypeFunction(generateAssetTypeFunction())
            .assetTypeOrganisation(generateAssetTypeOrganisation())
            .assetTypeTechnical(generateAssetTypeTechnical())
            .assetTypeClass(generateAssetTypeClass())
            .assetTypeTitle(generateAssetTypeTitle())
            .allowedAssetTypeFormats(List.of(getAssetTypeAllowedAssetTypeFormat().build()))
            .digitalAsset(ASSET_TYPE_DIGITAL_ASSET)
            .active(ASSET_TYPE_ACTIVE);
    }
    
    private static AssetTypeFunction generateAssetTypeFunction() {
        return AssetTypeFunction
            .builder()
            .id(ASSET_TYPE_FUNCTION_ID)
            .name(ASSET_TYPE_FUNCTION_NAME)
            .definition(ASSET_TYPE_FUNCTION_DEFINITION)
            .build();
    }
    
    private static AssetTypeOrganisation generateAssetTypeOrganisation() {
        return AssetTypeOrganisation
            .builder()
            .id(ASSET_TYPE_ORGANISATION_ID)
            .name(ASSET_TYPE_ORGANISATION_NAME)
            .definition(ASSET_TYPE_ORGANISATION_DEFINITION)
            .parentId(AssetTypeOrganisation.builder().id(ASSET_TYPE_PARENT_ID).build())
            .build();
    }
    
    private static AssetTypeTechnical generateAssetTypeTechnical() {
        return AssetTypeTechnical
            .builder()
            .id(ASSET_TYPE_TECHNICAL_ID)
            .name(ASSET_TYPE_TECHNICAL_NAME)
            .definition(ASSET_TYPE_TECHNICAL_DEFINITION)
            .parentId(AssetTypeTechnical.builder().id(ASSET_TYPE_PARENT_ID).build())
            .templateIsMandatory(ASSET_TYPE_TECHNICAL_TEMPLATE_IS_MANDATORY)
            .canUseTemplate(ASSET_TYPE_TECHNICAL_CAN_USE_TEMPLATE)
            .build();
    }
    
    private static AssetTypeClass generateAssetTypeClass() {
        return AssetTypeClass
            .builder()
            .id(ASSET_TYPE_CLASS_ID)
            .name(ASSET_TYPE_CLASS_NAME)
            .definition(ASSET_TYPE_CLASS_DEFINITION)
            .build();
    }
    
    private static AssetTypeTitle generateAssetTypeTitle() {
        return AssetTypeTitle
            .builder()
            .id(ASSET_TYPE_TITLE_ID)
            .name(ASSET_TYPE_TITLE_NAME)
            .definition(ASSET_TYPE_TITLE_DEFINITION)
            .build();
    }
    
    private static AssetTypeAllowedAssetTypeFormat.AssetTypeAllowedAssetTypeFormatBuilder getAssetTypeAllowedAssetTypeFormat() {
        return AssetTypeAllowedAssetTypeFormat
            .builder()
            .id(ASSET_TYPE_ALLOWED_ASSET_TYPE_FORMAT_ID)
            .assetTypeId(ASSET_TYPE_ALLOWED_ASSET_TYPE_FORMAT_ASSET_TYPE_ID)
            .assetTypeFormat(getAssetTypeFormat().build())
            .defaultPackage(ASSET_TYPE_ALLOWED_ASSET_TYPE_FORMAT_DEFAULT_PACKAGE)
            .deleted(ASSET_TYPE_ALLOWED_ASSET_TYPE_FORMAT_DELETE);
    }
    
    private static AssetTypeFormat.AssetTypeFormatBuilder getAssetTypeFormat() {
        return AssetTypeFormat
            .builder()
            .id(ASSET_TYPE_FORMAT_ID)
            .name(ASSET_TYPE_FORMAT_NAME)
            .definition(ASSET_TYPE_FORMAT_DEFINITION)
            .classId(generateAssetTypeFormatClass())
            .maxNumberOfCharacters(ASSET_TYPE_FORMAT_MAX_NUMBER_OF_CHARACTERS)
            .active(ASSET_TYPE_FORMAT_ACTIVE);
    }
    
    private static AssetTypeFormatClass generateAssetTypeFormatClass() {
        return AssetTypeFormatClass
            .builder()
            .id(ASSET_TYPE_FORMAT_CLASS_ID)
            .name(ASSET_TYPE_FORMAT_CLASS_NAME)
            .definition(ASSET_TYPE_FORMAT_CLASS_DEFINITION)
            .build();
    }
}
