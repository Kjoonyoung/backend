package sin.backend.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import sin.backend.domain.Address;
import sin.backend.repository.address.SpringDataJpaOracleAddressRepository;

import java.util.List;
//@Service
public class SpringDataJpaAddressService implements AddressService {
    @Autowired
    private final SpringDataJpaOracleAddressRepository repository;
    @Autowired
    public SpringDataJpaAddressService(SpringDataJpaOracleAddressRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Address> listS() {
        pln("@listS() by SpringDataJpa");
        //return repository.findAll();
        return repository.findAll(Sort.by(Sort.Direction.DESC,"seq"));
        //return repository.findByName("홍길동");//테스트
        //return repository.findByNameAndAddr("홍길동","평택시");//테스트2
        //return repository.findByNameContaining("홍");//테스트3
    }
    @Override
    public Address insertS(Address address) {
        pln("@insertS() by SpringDataJpa");
        address = repository.save(address);
        pln("@insertS() address: " + address);
        return address;
    }

    @Override
    public boolean deleteS(long seq) {
        pln("@deleteS() by SpringDataJpa");
        repository.deleteById(seq);
        return true;
    }
    public List<Address> findByName(String name){
        List<Address> list = repository.findByName(name);
        pln("@findByName() by SpringDataJpa list: " + list);
        return list;
    }
    public List<Address> findByNameAndAddr(String name, String addr){
        List<Address> list = repository.findByNameAndAddr(name, addr);
        pln("@findByNameAndAddr() by SpringDataJpa list: " + list);
        return list;
    }
    public List<Address> findByNameContaining(String name){
        List<Address> list = repository.findByNameContaining(name);
        pln("@findByNameContaining() by SpringDataJpa list: " + list);
        return list;
    }
    void pln(String str){
        System.out.println(str);
    }
}