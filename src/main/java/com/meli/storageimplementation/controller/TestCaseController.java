package com.meli.storageimplementation.controller;

import com.meli.storageimplementation.dto.CreateTestCaseDTO;
import com.meli.storageimplementation.errors.TestCaseDoesNotExistsException;
import com.meli.storageimplementation.models.TestCase;
import com.meli.storageimplementation.services.TestCaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/test-cases")
public class TestCaseController {

    TestCaseService  testCaseService;

    @PostMapping
    public void testDynamoDB(
            @RequestBody CreateTestCaseDTO testCaseDTO
    ) {
        TestCase testCase = testCaseDTO.mountTestCase();
        testCaseService.createTestCase(testCase);
    }

    @GetMapping
    public List<TestCase> listTestCases() {
        return testCaseService.listTestCases();
    }

    @GetMapping("/{id}")
    public TestCase getTestCase(
            @PathVariable String id
    ) throws TestCaseDoesNotExistsException {
        return testCaseService.findTestCase(id);
    }

}
