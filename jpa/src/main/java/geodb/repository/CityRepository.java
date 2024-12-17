package geodb.repository;

import geodb.entity.City;

import java.util.List;

public class CityRepository implements Repository<City, Integer> {

    @Override
    public City save(City city) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<City> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public City findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
