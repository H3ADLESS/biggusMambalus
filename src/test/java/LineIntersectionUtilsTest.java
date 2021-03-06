import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.h3ad.mamba.math.LineIntersectionUtils;
import de.h3ad.mamba.math.LineSegment;
import de.h3ad.mamba.math.Vector3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineIntersectionUtilsTest {

    public static final int X_OFFSET = 10;
    public static final int Y_OFFSET = 15;

    @Test
    void isBetweenIsFalseOnSameValues() {
        double a = 0.0;
        double b = 0.0;
        double center = 0.0;

        assertFalse(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    void isBetweenIsFalseOnSameStart() {
        double a = 0.0;
        double b = 1.0;
        double center = 0.0;

        assertFalse(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    void isBetweenIsFalseOnSameEnd() {
        double a = 0.0;
        double b = 1.0;
        double center = 1.0;

        assertFalse(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    void isBetweenIsTrueOnInBetween() {
        double a = 0.0;
        double b = 1.0;
        double center = 0.5;

        assertTrue(LineIntersectionUtils.isBetween(center, a, b));
    }

    @Test
    void isBetweenIsFalseOnOutsideValue() {
        double a = 0.0;
        double b = 1.0;
        double center = 1.5;

        assertFalse(LineIntersectionUtils.isBetween(center, a, b));
    }

    @ParameterizedTest
    @MethodSource("createWordsWithLength")
    void test(String testString, int length) {
        assertEquals(testString.length(), length);
    }

    private static Stream<Arguments> createWordsWithLength() {
        return Stream.of(
                Arguments.of("Hello", 5),
                Arguments.of("Fun", 3)
        );
    }

    @Test
    void intersectionOf_should_return_a_vector_if_given_a_vertical_and_horizontal_line() {
        final LineSegment verticalLineSegment = new LineSegment(new Vector3(10, 12), new Vector3(10, 20));
        final LineSegment horizontalLineSegment = new LineSegment(new Vector3(5, 15), new Vector3(15, 15));
        final Vector3 intersection1 = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(verticalLineSegment, horizontalLineSegment);
        final Vector3 intersection2 = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(horizontalLineSegment, verticalLineSegment);

        assertNotNull(intersection1);
        assertEquals(intersection1, intersection2);
        assertEquals(intersection1.getX(), X_OFFSET);
        assertEquals(intersection1.getY(), Y_OFFSET);
    }

    @Test
    void intersectionOf_should_return_null_if_given_a_vertical_and_horizontal_line_which_do_not_cross() {
        final LineSegment verticalLineSegment = new LineSegment(new Vector3(X_OFFSET, 4), new Vector3(X_OFFSET, 8));
        final LineSegment horizontalLineSegment = new LineSegment(new Vector3(30, Y_OFFSET), new Vector3(35, Y_OFFSET));
        final Vector3 intersection1 = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(verticalLineSegment, horizontalLineSegment);
        final Vector3 intersection2 = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(horizontalLineSegment, verticalLineSegment);

        assertNull(intersection1);
        assertNull(intersection2);
    }

    @Test
    void horizontalLinesCrossWhenLeftToRight() {
        // ab contains c in:
        // a -- c -- b -- d

        final LineSegment left = new LineSegment(new Vector3(0, Y_OFFSET), new Vector3(2, Y_OFFSET));
        final LineSegment right = new LineSegment(new Vector3(1, Y_OFFSET), new Vector3(3, Y_OFFSET));
        final Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(left, right);

        assertNotNull(intersection);
        assertEquals(intersection.getX(), 1.0);
        assertEquals(intersection.getY(), Y_OFFSET);
    }

    @Test
    void horizontalLinesCrossWhenRightToLeft() {
        // ab contains d in:
        // a -- d -- b -- c

        final LineSegment left = new LineSegment(new Vector3(0, Y_OFFSET), new Vector3(2, Y_OFFSET));
        final LineSegment right = new LineSegment(new Vector3(3, Y_OFFSET), new Vector3(1, Y_OFFSET));
        final Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(left, right);

        assertNotNull(intersection);
        assertEquals(intersection.getX(), 1.0);
        assertEquals(intersection.getY(), Y_OFFSET);
    }

    @Test
    void horizontalLinesDoNotCrossButAppend() {
        // both lines sharing point b: ab and bc
        // a -- b -- c

        final Vector3 b = new Vector3(1, Y_OFFSET);
        final LineSegment left = new LineSegment(new Vector3(0, Y_OFFSET), b);
        final LineSegment right = new LineSegment(b, new Vector3(2, Y_OFFSET));
        final Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(left, right);

        assertNull(intersection);
    }

    @Test
    void horizontalLinesDoNotCross() {
        // ab and cd got a distance greater than zero
        // a -- b -- c -- d

        final LineSegment left = new LineSegment(new Vector3(0, Y_OFFSET), new Vector3(1, Y_OFFSET));
        final LineSegment right = new LineSegment(new Vector3(2, Y_OFFSET), new Vector3(3, Y_OFFSET));
        final Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(left, right);
        assertNull(intersection);
    }

    @Test
    void verticalLinesCrossWhenTopToBottom() {
        // ab contains c in:
        // a
        // |
        // c
        // |
        // b
        // |
        // d

        final LineSegment top = new LineSegment(new Vector3(X_OFFSET, 0), new Vector3(X_OFFSET, 2));
        final LineSegment bottom = new LineSegment(new Vector3(X_OFFSET, 1), new Vector3(X_OFFSET, 3));
        final Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(top, bottom);

        assertNotNull(intersection);
        assertEquals(intersection.getX(), X_OFFSET);
        assertEquals(intersection.getY(), 1.0);
    }

    @Test
    void verticalLinesCrossWhenRightToLeft() {
        // ab contains d in:
        // a
        // |
        // d
        // |
        // b
        // |
        // c

        final LineSegment top = new LineSegment(new Vector3(X_OFFSET, 0), new Vector3(X_OFFSET, 2));
        final LineSegment bottom = new LineSegment(new Vector3(X_OFFSET, 3), new Vector3(X_OFFSET, 1));
        final Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(top, bottom);

        assertNotNull(intersection);
        assertEquals(intersection.getX(), X_OFFSET);
        assertEquals(intersection.getY(), 1.0);
    }

    @Test
    void verticalLinesDoNotCrossButAppend() {
        // both lines sharing point b: ab and bc
        // a
        // |
        // b
        // |
        // c

        final LineSegment top = new LineSegment(new Vector3(X_OFFSET, 0), new Vector3(X_OFFSET, 2));
        final LineSegment bottom = new LineSegment(new Vector3(X_OFFSET, 2), new Vector3(X_OFFSET, 3));
        final Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(top, bottom);

        assertNull(intersection);
    }

    @Test
    void verticalLinesDoNotCross() {
        // ab and cd got a distance greater than zero
        // a
        // |
        // b
        // |
        // c
        // |
        // d

        final LineSegment top = new LineSegment(new Vector3(X_OFFSET, 0), new Vector3(X_OFFSET, 1));
        final LineSegment bottom = new LineSegment(new Vector3(X_OFFSET, 2), new Vector3(X_OFFSET, 3));
        final Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(top, bottom);

        assertNull(intersection);
    }

    @Test
    void testIsOnPolygonEdge() {
        List<Vector3> polygon = new ArrayList<>();
        polygon.add(new Vector3(0,0));
        polygon.add(new Vector3(0,1));
        polygon.add(new Vector3(1,1));
        polygon.add(new Vector3(1,0));
        polygon.add(new Vector3(0,0));

        Vector3 point = new Vector3(4, 2);

        assertFalse(LineIntersectionUtils.isOnPolygonEdge(new Vector3(4, 2), polygon));
        assertFalse(LineIntersectionUtils.isOnPolygonEdge(new Vector3(-0.000001, 0.5), polygon));
        assertFalse(LineIntersectionUtils.isOnPolygonEdge(new Vector3(1, 1.0001), polygon));

        assertTrue(LineIntersectionUtils.isOnPolygonEdge(new Vector3(1, 1), polygon));
    }

    @Test
    void testIsInsidePolygon() {
        List<Vector3> polygon = new ArrayList<>();
        polygon.add(new Vector3(5.0,5.0));
        polygon.add(new Vector3(795.0,5.0));
        polygon.add(new Vector3(795.0,595.0));
        polygon.add(new Vector3(5.0,595.0));
        polygon.add(new Vector3(5.0,5.0));
        Vector3 movement = new Vector3(0.0, 31.98143999907188);
        Vector3 position = new Vector3(772.1940699976403, 5.0);

        assertTrue(LineIntersectionUtils.isInsidePolygon(position.add(movement), polygon));

    }

    @Test
    void perpendicularLinesAreNextToEachOther() {
        LineSegment horizontal = new LineSegment(new Vector3(0, 50), new Vector3(100,50));
        LineSegment vertical = new LineSegment(new Vector3(150, 0), new Vector3(150,100));

        Vector3 intersection = LineIntersectionUtils.intersectionOfPerpendicularLines(horizontal, vertical);

        Assertions.assertNull(intersection);
    }

    @Test
    void perpendicularLinesAreOneAboveTheOther() {
        LineSegment horizontal = new LineSegment(new Vector3(0, 50), new Vector3(100,50));
        LineSegment vertical = new LineSegment(new Vector3(50, 60), new Vector3(50,100));

        Vector3 intersection = LineIntersectionUtils.intersectionOfPerpendicularLines(horizontal, vertical);

        Assertions.assertNull(intersection);
    }

    @Test
    void perpendicularLinesCross() {
        LineSegment horizontal = new LineSegment(new Vector3(0, 50), new Vector3(100,50));
        LineSegment vertical = new LineSegment(new Vector3(50, 0), new Vector3(50,100));

        Vector3 intersection = LineIntersectionUtils.intersectionOfPerpendicularLines(horizontal, vertical);

        Assertions.assertNotNull(intersection);
        Assertions.assertEquals(new Vector3(50, 50), intersection);
    }

}
