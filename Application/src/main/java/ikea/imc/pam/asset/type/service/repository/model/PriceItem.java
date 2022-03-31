package ikea.imc.pam.asset.type.service.repository.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class PriceItem extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceItemId;

    private String priceItemName;
    private String assetTypeName;
    private String assetFormatName;
    private String contentTypeName;
    private String serviceTypeName;
    private boolean template;

    @Column(name = "new")
    private boolean isNew;

    private Double price;
    private Long pcoptDataMappingId;
    private String businessModel;
    private Integer field3;
    private String imcResponsibleCc;
    private String area;
    private String imcAssignmentArea;
    private String imcAssignmentGroup;
    private String imcAssignment;
    private String imcSubAssignment;
    private String contentArea;
    private String contentType;
    private String costType;
    private String iosArea;
    private String rAndPcContact;
    private String imcArea;
    private String orderSystem;
    private String content;

    private Integer weeks;
}
