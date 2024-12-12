package se.iths.java24.repository;

import se.iths.java24.JPAUtil;
import se.iths.java24.entity.City;

public class CityRepository {

    public void saveCity(City city) {
        JPAUtil.inTransaction(em -> em.persist(city));
    }
}
