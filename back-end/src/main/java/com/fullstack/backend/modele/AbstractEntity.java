package com.fullstack.backend.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nom", nullable = false, length = 32)
    private String nom;
    @Column(name = "description")
    private String description;
    @CreatedDate
    @Column(name = "creationData", nullable = false, updatable = false)
    private Instant creationData;
    @LastModifiedDate
    @Column(name = "lastModifiedData")
    @JsonIgnore
    private Instant lastModifiedData;

}

