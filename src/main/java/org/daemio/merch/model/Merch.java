package org.daemio.merch.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonSerialize
public class Merch {
    
    @Id
    private Integer id;
}
