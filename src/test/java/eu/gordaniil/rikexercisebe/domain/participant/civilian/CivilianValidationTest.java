package eu.gordaniil.rikexercisebe.domain.participant.civilian;

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

public class CivilianValidationTest extends BaseTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    private final List<TestCase<CivilianDto>> testCases = new ArrayList<>();

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
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("12345678901")
                                    .paymentType("CASH")
                                    .additionalInfo("Person")
                                    .build(),
                            "Valid request data, should have no issues.",
                            0
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("12345678901")
                                    .paymentType("CASH")
                                    .build(),
                            "Valid request data, additional info is missing, should have no issues.",
                            0
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("12345678901")
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, firstName is empty, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName(null)
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("12345678901")
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, firstName is null, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("")
                                    .personalIdentityNumber("12345678901")
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, lastName is empty, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName(null)
                                    .personalIdentityNumber("12345678901")
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, lastName is null, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("")
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, personalIdentityNumber is empty and invalid pattern, should have two issues.",
                            2
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("123")
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, personalIdentityNumber is too short, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("123456789890112")
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, personalIdentityNumber is too long, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("1234567890a")
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, personalIdentityNumber has letters inside, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber(null)
                                    .paymentType("CASH")
                                    .build(),
                            "Invalid request data, personalIdentityNumber is null, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("12345678901")
                                    .paymentType("")
                                    .build(),
                            "Invalid request data, paymentType is empty, should have one issue.",
                            1
                    ),
                    new TestCase<>(
                            CivilianDto.builder()
                                    .firstName("Daniil")
                                    .lastName("Gorohhov")
                                    .personalIdentityNumber("12345678901")
                                    .paymentType(null)
                                    .build(),
                            "Invalid request data, paymentType is null, should have one issue.",
                            1
                    )
                )
        );
    }

}
