package orlyworld.freattend.repository;

import org.springframework.stereotype.Repository;
import orlyworld.freattend.entity.Admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AdminRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Admin admin) {
        em.persist(admin);
        return admin.getId();
    }

    public Admin find(Long id) {
        return em.find(Admin.class, id);
    }
}
