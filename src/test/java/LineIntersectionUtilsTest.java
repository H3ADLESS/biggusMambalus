import de.h3ad.mamba.math.Vector3;
import de.h3ad.mamba.math.LineIntersectionUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineIntersectionUtilsTest {

    @Test
    public void isBetweenIsFalseOnSameValues() {
        double a = 0.0;
        double b = 0.0;
        double center = 0.0;

        assertFalse(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    public void isBetweenIsFalseOnSameStart() {
        double a = 0.0;
        double b = 1.0;
        double center = 0.0;

        assertFalse(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    public void isBetweenIsFalseOnSameEnd() {
        double a = 0.0;
        double b = 1.0;
        double center = 1.0;

        assertFalse(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    public void isBetweenIsTrueOnInBetween() {
        double a = 0.0;
        double b = 1.0;
        double center = 0.5;

        assertTrue(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    public void isBetweenIsFalseOnOutsideValue() {
        double a = 0.0;
        double b = 1.0;
        double center = 1.5;

        assertFalse(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    public void linesCrossAtGivenPositionWhenHorizontalFirst() {
        Vector3 a = new Vector3(10, 10);
        Vector3 b = new Vector3(10, 20);
        Vector3 y = new Vector3(5, 15);
        Vector3 z = new Vector3(15, 15);

        Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(a, b, y, z);

        assertNotNull(intersection);
        assertEquals(intersection.getX(), 10.0);
        assertEquals(intersection.getY(), 15.0);
    }

    @Test
    public void linesCrossAtGivenPositionWhenVerticalFirst() {
        Vector3 a = new Vector3(5, 15);
        Vector3 b = new Vector3(15, 15);
        Vector3 y = new Vector3(10, 10);
        Vector3 z = new Vector3(10, 20);

        Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(a, b, y, z);

        assertNotNull(intersection);
        assertEquals(intersection.getX(), 10.0);
        assertEquals(intersection.getY(), 15.0);
    }

    @Test
    public void horizontalLinesCrossWhenLeftToRight() {
        Vector3 a = new Vector3(0, 15);
        Vector3 b = new Vector3(2, 15);
        Vector3 y = new Vector3(1, 15);
        Vector3 z = new Vector3(3, 15);

        Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(a, b, y, z);

        assertNotNull(intersection);
        assertEquals(intersection.getX(), 1.0);
        assertEquals(intersection.getY(), 15.0);
    }

    @Test
    public void horizontalLinesCrossWhenRightToLeft() {
        Vector3 a = new Vector3(0, 15);
        Vector3 b = new Vector3(2, 15);
        Vector3 y = new Vector3(3, 15);
        Vector3 z = new Vector3(1, 15);

        Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(a, b, y, z);

        assertNotNull(intersection);
        assertEquals(intersection.getX(), 1.0);
        assertEquals(intersection.getY(), 15.0);
    }

    @Test
    public void horizontalLinesDoNotCrossButAppend() {
        Vector3 a = new Vector3(0, 15);
        Vector3 b = new Vector3(1, 15);
        Vector3 y = new Vector3(1, 15);
        Vector3 z = new Vector3(2, 15);

        Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(a, b, y, z);

        assertNull(intersection);
    }

    @Test
    public void horizontalLinesDoNotCross() {
        Vector3 a = new Vector3(0, 15);
        Vector3 b = new Vector3(1, 15);
        Vector3 y = new Vector3(2, 15);
        Vector3 z = new Vector3(3, 15);

        Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(a, b, y, z);

        assertNull(intersection);
    }

}
