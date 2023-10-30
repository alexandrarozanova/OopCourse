package ru.academits.rozanova.temperature.model.scales;

public class FahrenheitScale implements Scale {
    @Override
    public String getName() {
        return "Фаренгейта";
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * 5 / 9;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return (celsiusTemperature * 9 / 5) + 32;
    }
}
