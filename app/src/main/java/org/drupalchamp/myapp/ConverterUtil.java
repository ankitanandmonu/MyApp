package org.drupalchamp.myapp;

/**
 * Created by ANKIT ANAND
 * Date: 2/26/2016
 * Time: 1:24 PM
 */
public class ConverterUtil {
    // converts to celsius
    public static float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    // converts to fahrenheit
    public static float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }
}

