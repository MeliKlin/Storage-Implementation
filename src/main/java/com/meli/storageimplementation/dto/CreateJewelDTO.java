package com.meli.storageimplementation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.storageimplementation.models.Jewel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJewelDTO {

    @JsonProperty("material")
    String material;

    @JsonProperty("weight")
    BigDecimal weight;

    @JsonProperty("karat")
    Integer karat;

    public Jewel mountJewel() {
        return Jewel.builder()
                .material(material)
                .weight(weight)
                .karat(karat)
                .build();
    }
}
