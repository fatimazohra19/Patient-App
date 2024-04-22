package com.example.demo.document.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
//public class Document extends CustomAbstractAuditableWithoutId {
public class Document {

//    @Id
//    @UuidGenerator(style = UuidGenerator.Style.TIME)
//    private UUID id;
@Id
@Column(nullable = false, length = 80)
@UuidGenerator(style = UuidGenerator.Style.TIME)
private String id;


    @Audited
    @Column(length = 100)
    private String filename;
    @Audited
    @Column(length = 100)
    private String contentType;
    private Long size;


    public String getId() {
       if (id == null) {
           return null;
       }
       return id.toString();
        // on utilisant optional de la programmation fonctionnel
        //return Optional.ofNullable(id).map(UUID::toString).orElse(null);
   }
}
