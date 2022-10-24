package org.example;

public class CamelCaseConverter {
    public String converter(String name) {

        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
