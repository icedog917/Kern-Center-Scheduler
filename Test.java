import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Test {
    private static ArrayList<Employee> employees;

    @BeforeEach
    void setUp() {
        try (Scanner scanner = new Scanner(new File("responses.csv"))) {
            employees = Main.fileLoad(scanner);
        }catch(FileNotFoundException e){
            System.err.println("The file could not be found!");
        }
    }

    @AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void testFirstName() {
        String firstName = employees.get(0).getName();
        assertEquals("Lexi Such", firstName);
    }

    @org.junit.jupiter.api.Test
    void testIsAvailableTrue() {
        int hour = 15;
        assertTrue(employees.get(0).isAvailable(Employee.Day.M, hour));
    }

    @org.junit.jupiter.api.Test
    void testIsAvailableFalse() {
        int hour = 23;
        assertFalse(employees.get(0).isAvailable(Employee.Day.F, hour));
    }
}
