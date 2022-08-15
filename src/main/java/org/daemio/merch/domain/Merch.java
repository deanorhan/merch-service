package org.daemio.merch.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "merch", indexes = { @Index(name = "status_idx", columnList = "status") })
@Getter
@Setter
@EntityListeners({ AuditingEntityListener.class })
public class Merch {
    
    @Id
    @Column(name = "merch_id")
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private MerchStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merch")
    private List<Image> images;
    
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @PastOrPresent
    @CreatedDate
    private LocalDateTime createdTime;

    @PastOrPresent
    @LastModifiedDate
    private LocalDateTime modifiedTime;
}
