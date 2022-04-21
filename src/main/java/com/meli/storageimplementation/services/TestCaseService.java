package com.meli.storageimplementation.services;

import com.meli.storageimplementation.models.TestCase;
import com.meli.storageimplementation.repositories.TestCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestCaseService {

    TestCaseRepository testCaseRepository;

    public void createTestCase(TestCase testCase) {
        testCaseRepository.save(testCase);
    }

}
