package sin.backend.service.address;
// 구현클래스(Service), 여기를 쓰는것은 전적으로 view페이지에 달려있다

import sin.backend.domain.Address;
import sin.backend.repository.address.AddressRepository;

import java.util.List;

//@Service
public class JdbcOracleAddressService implements AddressService {
    private final AddressRepository repository;
    //@Autowired
    public JdbcOracleAddressService(AddressRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Address> listS() {
        return repository.list();
    }
    @Override
    public Address insertS(Address address) {
        return repository.insert(address);
    }
    @Override
    public boolean deleteS(long seq) {
        return repository.delete(seq);
    }
}
