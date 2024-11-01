package solvd.inc.service.implementation;

import solvd.inc.dao.CityMapper;
import solvd.inc.dao.implementation.CityMapperImpl;
import solvd.inc.model.City;
import solvd.inc.service.CityService;

import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;

    public CityServiceImpl() {
        this.cityMapper = new CityMapperImpl();
    }

    @Override
    public City createCity(City city) {
        return cityMapper.create(city);
    }

    @Override
    public Optional<City> getCityById(Long id) {
        return cityMapper.getById(id);
    }

    @Override
    public List<City> getAllCities() {
        return cityMapper.findAll();
    }

    @Override
    public void updateCity(City city) {
        cityMapper.update(city);
    }

    @Override
    public void deleteCityById(Long id) {
        cityMapper.deleteById(id);
    }
}
