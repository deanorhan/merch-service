package org.daemio.merch.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "images", indexes = { @Index(name = "merch_idx", columnList = "merch_id") })
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    @Hidden
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merch_id", nullable = false)
    private Merch merch;

    @NotBlank
    @Column(nullable = false)
    private String uri;

    private String title;
}
