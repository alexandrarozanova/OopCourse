package ru.academits.rozanova.temperature.model.converter;

import ru.academits.rozanova.temperature.model.scales.Scale;

public class TemperatureConverter {
    public double convert(double temperature, Scale scaleFrom, Scale scaleTo) {
        double celsiusTemperature = scaleFrom.convertToCelsius(temperature);

        return scaleTo.convertFromCelsius(celsiusTemperature);
    }
}
