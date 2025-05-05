package com.kodilla.testing.weather.stub;

import java.util.*;

public class WeatherForecast {

    private Temperatures temperatures;

    public WeatherForecast(Temperatures temperatures) {
        this.temperatures = temperatures;
    }

    public Map<String, Double> calculateForecast() {
        Map<String, Double> resultMap = new HashMap<>();

        for (Map.Entry<String, Double> temperature :
                temperatures.getTemperatures().entrySet()) {

            resultMap.put(temperature.getKey(), temperature.getValue() + 1.0);
        }

        return resultMap;
    }

    public double calculateAverageTemperature() {
        Collection<Double> values = temperatures.getTemperatures().values();
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    public double calculateMedianTemperature() {
        List<Double> values = new ArrayList<>(temperatures.getTemperatures().values());
        Collections.sort(values);
        int size = values.size();
        if (size % 2 == 0) {
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        } else {
            return values.get(size / 2);
        }
    }
}
