package ru.academits.rozanova.temperature.model.scales;

public class CelsiusScale implements Scale {
    @Override
    public String getName() {
        return "Цельсия";
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature;
    }
}
