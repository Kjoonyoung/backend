package sin.backend.service.address;

import jakarta.transaction.Transactional;
import sin.backend.domain.Address;
import sin.backend.repository.address.AddressRepository;

import java.util.List;
//@Service
@Transactional //추가( JPA는 트랜젝션안에서 사용해야 함 )
public class JpaAddressService implements AddressService {
    //@Autowired
    private final AddressRepository repository;
    //@Autowired
    public JpaAddressService(AddressRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Address> listS() {
        pln("@listS() by jpa");
        return repository.list();
    }

    @Override
    public Address insertS(Address address) {
        pln("@insertS() by jpa: (Before) address: "+ address);
        address = repository.insert(address);
        pln("@insertS() by jpa: (After) address: "+ address);
        pln("@insertS() by jpa: seq: " + address.getSeq());
        pln("@insertS() by jpa: rdate: " + address.getRdate());

        return address;
    }
    @Override
    public boolean deleteS(long seq) {
        pln("@deleteS() by jpa");
        return repository.delete(seq);
    }
    void pln(String str){
        System.out.println(str);
    }
}