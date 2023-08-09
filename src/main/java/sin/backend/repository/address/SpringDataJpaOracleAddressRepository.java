package sin.backend.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import sin.backend.domain.Address;

import java.util.List;

public interface SpringDataJpaOracleAddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByName(String name);//테이블 컬럼에 의존적인 select는 직접 만들어줘야 함
    //JPQL => select a from ADDRESS a where a.name=:name
    List<Address> findByNameAndAddr(String name,String addr);//And, Or,.. sql문상 and or 같이

    List<Address> findByNameContaining(String name); //XXXContaining은  sql문상 like 연산자
}
