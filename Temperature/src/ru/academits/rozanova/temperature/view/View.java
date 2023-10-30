package ru.academits.rozanova.temperature.view;

import ru.academits.rozanova.temperature.model.converter.TemperatureConverter;
import ru.academits.rozanova.temperature.model.scales.CelsiusScale;
import ru.academits.rozanova.temperature.model.scales.FahrenheitScale;
import ru.academits.rozanova.temperature.model.scales.KelvinScale;
import ru.academits.rozanova.temperature.model.scales.Scale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class View {
    private final TemperatureConverter converter;
    private JFrame frame;
    private JTextField unitFromValue;
    private JComboBox<String> unitsFromList;
    private int selectedUnitIndexFrom;
    private JTextField unitToValue;
    private JComboBox<String> unitsToList;
    private int selectedUnitIndexTo;
    private JButton convertButton;
    private JButton resetButton;

    public View(TemperatureConverter converter) {
        this.converter = converter;
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Temperature converter");

            frame.setSize(420, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(5, 2, 1, 0));

            JPanel panelWithLabelAndComboBoxFrom = new JPanel();
            frame.add(panelWithLabelAndComboBoxFrom);

            JLabel unitFromLabel = new JLabel("Выберите исходную шкалу температуры:");
            panelWithLabelAndComboBoxFrom.add(unitFromLabel);

            ArrayList<Scale> scales = new ArrayList<>(Arrays.asList(new CelsiusScale(), new FahrenheitScale(), new KelvinScale()));

            String[] unitsFrom = new String[scales.size()];
            setUnitsName(unitsFrom, scales);

            unitsFromList = new JComboBox<>(unitsFrom);
            unitsFromList.addItemListener(e -> selectedUnitIndexFrom = unitsFromList.getSelectedIndex());
            panelWithLabelAndComboBoxFrom.add(unitsFromList);

            JPanel panelWithLabelAndValueFrom = new JPanel();
            frame.add(panelWithLabelAndValueFrom);

            JLabel unitFromValueLabel = new JLabel("Введите значение температуры:");
            panelWithLabelAndValueFrom.add(unitFromValueLabel);

            unitFromValue = new JTextField(10);
            panelWithLabelAndValueFrom.add(unitFromValue);

            JPanel panelWithLabelAndComboBoxTo = new JPanel();
            frame.add(panelWithLabelAndComboBoxTo);

            JLabel unitToLabel = new JLabel("Выберите результирующую шкалу температуры:");
            panelWithLabelAndComboBoxTo.add(unitToLabel);

            String[] unitsTo = new String[scales.size()];
            setUnitsName(unitsTo, scales);

            unitsToList = new JComboBox<>(unitsTo);
            unitsToList.addItemListener(e -> selectedUnitIndexTo = unitsToList.getSelectedIndex());
            panelWithLabelAndComboBoxTo.add(unitsToList);

            JPanel panelWithLabelAndValueTo = new JPanel();
            frame.add(panelWithLabelAndValueTo);

            JLabel unitToValueLabel = new JLabel("Результат:");
            panelWithLabelAndValueTo.add(unitToValueLabel);

            unitToValue = new JTextField(10);
            unitToValue.setEditable(false);
            panelWithLabelAndValueTo.add(unitToValue);

            JPanel buttons = new JPanel();
            frame.add(buttons);

            convertButton = new JButton("Конвертировать");

            convertButton.addActionListener(e -> {
                try {
                    String textFrom = unitFromValue.getText();
                    double temperatureFrom = Double.parseDouble(textFrom);
                    double temperatureTo = converter.convert(temperatureFrom, scales.get(selectedUnitIndexFrom), scales.get(selectedUnitIndexTo));

                    unitToValue.setText(String.format("%.2f", temperatureTo));
                } catch (NumberFormatException exception) {
                    showMessageError();
                }
            });

            buttons.add(convertButton);

            resetButton = new JButton("Сброс");

            resetButton.addActionListener(e -> {
                unitFromValue.setText("");
                unitToValue.setText("");
            });

            buttons.add(resetButton);

            frame.setVisible(true);
        });
    }

    private void showMessageError() {
        JOptionPane.showMessageDialog(frame, "Введено некорректное значение температуры.");
    }

    private void setUnitsName(String[] unitsName, ArrayList<Scale> scales) {
        for (int i = 0; i < scales.size(); i++) {
            unitsName[i] = scales.get(i).getName();
        }
    }
}
