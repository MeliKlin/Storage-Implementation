package com.meli.storageimplementation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
public class CreateOrUpdateTutorialDTO {

    @NotEmpty(message = "Campo tutorial não pode estar vazio.")
    private String title;
    @NotEmpty(message = "Campo descrição não pode estar vazio")
    private String description;

    private boolean published = false;

}
