package sin.backend.service;

import sin.backend.domain.MemberLogin;
import sin.backend.repository.MemberLoginRepository;

public interface MemberLoginService {
    int check(String email, String pwd);
    MemberLogin getLogin(String email);
}
