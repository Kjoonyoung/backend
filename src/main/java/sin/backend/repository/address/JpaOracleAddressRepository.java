package sin.backend.repository.address;

import jakarta.persistence.EntityManager;
import sin.backend.domain.Address;

import java.util.List;

//@Repository
public class JpaOracleAddressRepository implements AddressRepository {
    //@Autowired
    private final EntityManager em;
    //@Autowired
    public JpaOracleAddressRepository(EntityManager em){
        this.em = em;
    }
    @Override
    public List<Address> list() {
        List<Address> list = em.createQuery("select a from Address a", Address.class)
                .getResultList();
        return list;
    }
    public List<Address> findByName(String name) {
        List<Address> list = em.createQuery("select a from Address a where a.name=:name", Address.class)
                .setParameter("name", name)
                .getResultList();
        return list;
    }
    public Address findBySeq(long seq){
        Address address = em.find(Address.class, seq);
        return address;
    }
    @Override
    public Address insert(Address address) { //insert, update, delete 기능은 jpql이 필요없음
        em.persist(address);
        return address;
    }

    @Override
    public boolean delete(long seq) { //interface에 void delete(long seq) 또는 Address delete(long seq)할껄ㅠㅠ
        Address address = findBySeq(seq);
        em.remove(address);
        return true;
    }
}