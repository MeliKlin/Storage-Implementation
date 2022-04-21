package com.meli.storageimplementation.controller;

import com.meli.storageimplementation.dto.CreateJewelDTO;
import com.meli.storageimplementation.errors.JewelNotFoundException;
import com.meli.storageimplementation.models.Jewel;
import com.meli.storageimplementation.service.JewelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/jewels")
@AllArgsConstructor
public class JewelController {

    JewelService jewelService;

    @PostMapping
    public ResponseEntity<Void> createJewel(
            @Valid @RequestBody CreateJewelDTO createJewelDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Jewel jewel = jewelService.createJewel(createJewelDTO.mountJewel());
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

}
