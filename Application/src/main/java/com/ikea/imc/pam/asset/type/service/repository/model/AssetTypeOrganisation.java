package com.ikea.imc.pam.asset.type.service.repository.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class AssetTypeOrganisation extends AbstractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String definition;
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private AssetTypeOrganisation parentId;
    
}
