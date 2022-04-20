package com.meli.storageimplementation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.storageimplementation.models.Jewel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJewelDTO {

    @JsonProperty("material")
    @NotEmpty(message = "Jewel material is required.")
    String material;

    @JsonProperty("weight")
    @NotNull(message = "Jewel weight is required.")
    BigDecimal weight;

    @JsonProperty("karat")
    @NotNull(message = "Jewel karat is required.")
    Integer karat;

    public Jewel mountJewel() {
        return Jewel.builder()
                .material(material)
                .weight(weight)
                .karat(karat)
                .build();
    }
}
