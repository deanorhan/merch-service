package org.daemio.merch.dto;

import java.util.ArrayList;
import java.util.List;

import org.daemio.merch.model.Merch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchListResponse {
    
    private List<Merch> merch = new ArrayList<>();
}
