package sin.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sin.backend.domain.Address;
import sin.backend.domain.Board;
import sin.backend.service.board.BoardAjaxService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("rest_board")
@RestController
public class BoardRestController {
    private BoardAjaxService boardAjaxService;

    //(1) Create
    @PostMapping("create1")
    public void create1(Board board){
        System.out.println("BoardRestController create1() board: "+board);
        boardAjaxService.insertS(board);
    }
    @PostMapping("create2")
    public void create2(@RequestBody Board board){
        boardAjaxService.insertS(board);
    }
    @GetMapping("readList")
    public List<Board> read(){
        List<Board> list = boardAjaxService.listS();
        return list;
    }
    @GetMapping("read/{seq}")
    public Board read(@PathVariable long seq){
        Board board = boardAjaxService.getBySeqS(seq);
        return board;
    }
    @DeleteMapping("delete/{seq}")
    public void delete(@PathVariable long seq){
        boardAjaxService.deleteS(seq);
    }
    @PutMapping("update")
    public void updateS(@RequestBody Board board){
        boardAjaxService.updateS(board);
    }
}
