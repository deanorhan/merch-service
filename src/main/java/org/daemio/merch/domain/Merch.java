package org.daemio.merch.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Entity
@JsonSerialize
@Getter
@Setter
public class Merch {
    
    @Id
    private Integer id;

    @NotNull
    private String title;
    
    @NotNull
    private BigDecimal price;
}
