package com.meli.storageimplementation.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.meli.storageimplementation.models.TestCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TestCaseRepository implements ApplicationRepository<TestCase, String> {

    DynamoDBMapper dynamoDBMapper;

    @Override
    public void save(TestCase testCase) {
        dynamoDBMapper.save(testCase);
    }

    @Override
    public List<TestCase> list() {
        return dynamoDBMapper.scan(TestCase.class, new DynamoDBScanExpression());
    }

    @Override
    public TestCase find(String id) {
        return dynamoDBMapper.load(TestCase.class, id);
    }

    @Override
    public void delete(TestCase testCase) {
        dynamoDBMapper.delete(testCase);
    }
}
