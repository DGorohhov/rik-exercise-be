package eu.gordaniil.rikexercisebe.domain.participant.company;

import eu.gordaniil.rikexercisebe.BaseTest;
import lombok.AllArgsConstructor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanyValidationTest extends BaseTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    private final List<TestCase<CompanyDto>> testCases = new ArrayList<>();

    @BeforeClass
    public static void setupValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Before
    public void setup() {
        addTestCases();
    }

    @Test
    public void civilianPatternValidationTest() {
        testCases.forEach(tc -> {
            var violations = validator.validate(tc.dto);
            Assertions.assertEquals(tc.failedFields, violations.size(), tc.description + tc.dto);
        });
    }

    @AllArgsConstructor
    static class TestCase<T> {
        T dto;
        String description;
        int failedFields;
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    private void addTestCases() {
        testCases.addAll(
                Arrays.asList(
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("Nullam OY")
                                        .registerCode("12345678")
                                        .numberOfParticipants(10)
                                        .paymentType("CASH")
                                        .additionalInfo("Info")
                                        .build(),
                                "Valid request data, should have no issues.",
                                0
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("Nullam OY")
                                        .registerCode("12345678")
                                        .numberOfParticipants(10)
                                        .paymentType("CASH")
                                        .build(),
                                "Valid request data, additionalInfo is missing, should have no issues.",
                                0
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("")
                                        .registerCode("12345678")
                                        .numberOfParticipants(10)
                                        .paymentType("CASH")
                                        .build(),
                                "Invalid request data, legalName is empty, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName(null)
                                        .registerCode("12345678")
                                        .numberOfParticipants(10)
                                        .paymentType("CASH")
                                        .build(),
                                "Invalid request data, legalName is null, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("Nullam OY")
                                        .registerCode("")
                                        .numberOfParticipants(10)
                                        .paymentType("CASH")
                                        .build(),
                                "Invalid request data, registerCode is empty and invalid pattern, should have two issues.",
                                2
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("Nullam OY")
                                        .registerCode(null)
                                        .numberOfParticipants(10)
                                        .paymentType("CASH")
                                        .build(),
                                "Invalid request data, registerCode is null, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("Nullam OY")
                                        .registerCode("12345678")
                                        .numberOfParticipants(null)
                                        .paymentType("CASH")
                                        .build(),
                                "Invalid request data, numberOfParticipants is null, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("Nullam OY")
                                        .registerCode("12345678")
                                        .numberOfParticipants(0)
                                        .paymentType("CASH")
                                        .build(),
                                "Invalid request data, numberOfParticipants is zero, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("Nullam OY")
                                        .registerCode("12345678")
                                        .numberOfParticipants(10)
                                        .paymentType("")
                                        .build(),
                                "Invalid request data, paymentType is empty, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                CompanyDto.builder()
                                        .legalName("Nullam OY")
                                        .registerCode("12345678")
                                        .numberOfParticipants(10)
                                        .paymentType(null)
                                        .build(),
                                "Invalid request data, paymentType is null, should have one issue.",
                                1
                        )
                )
        );
    }

}
