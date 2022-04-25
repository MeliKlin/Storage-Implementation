package com.meli.storageimplementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.storageimplementation.dto.CreateTutorialDTO;
import com.meli.storageimplementation.entities.Tutorial;
import com.meli.storageimplementation.repository.TutorialRepository;
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

    TutorialRepository tutorialRepository;
    TutorialService tutorialService;
    ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<Void> createTutorial(
            @Valid @RequestBody CreateTutorialDTO createTutorialDTO,
            UriComponentsBuilder uriBuilder
    ) throws JsonProcessingException {
        UUID id = UUID.randomUUID();

        tutorialRepository.set(id.toString(), objectMapper.writeValueAsString(createTutorialDTO));
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
    ) throws JsonProcessingException {
        return ResponseEntity.ok(tutorialService.findTutorialById(id));
    }

}
