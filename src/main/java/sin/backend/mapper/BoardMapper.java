package sin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sin.backend.domain.Board;

import java.util.List;

@Mapper
@Repository
public interface BoardMapper {

    List<Board> list();
    Board selectBySeq(long seq);
    void insertSelectKey(Board board);

    void delete(long seq);

    Board content(long seq);

    void update(Board board);



}
