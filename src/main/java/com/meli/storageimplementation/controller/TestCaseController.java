package com.meli.storageimplementation.controller;

import com.meli.storageimplementation.dto.CreateTestCaseDTO;
import com.meli.storageimplementation.models.TestCase;
import com.meli.storageimplementation.services.TestCaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TestCaseController {

    TestCaseService  testCaseService;

    @PostMapping("/test-cases")
    public void testDynamoDB(
            @RequestBody CreateTestCaseDTO testCaseDTO
    ) {
        TestCase testCase = testCaseDTO.mountTestCase();
        testCaseService.createTestCase(testCase);
    }

    @GetMapping("/test-cases")
    public List<TestCase> listTestCases() {
        return testCaseService.listTestCases();
    }

}
