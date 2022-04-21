package com.meli.storageimplementation.controller;

import com.meli.storageimplementation.dto.CreateTestCaseDTO;
import com.meli.storageimplementation.errors.TestCaseDoesNotExistsException;
import com.meli.storageimplementation.models.TestCase;
import com.meli.storageimplementation.services.TestCaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/testcases")
public class TestCaseController {

    TestCaseService  testCaseService;

    @PostMapping("/new")
    public ResponseEntity<Void> testDynamoDB(
            @RequestBody CreateTestCaseDTO testCaseDTO,
            UriComponentsBuilder uriBuilder
    ) {
        TestCase testCase = testCaseDTO.mountTestCase();
        testCaseService.createTestCase(testCase);
        URI uri = uriBuilder.path("/api/v1/testcases/{id}").buildAndExpand(testCase.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<TestCase>> listTestCases(
            @RequestParam(required = false) String last_update
    ) throws ParseException {
        if (last_update == null) {
            System.out.println("ok");
            List<TestCase> testCases = testCaseService.listTestCases();
            return ResponseEntity.ok(testCases);
        }

        Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(last_update);

        String startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date);
        return ResponseEntity.ok(testCaseService.listTestCases(startDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCase> getTestCase(
            @PathVariable String id
    ) throws TestCaseDoesNotExistsException {
        TestCase testCase = testCaseService.findTestCase(id);
        return ResponseEntity.ok(testCase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestCase(
            @PathVariable String id
    ) throws TestCaseDoesNotExistsException {
        testCaseService.deleteTestCase(id);
        return ResponseEntity.noContent().build();
    }

}
