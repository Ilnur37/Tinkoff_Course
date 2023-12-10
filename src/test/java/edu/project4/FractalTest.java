package edu.project4;

import edu.project4.render.AffineTransformation;
import edu.project4.render.OneThreadRender;
import edu.project4.render.Transformation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FractalTest {
    @Test
    void randomPoint() {
        double xMax = 1.77;
        double xMin = -1.77;
        double yMax = 1;
        double yMin = -1;
        OneThreadRender oneThreadRender = new OneThreadRender();
        for (int i = 0; i < 1000; i++) {
            Point point = oneThreadRender.randomPoint();
            Assertions.assertTrue(point.x() < xMax && point.x() > xMin
                && point.y() < yMax && point.y() > yMin);
        }
    }

    @Test
    void affineTransformation() {
        double max = 1.5;
        double min = -1.5;
        double maxColor = 256;
        double minColor = -1;
        for (int i = 0; i < 1000; i++) {
            AffineTransformation affineTransformation = new AffineTransformation();
            Assertions.assertAll(
                () -> Assertions.assertTrue(
                    affineTransformation.red < maxColor && affineTransformation.red > minColor),
                () -> Assertions.assertTrue(
                    affineTransformation.blue < maxColor && affineTransformation.blue > minColor),
                () -> Assertions.assertTrue(
                    affineTransformation.green < maxColor && affineTransformation.green > minColor),
                () -> {
                    for (double x : affineTransformation.affine) {
                        Assertions.assertTrue(x < max && x > min);
                    }
                }
            );
        }
    }



    @Test
    void getTransformations_StringInput() {
        List<String> strings = new ArrayList<>(List.of("power", "heart", "waves", "spiral"));
        Assertions.assertEquals(strings, Transformation.getTransformations(strings, strings.size()));
    }

    @Test
    void getTransformations_NumInput() {
        int num = 8;
        Assertions.assertEquals(num, Transformation.getTransformations(Collections.emptyList(), num).size());
    }

    @Test
    void getTransformations_StringAndNumInput() {
        List<String> strings = new ArrayList<>(List.of("power", "heart", "waves", "spiral"));
        int num = 8;
        List<String> res = Transformation.getTransformations(strings, num);
        Assertions.assertAll(
            () -> Assertions.assertEquals(num, res.size()),
            () -> Assertions.assertTrue(res.containsAll(strings))
        );
    }

    @Test
    void getTransformations_EmptyStringInput() {
        List<String> strings = new ArrayList<>();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Transformation.getTransformations(strings, 0);
        }, "Arguments is valid");

        Assertions.assertEquals(
            "The list of transformations is empty, incorrect parameters have been entered!",
            thrown.getMessage()
        );
    }

    @Test
    void getTransformations_BigNumInput() {
        List<String> strings = new ArrayList<>();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Transformation.getTransformations(strings, 1000);
        }, "Arguments is valid");

        Assertions.assertEquals(
            "The number of requested transformations is greater than the number of implemented ones!",
            thrown.getMessage()
        );
    }

}
