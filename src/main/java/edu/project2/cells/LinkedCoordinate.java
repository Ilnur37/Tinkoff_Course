package edu.project2.cells;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LinkedCoordinate{
    private final Coordinate coordinate;
    private final LinkedCoordinate parent;
}
