package orlyworld.freattend.repository;

import org.springframework.stereotype.Repository;
import orlyworld.freattend.entity.Record;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RecordRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Record record) {
        em.persist(record);
        return record.getId();
    }

    public Record find(Long id) {
        return em.find(Record.class, id);
    }

    public List<Record> findAll() {
        return em.createQuery("select r from record r")
                .getResultList();
    }

    // TODO FIND 동적쿼리

}
