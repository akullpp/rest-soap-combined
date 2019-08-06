package de.akull.geoloc.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "geolocation")
@IdClass(GeolocationEntityId.class)
public class GeolocationEntity {

    @Generated(GenerationTime.INSERT)
    @Column(name = "count", columnDefinition = "serial", insertable = false, updatable = false)
    private Long count;

    @Id
    @NotBlank
    private String query;

    @Id
    @NotBlank
    private String address;

    @NotBlank
    @Column(unique = true)
    private String latitude;

    @NotBlank
    @Column(unique = true)
    private String longitude;
}
