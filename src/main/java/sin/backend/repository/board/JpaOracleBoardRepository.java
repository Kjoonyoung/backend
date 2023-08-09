package sin.backend.repository.board;

import jakarta.persistence.EntityManager;
import sin.backend.domain.Board;

import java.util.List;

//@Repository
public class JpaOracleBoardRepository implements BoardRepository {
   // @Autowired
    private final EntityManager em;

    public JpaOracleBoardRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public List<Board> list() {
        List<Board> list = em.createQuery("select a from Board a", Board.class)
                .getResultList();

        return list;
    }

    @Override
    public Board insert(Board board) {
        em.persist(board);

        return board;
    }

    public List<Board> findByName(String name) {
        List<Board> list = em.createQuery("select a from Board a where a.name=:name", Board.class)
                .setParameter("name", name)//왜 setParameter을 하냐면 변수 name으로 select문 안에 name(String값)에 대입불가
                .getResultList();
        return list;
    }

    public Board findBySeq(long seq) {//필요에 의해 추가되는 메서드(address를 리턴하기 위해서)
        Board board = em.find(Board.class, seq);//하나의 address가 튀어나온다

        return board;
    }

    @Override
    public Board content(long seq) {
        Board board = em.find(Board.class, seq);
        return board;
    }

    @Override
    public void update(Board board) {
        em.createQuery("UPDATE Board b SET b.email = :email, b.subject = :subject, b.content = :content WHERE b.seq = :seq")
                .setParameter("email", board.getEmail())
                .setParameter("subject", board.getSubject())
                .setParameter("content", board.getContent())
                .setParameter("seq", board.getSeq())
                .executeUpdate();

    }

    @Override
    public boolean delete(long seq) {
        Board board = findBySeq(seq);
        em.remove(board);

        return true;
    }
}