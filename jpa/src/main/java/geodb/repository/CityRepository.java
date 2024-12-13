package geodb.repository;

import geodb.JPAUtil;
import geodb.entity.City;

import java.util.List;

import static geodb.JPAUtil.inTransaction;

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
