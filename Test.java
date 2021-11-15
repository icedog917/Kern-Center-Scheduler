import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Test {

    @BeforeEach
    //No Constructor
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNegativeDelaySeconds() {
        assertThrows(NullPointerException.class, ()->{stopTimeAttributesController.update(-1, 0, new String[3], new String[3]);
        });
    }


}
