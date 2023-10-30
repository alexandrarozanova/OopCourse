package ru.academits.rozanova.temperature.model.scales;

public class KelvinScale implements Scale {
    @Override
    public String getName() {
        return "Кельвина";
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }
}
