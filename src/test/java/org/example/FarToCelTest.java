package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;


class FarToCelTest {

    @Test
    public void testFahrenheitToCelsius() {
        Assertions.assertEquals(0, FarToCel.fahrenheitToCelsius(32), 0.01);
        Assertions.assertEquals(100, FarToCel.fahrenheitToCelsius(212), 0.01);
    }

    @Test
    void testCelsiusToFahrenheit() {
        Assertions.assertEquals(32, FarToCel.celsiusToFahrenheit(0), 0.01);
        Assertions.assertEquals(212, FarToCel.celsiusToFahrenheit(100), 0.01);
    }

    @Test
    void testKelvinToCel() {
        Assertions.assertEquals(0, FarToCel.kelvintoCel(273.15f), 0.01);
        Assertions.assertEquals(100, FarToCel.kelvintoCel(373.15f), 0.01);
    }

    // New test case for Kelvin to Fahrenheit conversion
    @Test
    void testKelvinToFahrenheit() {
        Assertions.assertEquals(80.51, FarToCel.kelvinToFahrenheit(300.1f), 0.1 );
    }

}