package com.ikea.imc.pam.asset.type.service.repository.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class AssetTypeAllowedAssetTypeFormat extends AbstractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "asset_type_id")
    private Long assetTypeId;
    
    @ManyToOne
    @JoinColumn(name = "asset_type_format_id")
    private AssetTypeFormat assetTypeFormat;
    
    private Boolean defaultPackage;
    
    private Boolean deleted;
}
