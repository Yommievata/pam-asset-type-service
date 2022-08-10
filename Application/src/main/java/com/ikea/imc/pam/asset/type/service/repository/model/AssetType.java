package com.ikea.imc.pam.asset.type.service.repository.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@NamedQuery(name = "AssetType.findAssetTypesByAssetTypeTechnical",
    query = "select distinct at from AssetType at "
        + "left join fetch at.assetTypeFunction atf "
        + "left join fetch at.assetTypeOrganisation ato "
        + "left join fetch at.assetTypeTechnical att "
        + "left join fetch at.assetTypeClass atc "
        + "left join fetch at.assetTypeTitle atTitle "
        + "left join fetch at.allowedAssetTypeFormats aatf "
        + "where att.id in :technicalIds AND at.active = true")
@NamedQuery(name = "AssetType.findAssetTypesByAssetTypeClass",
    query = "select distinct at from AssetType at "
        + "left join fetch at.assetTypeFunction atf "
        + "left join fetch at.assetTypeOrganisation ato "
        + "left join fetch at.assetTypeTechnical att "
        + "left join fetch at.assetTypeClass atc "
        + "left join fetch at.assetTypeTitle atTitle "
        + "left join fetch at.allowedAssetTypeFormats aatf "
        + "where atc.id in :classIds AND at.active = true")
@NamedQuery(name = "AssetType.findAssetTypesByAssetTypeOrganisation",
    query = "select distinct at from AssetType at "
        + "left join fetch at.assetTypeFunction atf "
        + "left join fetch at.assetTypeOrganisation ato "
        + "left join fetch at.assetTypeTechnical att "
        + "left join fetch at.assetTypeClass atc "
        + "left join fetch at.assetTypeTitle atTitle "
        + "left join fetch at.allowedAssetTypeFormats aatf "
        + "where ato.id in :organisationIds AND at.active = true")
@NamedQuery(name = "AssetType.findAssetTypesByAssetTypeTechnicalAndAssetTypeClass",
    query = "select distinct at from AssetType at "
        + "left join fetch at.assetTypeFunction atf "
        + "left join fetch at.assetTypeOrganisation ato "
        + "left join fetch at.assetTypeTechnical att "
        + "left join fetch at.assetTypeClass atc "
        + "left join fetch at.assetTypeTitle atTitle "
        + "left join fetch at.allowedAssetTypeFormats aatf "
        + "where att.id in :technicalIds AND atc.id in :classIds AND at.active = true")
@NamedQuery(name = "AssetType.findAssetTypesByAssetTypeTechnicalAndAssetTypeOrganisation",
    query = "select distinct at from AssetType at "
        + "left join fetch at.assetTypeFunction atf "
        + "left join fetch at.assetTypeOrganisation ato "
        + "left join fetch at.assetTypeTechnical att "
        + "left join fetch at.assetTypeClass atc "
        + "left join fetch at.assetTypeTitle atTitle "
        + "left join fetch at.allowedAssetTypeFormats aatf "
        + "where att.id in :technicalIds AND ato.id in :organisationIds AND at.active = true")
@NamedQuery(name = "AssetType.findAssetTypesByAssetTypeClassAndAssetTypeOrganisation",
    query = "select distinct at from AssetType at "
        + "left join fetch at.assetTypeFunction atf "
        + "left join fetch at.assetTypeOrganisation ato "
        + "left join fetch at.assetTypeTechnical att "
        + "left join fetch at.assetTypeClass atc "
        + "left join fetch at.assetTypeTitle atTitle "
        + "left join fetch at.allowedAssetTypeFormats aatf "
        + "where atc.id in :classIds AND ato.id in :organisationIds AND at.active = true")
@NamedQuery(name = "AssetType.findAssetTypesByAssetTypeTechnicalAndAssetTypeClassAndAssetTypeOrganisation",
    query = "select distinct at from AssetType at "
        + "left join fetch at.assetTypeFunction atf "
        + "left join fetch at.assetTypeOrganisation ato "
        + "left join fetch at.assetTypeTechnical att "
        + "left join fetch at.assetTypeClass atc "
        + "left join fetch at.assetTypeTitle atTitle "
        + "left join fetch at.allowedAssetTypeFormats aatf "
        + "where att.id in :technicalIds AND atc.id in :classIds AND ato.id in :organisationIds AND at.active = true")
public class AssetType extends AbstractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private AssetType parent;
    
    private String name;
    
    private String shortName;
    
    @ManyToOne
    @JoinColumn(name = "function_id")
    private AssetTypeFunction assetTypeFunction;
    
    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private AssetTypeOrganisation assetTypeOrganisation;
    
    @ManyToOne
    @JoinColumn(name = "technical_id")
    private AssetTypeTechnical assetTypeTechnical;
    
    @ManyToOne
    @JoinColumn(name = "class_id")
    private AssetTypeClass assetTypeClass;
    
    @ManyToOne
    @JoinColumn(name = "title_id")
    private AssetTypeTitle assetTypeTitle;
    
    @OneToMany
    @JoinColumn(name = "asset_type_id")
    private List<AssetTypeAllowedAssetTypeFormat> allowedAssetTypeFormats;
    
    private Boolean digitalAsset;
    
    private Boolean active;
}
