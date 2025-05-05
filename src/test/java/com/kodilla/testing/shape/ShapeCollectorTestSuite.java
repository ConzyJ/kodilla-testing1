package com.kodilla.testing.shape;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD: Shape Collector Test Suite")
public class ShapeCollectorTestSuite {

    @Nested
    @DisplayName("Tests for adding and removing figures")
    class AddRemoveTests {

        @Test
        void testAddFigure() {
            // Given
            ShapeCollector collector = new ShapeCollector();
            Shape circle = new Circle(3.0);

            // When
            collector.addFigure(circle);

            // Then
            assertEquals(circle, collector.getFigure(0));
        }

        @Test
        void testRemoveFigure() {
            // Given
            ShapeCollector collector = new ShapeCollector();
            Shape square = new Square(5.0);
            collector.addFigure(square);

            // When
            boolean result = collector.removeFigure(square);

            // Then
            assertTrue(result);
            assertNull(collector.getFigure(0));
        }
    }

    @Nested
    @DisplayName("Tests for getting and showing figures")
    class GetShowTests {

        @Test
        void testGetFigure() {
            // Given
            ShapeCollector collector = new ShapeCollector();
            Shape triangle = new Triangle(3.0, 4.0);
            collector.addFigure(triangle);

            // When
            Shape result = collector.getFigure(0);

            // Then
            assertEquals(triangle, result);
        }

        @Test
        void testShowFigures() {
            // Given
            ShapeCollector collector = new ShapeCollector();
            collector.addFigure(new Square(2.0));
            collector.addFigure(new Circle(3.0));
            collector.addFigure(new Triangle(3.0, 4.0));

            // When
            String result = collector.showFigures();

            // Then
            assertEquals("Square Circle Triangle", result);
        }
    }
}
