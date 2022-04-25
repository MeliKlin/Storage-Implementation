package com.meli.storageimplementation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.storageimplementation.dto.CreateOrUpdateTutorialDTO;
import com.meli.storageimplementation.entities.Tutorial;
import com.meli.storageimplementation.error.TutorialNotFoundException;
import com.meli.storageimplementation.repository.TutorialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TutorialService {

    TutorialRepository tutorialRepository;
    ObjectMapper objectMapper;

    public void saveTutorial(UUID id, CreateOrUpdateTutorialDTO tutorial) throws JsonProcessingException {
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

    public Tutorial findTutorialById(UUID id) throws JsonProcessingException, TutorialNotFoundException {
        String tutorialRawData = tutorialRepository.findById(id.toString());
        if (tutorialRawData == null) {
            throw new TutorialNotFoundException("Tutorial does not exists");
        }
        return objectMapper.readValue(tutorialRawData, Tutorial.class);
    }

    public void updateTutorial(UUID id, CreateOrUpdateTutorialDTO updateTutorialDTO) throws JsonProcessingException, TutorialNotFoundException {
        Optional<Tutorial> tutorial = listTutorials().stream().filter(t -> t.getId().equals(id)).findFirst();
        if (tutorial.isEmpty()) {
            throw new TutorialNotFoundException("Tutorial does not exists");
        }
        saveTutorial(id, updateTutorialDTO);
    }

    public void deleteTutorial(UUID id) throws JsonProcessingException, TutorialNotFoundException {
        Optional<Tutorial> tutorial = listTutorials().stream().filter(t -> t.getId().equals(id)).findFirst();
        if (tutorial.isEmpty()) {
            throw new TutorialNotFoundException("Tutorial does not exists");
        }
        tutorialRepository.delete(id.toString());
    }

    public List<Tutorial> listPublished() throws JsonProcessingException {
        return listTutorials().stream().filter(Tutorial::isPublished).collect(Collectors.toList());
    }

    public List<Tutorial> listByTitle(String title) throws JsonProcessingException {
        return listTutorials().stream().filter(t -> t.getTitle().contains(title)).collect(Collectors.toList());
    }
}
