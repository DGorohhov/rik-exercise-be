package eu.gordaniil.rikexercisebe.domain.event;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventValidationTest extends BaseTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    private final List<TestCase<EventDto>> testCases = new ArrayList<>();

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
    public void eventPatternValidationTest() {
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
                                EventDto.builder()
                                        .name("RIK Party")
                                        .location("Tallinn, Harjumaa")
                                        .date(LocalDateTime.now().plusMonths(1))
                                        .additionalInfo("Interesting party")
                                        .build(),
                                "Valid request data, should have no issues.",
                                0
                        ),
                        new TestCase<>(
                                EventDto.builder()
                                        .name("RIK Party")
                                        .location("Tallinn, Harjumaa")
                                        .date(LocalDateTime.now().plusMonths(1))
                                        .build(),
                                "Valid request data, optional additional data is missing, should have no issues.",
                                0
                        ),
                        new TestCase<>(
                                EventDto.builder()
                                        .name("")
                                        .location("Tallinn, Harjumaa")
                                        .date(LocalDateTime.now().plusMonths(1))
                                        .additionalInfo("Interesting party")
                                        .build(),
                                "Invalid request data, name is empty, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                EventDto.builder()
                                        .name(null)
                                        .location("Tallinn, Harjumaa")
                                        .date(LocalDateTime.now().plusMonths(1))
                                        .additionalInfo("Interesting party")
                                        .build(),
                                "Invalid request data, name is null, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                EventDto.builder()
                                        .name("RIK Party")
                                        .location("")
                                        .date(LocalDateTime.now().plusMonths(1))
                                        .additionalInfo("Interesting party")
                                        .build(),
                                "Invalid request data, location is empty, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                EventDto.builder()
                                        .name("RIK Party")
                                        .location(null)
                                        .date(LocalDateTime.now().plusMonths(1))
                                        .additionalInfo("Interesting party")
                                        .build(),
                                "Invalid request data, location is null, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                EventDto.builder()
                                        .name("RIK Party")
                                        .location("Tallinn, Harjumaa")
                                        .date(LocalDateTime.now().minusMonths(1))
                                        .additionalInfo("Interesting party")
                                        .build(),
                                "Invalid request data, date is in past, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                EventDto.builder()
                                        .name("RIK Party")
                                        .location("Tallinn, Harjumaa")
                                        .date(null)
                                        .additionalInfo("Interesting party")
                                        .build(),
                                "Invalid request data, date is null, should have one issue.",
                                1
                        ),
                        new TestCase<>(
                                EventDto.builder()
                                        .name("RIK Party")
                                        .location("Tallinn, Harjumaa")
                                        .date(LocalDateTime.now().plusMonths(1))
                                        .additionalInfo("Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem")
                                        .build(),
                                "Invalid request data, additionalInfo has more that 1000 characters, should have one issue.",
                                1
                        )
                )
        );
    }

}
