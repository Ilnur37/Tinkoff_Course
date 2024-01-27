package edu.hw10.Task1;

import edu.hw10.Task1.annotation.Max;
import edu.hw10.Task1.annotation.Min;
import edu.hw10.Task1.annotation.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MyClass {
    private int num1;
    private double num2;
    private String name;
    private String secondName;

    public MyClass() {
    }

    public MyClass(@Min(1) @Max(2) int num1) {
        this.num1 = num1;
    }

    public MyClass(String name) {
        this.name = name;
    }

    public MyClass(
        @Min(1) @Max(2) int num1,
        @Max(1) double num2
    ) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public MyClass(
        @NotNull String name,
        String secondName
    ) {
        this.name = name;
        this.secondName = secondName;
    }

    public MyClass(
        int num1,
        @Min(1) @Max(2) double num2,
        @NotNull String name,
        String secondName
    ) {
        this.num1 = num1;
        this.num2 = num2;
        this.name = name;
        this.secondName = secondName;
    }

    public static MyClass create(
        @Min(1) @Max(2) int num1,
        double num2,
        @NotNull String name
    ) {
        MyClass myClass = new MyClass();
        myClass.setNum1(num1);
        myClass.setNum2(num2);
        myClass.setName(name);
        return myClass;
    }

    public static MyClass create(
        @Min(1) @Max(2) double num2,
        @NotNull String name
    ) {
        MyClass myClass = new MyClass();
        myClass.setNum2(num2);
        myClass.setName(name);
        return myClass;
    }
}
