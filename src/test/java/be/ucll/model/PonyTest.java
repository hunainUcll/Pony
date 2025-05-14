package be.ucll.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PonyTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator= validatorFactory.getValidator();
    }
    @AfterAll
    public static void close(){
        validatorFactory.close();
    }

    @Test
    void givenNegativeAge_whenCreatingPony_thenAgeViolationIsThrown(){
        Pony tornado = new Pony("Tornados",-5,130);

        // testing the number of violations
        Set<ConstraintViolation<Pony>> violations = validator.validate(tornado);
        assertEquals(1,violations.size());

        // testing violation message
        ConstraintViolation<Pony> violation = violations.iterator().next(); // Retrieves the single violation from the set
        assertEquals("age cannot be less than 0",violation.getMessage());

    }

    // -- Name validation tests --

    @Test
    void givenBlankName_whenCreatingPony_thenViolationIsThrown() {
        Pony pony = new Pony("   ", 10, 100);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals("the pony name must not be empty", violations.iterator().next().getMessage());
    }

    @Test
    void givenTooShortName_whenCreatingPony_thenViolationIsThrown() {
        Pony pony = new Pony("abc", 10, 100);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals("the name must be between 4 and 20 characters", violations.iterator().next().getMessage());
    }

    @Test
    void givenTooLongName_whenCreatingPony_thenViolationIsThrown() {
        Pony pony = new Pony("Supercalifragilisticexpialidocious", 10, 100);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals("the name must be between 4 and 20 characters", violations.iterator().next().getMessage());
    }

    // -- Age validation tests --

    @Test
    void givenNegativeAge_whenCreatingPony_thenMinViolation() {
        Pony pony = new Pony("Tornado", -1, 100);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals("age cannot be less than 0", violations.iterator().next().getMessage());
    }

    @Test
    void givenTooHighAge_whenCreatingPony_thenMaxViolation() {
        Pony pony = new Pony("Tornado", 101, 100);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals("Age cannot be more than 100", violations.iterator().next().getMessage());
    }

    // -- Size validation tests --

    @Test
    void givenTooSmallSize_whenCreatingPony_thenMinViolation() {
        Pony pony = new Pony("Tornado", 10, 39);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals("a pony cannot be under 40cm", violations.iterator().next().getMessage());
    }

    @Test
    void givenTooLargeSize_whenCreatingPony_thenMaxViolation() {
        Pony pony = new Pony("Tornado", 10, 148);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals(" a pony cannot be over 147 cm", violations.iterator().next().getMessage());
    }

    // -- Multiple violations test --

    @Test
    void givenMultipleInvalidFields_whenCreatingPony_thenAllViolationsAreCaught() {
        Pony pony = new Pony("A", -10, 200); // too short name, negative age, too large size
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals(3, violations.size());
    }

}
