package sin.backend.repository.board;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sin.backend.domain.Board;

public interface SpringDataJpaOracleBoardRepository extends JpaRepository<Board,Long> {
    Page<Board> findByOrderBySeqDesc(Pageable pageable); //페이징기능

}
