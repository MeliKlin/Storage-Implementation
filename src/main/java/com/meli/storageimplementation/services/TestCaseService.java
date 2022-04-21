package com.meli.storageimplementation.services;

import com.meli.storageimplementation.errors.TestCaseDoesNotExistsException;
import com.meli.storageimplementation.models.TestCase;
import com.meli.storageimplementation.repositories.TestCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TestCaseService {

    TestCaseRepository testCaseRepository;

    public void createTestCase(TestCase testCase) {
        testCaseRepository.save(testCase);
    }

    public List<TestCase> listTestCases() {
        return testCaseRepository.list();
    }

    public TestCase findTestCase(String id) throws TestCaseDoesNotExistsException {
        Optional<TestCase> testCase = Optional.ofNullable(testCaseRepository.find(id));
        if (testCase.isPresent()) {
            return testCase.get();
        }

        throw new TestCaseDoesNotExistsException("Test case does not exists.");
    }

}
;