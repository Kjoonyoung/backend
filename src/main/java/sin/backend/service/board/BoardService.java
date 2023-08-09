package sin.backend.service.board;

import sin.backend.domain.Board;

import java.util.List;

public interface BoardService {
    List<Board> listB();
    Board insertB(Board board);

    Board contentB(long seq);

    void updateB(Board board);

    boolean deleteB(long seq);

}
