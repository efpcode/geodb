package se.iths.java24.repository;

import se.iths.java24.JPAUtil;
import se.iths.java24.entity.City;

import java.util.List;

import static se.iths.java24.JPAUtil.inTransaction;

public class CityRepository {

    public void saveCity(City city) {
        inTransaction(em -> em.persist(city));
    }

    public List<City> allCitiesWithMoreThan(int minpopulation) {
        return JPAUtil.getEntityManager()
                .createQuery("select c from City c where c.population > :minpopulation",
                        City.class)
                .setParameter("minpopulation", minpopulation)
                .getResultList();
    }
}
