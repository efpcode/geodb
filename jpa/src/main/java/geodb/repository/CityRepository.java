package geodb.repository;

import geodb.JPAUtil;
import geodb.entity.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Function;

public class CityRepository implements Repository<City, Integer> {

    @Override
    public City save(City city) {
        return CityRepository.inTransactionWithResult(entityManager -> {
            if (city.getId() == null) {
                entityManager.persist(city);
            } else {
                entityManager.merge(city);
            }
            return city;
        });
    }

    @Override
    public List<City> findAll() {
        return CityRepository.inTransactionWithResult(entityManager ->
                entityManager.createQuery("SELECT c FROM City c", City.class).getResultList());
    }

    @Override
    public City findById(Integer id) {
        return CityRepository.inTransactionWithResult(entityManager -> entityManager.find(City.class, id));
    }

    @Override
    public void deleteById(Integer id) {
        JPAUtil.inTransaction(entityManager -> {
            City city = entityManager.find(City.class, id);
            if (city != null) {
                entityManager.remove(city);
            }
        });
    }

    private static <T> T inTransactionWithResult(Function<EntityManager, T> function) {
        EntityTransaction transaction;
        try (EntityManager em = JPAUtil.getEntityManager()) {
            transaction = em.getTransaction();
            try {
                transaction.begin();
                T result = function.apply(em);
                transaction.commit();
                return result;
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}
