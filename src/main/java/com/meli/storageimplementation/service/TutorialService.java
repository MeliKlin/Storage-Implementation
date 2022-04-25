package com.meli.storageimplementation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.storageimplementation.dto.CreateTutorialDTO;
import com.meli.storageimplementation.entities.Tutorial;
import com.meli.storageimplementation.repository.TutorialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TutorialService {

    TutorialRepository tutorialRepository;
    ObjectMapper objectMapper;

    public void createTutorial(UUID id, CreateTutorialDTO tutorial) throws JsonProcessingException {
        tutorialRepository.set(id.toString(), objectMapper.writeValueAsString(tutorial));
    }

    public List<Tutorial> listTutorials() throws JsonProcessingException {
        List<Tutorial> tutorials = new ArrayList<>();

        Map<String, String> tutorialsRawData = tutorialRepository.getAll();
        for (Map.Entry<String, String> entry : tutorialsRawData.entrySet()) {
            Tutorial tutorial = objectMapper.readValue(entry.getValue(), Tutorial.class);
            tutorial.setId(UUID.fromString(entry.getKey()));
            tutorials.add(tutorial);
        }

        return tutorials;
    }

    public Tutorial findTutorialById(UUID id) throws JsonProcessingException {
        String tutorialRawData = tutorialRepository.findById(id.toString());
        return objectMapper.readValue(tutorialRawData, Tutorial.class);
    }

}
