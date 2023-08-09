package sin.backend.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sin.backend.domain.Board;
import sin.backend.repository.board.SpringDataJpaOracleBoardRepository;

import java.util.List;
@Service
public class SpringDataJpaBoardService implements BoardService {
    @Autowired
    private final SpringDataJpaOracleBoardRepository repository;
    @Autowired
    public SpringDataJpaBoardService(SpringDataJpaOracleBoardRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Board> listB() {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"seq"));
    }

    @Override
    public Board insertB(Board board) {
        board = repository.save(board);
        return board;
    }

    @Override
    public Board contentB(long seq) {
        Board board = repository.getById(seq);
        return board;
    }

    @Override
    public void updateB(Board board) {
        Board boardPhase = repository.getById(board.getSeq());
        boardPhase.setWriter(board.getWriter());
        boardPhase.setEmail(board.getEmail());
        boardPhase.setSubject(board.getSubject());
        boardPhase.setContent(board.getContent());

        repository.save(boardPhase);
    }

    @Override
    public boolean deleteB(long seq) {
        repository.deleteById(seq);
        return true;
    }
}

