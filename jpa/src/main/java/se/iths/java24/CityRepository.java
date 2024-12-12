package se.iths.java24;

public class CityRepository {

    public void saveCity(City city) {
        JPAUtil.inTransaction(em -> em.persist(city));
    }
}
