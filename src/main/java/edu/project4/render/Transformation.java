package edu.project4.render;

import edu.project4.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

@UtilityClass
@SuppressWarnings("MagicNumber")
public class Transformation {
    private static final Map<String, NonlinearTransformation> NONLINEAR_TRANSFORMATION = new HashMap<>();

    static {
        NONLINEAR_TRANSFORMATION.put("linear", point -> new Point(point.x(), point.y()));
        NONLINEAR_TRANSFORMATION.put("spherical", point -> {
            double denominator = (1.0 / (pow(point.x(), 2) + pow(point.y(), 2)));
            return new Point(point.x() * denominator, point.y() * denominator);
        });
        NONLINEAR_TRANSFORMATION.put("sinusoidal", point -> new Point(sin(point.x()), sin(point.y())));
        NONLINEAR_TRANSFORMATION.put("swirl", point -> {
            double r = pow(point.x(), 2) + pow(point.y(), 2);
            return new Point(
                point.x() * sin(r) - point.y() * cos(r),
                point.x() * sin(r) + point.y() * cos(r)
            );
        });
        NONLINEAR_TRANSFORMATION.put("cross", point -> {
            double r = sqrt(1.0 / pow(pow(point.x(), 2) - pow(point.y(), 2), 2));
            double nextX = point.x() * r;
            double nextY = point.y() * r;
            return new Point(nextX, nextY);
        });
        NONLINEAR_TRANSFORMATION.put("disk", point -> {
            double r = sqrt(pow(point.x(), 2) + pow(point.y(), 2)) * PI;
            double theta = atan2(point.y(), point.x()) / PI;
            double nextX = theta * sin(r);
            double nextY = theta * cos(r);
            return new Point(nextX, nextY);
        });
        NONLINEAR_TRANSFORMATION.put("polar", point -> {
            double nextX = atan2(point.y(), point.x()) / PI;
            double nextY = sqrt(pow(point.x(), 2) + pow(point.y(), 2)) - 1;
            return new Point(nextX, nextY);
        });
        NONLINEAR_TRANSFORMATION.put("heart", point -> {
            double r = sqrt(pow(point.x(), 2) + pow(point.y(), 2));
            double theta = atan2(point.y(), point.x());
            double nextX = r * sin(theta * r);
            double nextY = -r * cos(theta * r);
            return new Point(nextX, nextY);
        });
        NONLINEAR_TRANSFORMATION.put("spiral", point -> {
            double r = sqrt(pow(point.x(), 2) + pow(point.y(), 2));
            double theta = atan2(point.y(), point.x());
            double nextX = 1.0 / r * (cos(theta) + sin(r));
            double nextY = 1.0 / r * (sin(theta) + cos(r));
            return new Point(nextX, nextY);
        });
        NONLINEAR_TRANSFORMATION.put("hyperbolic", point -> {
            double r = sqrt(pow(point.x(), 2) + pow(point.y(), 2));
            double theta = atan2(point.y(), point.x());
            double nextX = sin(theta) / r;
            double nextY = r * cos(theta);
            return new Point(nextX, nextY);
        });
        NONLINEAR_TRANSFORMATION.put("julia", point -> {
            double r = sqrt(sqrt(pow(point.x(), 2) + pow(point.y(), 2)));
            double theta = atan2(point.y(), point.x()) * 0.5;
            if (ThreadLocalRandom.current().nextBoolean()) {
                theta += PI;
            }
            double nextX = r * cos(theta);
            double nextY = r * sin(theta);
            return new Point(nextX, nextY);
        });
        NONLINEAR_TRANSFORMATION.put("waves", point -> {
            double nextX = exp(point.x() - 1) * cos(PI * point.y());
            double nextY = exp(point.x() - 1) * sin(PI * point.y());
            return new Point(nextX, nextY);
        });
        NONLINEAR_TRANSFORMATION.put("power", point -> {
            double r = sqrt(pow(point.x(), 2) + pow(point.y(), 2));
            double theta = atan2(point.y(), point.x());
            double nextX = pow(r, sin(theta)) * cos(theta);
            double nextY = pow(r, sin(theta)) * sin(theta);
            return new Point(nextX, nextY);
        });
    }

    public static List<String> getTransformations(@NonNull List<String> namesTrans, int number) {
        int numberOfTrans = number;
        List<String> listTrans = new ArrayList<>();
        List<String> tempListTrans = new ArrayList<>(NONLINEAR_TRANSFORMATION.keySet());
        if (!namesTrans.isEmpty()) {
            namesTrans.forEach(name -> {
                if (tempListTrans.contains(name)) {
                    listTrans.add(name);
                    tempListTrans.remove(name);
                }
            });
            numberOfTrans -= listTrans.size();
        }
        if (numberOfTrans > tempListTrans.size()) {
            throw new IllegalArgumentException(
                "The number of requested transformations is greater than the number of implemented ones!"
            );
        }
        while (numberOfTrans > 0) {
            int idx = ThreadLocalRandom.current().nextInt(tempListTrans.size());
            listTrans.add(tempListTrans.get(idx));
            tempListTrans.remove(idx);
            --numberOfTrans;
        }

        if (listTrans.isEmpty()) {
            throw new IllegalArgumentException(
                "The list of transformations is empty, incorrect parameters have been entered!"
            );
        }
        return listTrans;
    }

    public static NonlinearTransformation getFunction(String name) {
        return NONLINEAR_TRANSFORMATION.get(name);
    }
}
