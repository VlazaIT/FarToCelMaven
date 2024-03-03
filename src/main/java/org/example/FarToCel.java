package org.example;

public class FarToCel {
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    public static float kelvintoCel(float kel){
        return (float) (kel-273.15);
    }

    // New method for converting Kelvin to Fahrenheit
    public static double kelvinToFahrenheit(float kelvin) {return (kelvin - 273.15) * 9.0 / 5 + 32;}
}