package com.meli.storageimplementation.controller;

import com.meli.storageimplementation.dto.CreateOrUpdateJewelDTO;
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
@RequestMapping("/api/v1/jewels")
@AllArgsConstructor
public class JewelController {

    JewelService jewelService;

    @PostMapping
    public ResponseEntity<Void> createJewel(
            @Valid @RequestBody CreateOrUpdateJewelDTO createOrUpdateJewelDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Jewel jewel = jewelService.createOrUpdateJewel(createOrUpdateJewelDTO.mountJewel());
        URI uri = uriBuilder.path("/api/v1/jewels/{id}").buildAndExpand(jewel.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jewel> getJewel(
            @PathVariable UUID id
    ) throws JewelNotFoundException {
        Jewel jewel = jewelService.findJewelById(id);

        return ResponseEntity.ok(jewel);
    }

    @GetMapping
    public ResponseEntity<List<Jewel>> getJewels() {
        List<Jewel> jewels = jewelService.listJewels();
        return ResponseEntity.ok(jewels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJewel(
            @PathVariable UUID id,
            @Valid @RequestBody CreateOrUpdateJewelDTO updateJewelDTO
    ) throws JewelNotFoundException {
        jewelService.findJewelById(id);

        Jewel jewel = updateJewelDTO.mountJewel();
        jewel.setId(id);
        jewelService.createOrUpdateJewel(jewel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJewel(
            @PathVariable UUID id
    ) throws JewelNotFoundException {
        jewelService.deleteJewel(id);
        return ResponseEntity.noContent().build();
    }
}
