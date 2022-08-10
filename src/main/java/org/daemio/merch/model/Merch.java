package org.daemio.merch.model;

import javax.persistence.Entity;
import javax.persistence.Id;

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
}
