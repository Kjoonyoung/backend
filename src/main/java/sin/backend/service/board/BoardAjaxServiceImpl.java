package sin.backend.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sin.backend.domain.Board;
import sin.backend.repository.address.SpringDataJpaOracleAddressRepository;
import sin.backend.repository.board.SpringDataJpaOracleBoardRepository;

import java.util.List;

@Service
public class BoardAjaxServiceImpl implements BoardAjaxService {
    private final SpringDataJpaOracleBoardRepository repository;
    @Autowired
    public BoardAjaxServiceImpl(SpringDataJpaOracleBoardRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Board> listS() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
    }

    @Override
    public Board insertS(Board board) {
        board = repository.save(board);
        return board;
    }

    @Override
    public Board contentS(long seq) {
        Board board = repository.getById(seq);
        return board;
    }

    @Override
    public void updateS(Board board) {
        Board boardPhase = repository.getById(board.getSeq());
        boardPhase.setWriter(board.getWriter());
        boardPhase.setEmail(board.getEmail());
        boardPhase.setSubject(board.getSubject());
        boardPhase.setContent(board.getContent());

        repository.save(boardPhase);
    }

    @Override
    public boolean deleteS(long seq) {
        repository.deleteById(seq);
        return true;
    }

    @Override
    public Board getBySeqS(long seq) {
        Board board = repository.findById(seq).get();
        return board;
    }

    @Override
    public List<Board> getListByWriter(String writer) {
        List<Board> list = repository.findByWriterContaining(writer);
        return list;
    }
}
