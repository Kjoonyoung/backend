package sin.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sin.backend.domain.MemberLogin;

public interface MemberLoginRepository extends JpaRepository<MemberLogin, Long> {
    MemberLogin findByEmail(String email);
}
