package solvd.inc;

import solvd.inc.model.City;
import solvd.inc.service.CityService;
import solvd.inc.service.implementation.CityServiceImpl;
import solvd.inc.ui.CityMap;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CityService cityService = new CityServiceImpl();

        List<City> cities = cityService.getAllCities();
        cities.forEach(System.out::println);

        System.out.println(cityService.getCityById(5L));

        CityMap cityMap = new CityMap();
        cityMap.displayCities(cities);

    }
}
