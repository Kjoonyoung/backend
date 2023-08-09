package sin.backend.service.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sin.backend.domain.Board;
import sin.backend.dto.BoardListResult;
import sin.backend.repository.board.SpringDataJpaOracleBoardRepository;


@Service
@Transactional
//@Transactional(readOnly = true) //DML 커밋을 막음
@RequiredArgsConstructor
public class SpringDataJpaOrclePageBoardService implements PageBoardService {
    @Autowired
    private final SpringDataJpaOracleBoardRepository repository;

    /*
    @Autowired
    public  SpringDataJpaOrclePageBoardService(SpringDataJpaOracleBoardRepository repository){
        this.repository = repository;
    }//다른방법 -> @RequiredArgsConstructor
     */
    @Override
    public Page<Board> findAll(Pageable pageable) {
        return repository.findByOrderBySeqDesc(pageable);
    }

    @Override
    public BoardListResult getBoardListResult(Pageable pageable) {
        Page<Board> list = findAll(pageable);
        int page = pageable.getPageNumber();
        long totalCount = repository.count();
        int size = pageable.getPageSize();

        return new BoardListResult(page,totalCount,size,list);
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
    public void deleteB(long seq) {
        repository.deleteById(seq);
    }
}
