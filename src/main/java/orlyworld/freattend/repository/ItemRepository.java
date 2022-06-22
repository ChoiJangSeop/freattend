package orlyworld.freattend.repository;

import org.springframework.stereotype.Repository;
import orlyworld.freattend.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public Item find(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i")
                .getResultList();
    }
}
