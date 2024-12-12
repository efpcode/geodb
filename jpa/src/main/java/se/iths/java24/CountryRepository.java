package se.iths.java24;

import jakarta.persistence.EntityManager;

public class CountryRepository {


    public Country countryWithName(String countryName) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("select c from Country c where c.countryName = :countryName", Country.class)
                .getSingleResult();
    }
}
