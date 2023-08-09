package sin.backend.service.board;

import sin.backend.domain.Board;
import sin.backend.mapper.BoardMapper;

import java.util.List;
//@Service
public class MybatisBoardService implements BoardService {
   // @Autowired
    private final BoardMapper mapper;
  //  @Autowired
    public MybatisBoardService(BoardMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public List<Board> listB() {
        return mapper.list();
    }

    @Override
    public Board insertB(Board board) {
        mapper.insertSelectKey(board);
        long seq = board.getSeq();
        board = mapper.selectBySeq(seq);
        return board;
    }

    @Override
    public Board contentB(long seq) {
        mapper.content(seq);
        return mapper.content(seq);
    }

    @Override
    public void updateB(Board board) {
        mapper.update(board);
    }

    @Override
    public boolean deleteB(long seq) {
        mapper.delete(seq);
        return true;
    }
}
