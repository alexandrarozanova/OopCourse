package ru.academits.rozanova.temperature_main;

import ru.academits.rozanova.temperature.model.converter.TemperatureConverter;
import ru.academits.rozanova.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        TemperatureConverter converter = new TemperatureConverter();

        View view = new View(converter);
        view.run();
    }
}