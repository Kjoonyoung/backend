package sin.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sin.backend.domain.Address;
import sin.backend.service.address.AddressAjaxService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("rest_addr")
@RestController
public class AddressRestController {
    private AddressAjaxService addressAjaxService;

    //(1) Create ( insert )
    //@GetMapping("create1") //테스트 성공
    @PostMapping("create1")
    public void create1(Address address){//파라미터를 jsObj로 받을 때
        System.out.println("AddressRestController create1() address: "+address);
        addressAjaxService.insertS(address);
    }
    @PostMapping("create2")
    public void create2(@RequestBody Address address){ //파라미터를 JSON으로 받을 때 @RequestBody를 써야함
        addressAjaxService.insertS(address);
        //{"name":"준영", "addr":"서울"}
    }

    //(2) Read ( select ) //Get방식
    @GetMapping("read")
    public List<Address> read(){
        List<Address> list = addressAjaxService.listS();
        return list;
    }
    @GetMapping("read/{seq}")
    public Address read(@PathVariable long seq){
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }
    @GetMapping(value="read", params = {"na"}) //Mapping 이름이 같으면 이렇게
    public List<Address> read(@RequestParam("na") String name){
        List<Address> list = addressAjaxService.getListByNames(name);
        return list;
    }
    @DeleteMapping("delete/{seq}")
    public void delete(@PathVariable long seq){
        addressAjaxService.deleteS(seq);
    }
}
