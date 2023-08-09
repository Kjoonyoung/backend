package sin.backend;
// 수동으로 원하는 파일로 가서 주석처리를 해준다 (//@Service, //Repository)와 같이
// 수동의 장점은 쓰던 서비스를 주석처리 하고 새로운 서비스 객체를 등록해주면 된다
// DI (의존성 주입) --> 완벽하게 역할을 분담해 놓을 수 있다(분업)

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sin.backend.mapper.AddressMapper;
import sin.backend.repository.address.SpringDataJpaOracleAddressRepository;
import sin.backend.repository.board.BoardRepository;
import sin.backend.repository.board.JdbcTemplateOracleBoardRepository;
import sin.backend.service.address.AddressService;
import sin.backend.service.address.SpringDataJpaAddressService;
import sin.backend.service.board.BoardService;
import sin.backend.service.board.JdbcOracleBoardService;

import javax.sql.DataSource;

@Configuration// 해당 클래스가 스프링의 설정 정보를 포함하는 클래스임을 나타내는 역할
public class SpringConfig {
    @Autowired
    DataSource dataSource;//Jdbc + JdbcTemplate
    @Autowired
    EntityManager em; //JPA
    @Autowired
    SpringDataJpaOracleAddressRepository repository; //SpringJpa
    /*
    @Bean// 개발자가 직접 스프링 컨테이너에 빈 객체를 등록할 수 있다
    public AddressRepository addressRepository(){//Jdbc + JdbcTemplate + JPA
        //return new JdbcOracleAddressRepository(dataSource);// 이걸 쓰면 name 내림차순 기준으로 리턴
        //return new JdbcTemplateOracleAddressRepository(dataSource);// 이게 Repository이기 때문에 충돌이 난다
        // 이걸 쓰면 seq 내림차순 기준으로 리턴
        return new JpaOracleAddressRepository(em);
    }*/
    @Autowired AddressMapper mapper; //MyBatis
    @Bean
    public AddressService addressService(){//Jdbc + MyBatis + JPA
        //return new JdbcOracleAddressService(addressRepository());
        //return new MybatisAddressService(mapper);
        //return new JpaAddressService(addressRepository());
        return  new SpringDataJpaAddressService(repository);
    }

    //@Bean
    public BoardRepository boardRepository() {
        //return new JdbcOracleBoardRepository(dataSource);
        return new JdbcTemplateOracleBoardRepository(dataSource);
    }
    //@Bean
    public BoardService boardService() {
        return  new JdbcOracleBoardService(boardRepository());
    }



}
