package com.meli.storageimplementation.controller;

import com.meli.storageimplementation.dto.CreateOrUpdateJewelDTO;
import com.meli.storageimplementation.errors.IdIsRequiredException;
import com.meli.storageimplementation.errors.JewelNotFoundException;
import com.meli.storageimplementation.models.Jewel;
import com.meli.storageimplementation.service.JewelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class JewelController {

    JewelService jewelService;

    @PostMapping("/joia/inserir")
    public ResponseEntity<Void> createJewel(
            @Valid @RequestBody CreateOrUpdateJewelDTO createOrUpdateJewelDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Jewel jewel = jewelService.createOrUpdateJewel(createOrUpdateJewelDTO.mountJewel());
        URI uri = uriBuilder.path("/api/v1/joia/buscar/{id}").buildAndExpand(jewel.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/joia/buscar/{id}")
    public ResponseEntity<Jewel> getJewel(
            @PathVariable UUID id
    ) throws JewelNotFoundException {
        Jewel jewel = jewelService.findJewelById(id);

        return ResponseEntity.ok(jewel);
    }

    @GetMapping("/joias")
    public ResponseEntity<List<Jewel>> getJewels() {
        List<Jewel> jewels = jewelService.listJewels();
        return ResponseEntity.ok(jewels);
    }

    @PutMapping("/joia/atualizar")
    public ResponseEntity<Void> updateJewel(
            @RequestParam(required = false) UUID cod_identificacao,
            @Valid @RequestBody CreateOrUpdateJewelDTO updateJewelDTO
    ) throws JewelNotFoundException, IdIsRequiredException {
        if (cod_identificacao == null) {
            throw new IdIsRequiredException("cod_identificacao is required.");
        }
        jewelService.findJewelById(cod_identificacao);

        Jewel jewel = updateJewelDTO.mountJewel();
        jewel.setId(cod_identificacao);
        jewelService.createOrUpdateJewel(jewel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/joia/excluir")
    public ResponseEntity<Void> deleteJewel(
            @RequestParam(required = false) UUID cod_identificacao
    ) throws JewelNotFoundException, IdIsRequiredException {
        if (cod_identificacao == null) {
            throw new IdIsRequiredException("cod_identificacao is required.");
        }
        jewelService.deleteJewel(cod_identificacao);
        return ResponseEntity.noContent().build();
    }
}
