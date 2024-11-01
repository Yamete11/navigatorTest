package solvd.inc.service;

import solvd.inc.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    City createCity(City city);
    Optional<City> getCityById(Long id);
    List<City> getAllCities();
    void updateCity(City city);
    void deleteCityById(Long id);
}
