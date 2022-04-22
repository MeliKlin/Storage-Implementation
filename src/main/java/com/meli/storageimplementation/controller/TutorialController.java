package com.meli.storageimplementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.storageimplementation.dto.CreateTutorialDTO;
import com.meli.storageimplementation.repository.TutorialRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tutorials")
public class TutorialController {

    TutorialRepository tutorialRepository;
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
}
