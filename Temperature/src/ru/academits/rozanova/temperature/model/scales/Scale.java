package ru.academits.rozanova.temperature.model.scales;

public interface Scale {
    String getName();

    double convertToCelsius(double temperature);

    double convertFromCelsius(double celsiusTemperature);
}
