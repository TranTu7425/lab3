import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Lab 4 — kiểm thử phương thức {@link Triangle#classify(int, int, int)}.
 */
class TriangleTest {

    /** Bộ 5 test case theo basis path (độc lập path P1–P5). */
    @ParameterizedTest(name = "basis: classify({0}, {1}, {2}) = {3}")
    @CsvSource({
            "0, 1, 1, Invalid",
            "1, 2, 5, Not a triangle",
            "3, 3, 3, Equilateral",
            "3, 3, 4, Isosceles",
            "3, 4, 5, Scalene"
    })
    void classify_basisPaths(int a, int b, int c, String expected) {
        assertEquals(expected, Triangle.classify(a, b, c));
    }

    /** Bổ sung để phủ nhánh con trong biểu thức boolean (JaCoCo branch coverage). */
    @ParameterizedTest(name = "branch: classify({0}, {1}, {2}) = {3}")
    @CsvSource({
            "1, 0, 1, Invalid",
            "1, 1, 0, Invalid",
            "1, 10, 2, Not a triangle",
            "10, 1, 2, Not a triangle",
            "4, 5, 5, Isosceles",
            "5, 4, 5, Isosceles"
    })
    void classify_additionalBranches(int a, int b, int c, String expected) {
        assertEquals(expected, Triangle.classify(a, b, c));
    }
}
