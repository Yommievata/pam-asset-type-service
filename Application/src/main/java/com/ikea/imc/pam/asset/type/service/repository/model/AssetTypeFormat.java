package com.ikea.imc.pam.asset.type.service.repository.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class AssetTypeFormat extends AbstractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String definition;
    
    @ManyToOne
    @JoinColumn(name = "class_id")
    private AssetTypeFormatClass classId;
    
    private Integer maxNumberOfCharacters;
    
    private Boolean active;
    
}
