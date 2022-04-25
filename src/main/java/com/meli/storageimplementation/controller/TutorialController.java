package com.meli.storageimplementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.storageimplementation.dto.CreateOrUpdateTutorialDTO;
import com.meli.storageimplementation.entities.Tutorial;
import com.meli.storageimplementation.error.TutorialNotFoundException;
import com.meli.storageimplementation.service.TutorialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tutorials")
public class TutorialController {

    TutorialService tutorialService;
    ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<Void> createTutorial(
            @Valid @RequestBody CreateOrUpdateTutorialDTO createOrUpdateTutorialDTO,
            UriComponentsBuilder uriBuilder
    ) throws JsonProcessingException {
        UUID id = UUID.randomUUID();

        tutorialService.saveTutorial(id, createOrUpdateTutorialDTO);
        URI uri = uriBuilder.path("/api/tutorials/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Tutorial>> listTutorials() throws JsonProcessingException {
        return ResponseEntity.ok(tutorialService.listTutorials());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> findTutorial(
            @PathVariable UUID id
    ) throws JsonProcessingException, TutorialNotFoundException {
        return ResponseEntity.ok(tutorialService.findTutorialById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTutorial(
            @PathVariable UUID id,
            @Valid @RequestBody CreateOrUpdateTutorialDTO updateTutorialDTO,
            UriComponentsBuilder uriBuilder
    ) throws TutorialNotFoundException, JsonProcessingException {
        tutorialService.updateTutorial(id, updateTutorialDTO);
        URI uri = uriBuilder.path("/api/tutorials/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.noContent().location(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutorial(
            @PathVariable UUID id
    ) throws TutorialNotFoundException, JsonProcessingException {
        tutorialService.deleteTutorial(id);
        return ResponseEntity.noContent().build();
    }

}
