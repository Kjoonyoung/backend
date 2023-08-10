package sin.backend.service.board;

import sin.backend.domain.Board;

import java.util.List;

public interface BoardAjaxService {
    List<Board> listS();
    Board insertS(Board board);
    Board contentS(long seq);
    void updateS(Board board);
    boolean deleteS(long seq);


    Board getBySeqS(long seq);
    List<Board> getListByWriter(String writer);
}
