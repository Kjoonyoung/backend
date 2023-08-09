package sin.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sin.backend.domain.Address;
import sin.backend.service.address.AddressAjaxService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("ajax3")
@Controller
public class AjaxTest3Controller {
    private AddressAjaxService addressAjaxService;

    @GetMapping("search1.do")
    @ResponseBody
    public Address search1(long seq){
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }
    @PostMapping("search2.do")
    @ResponseBody
    public List<Address> search2(String name){
        List<Address> list = addressAjaxService.getListByNames(name);
        return list;
    }
}