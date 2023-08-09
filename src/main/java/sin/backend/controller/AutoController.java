package sin.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sin.backend.domain.Address;
import sin.backend.service.address.AddressAjaxService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("auto")
public class AutoController {
    private AddressAjaxService addressAjaxService;

    @GetMapping("auto.do")
    public String showView() {
        return "auto/autocomplete";
    }
    @PostMapping("autoData.do")
    public @ResponseBody List<Address> getAddressList(@RequestParam("writer")String writer){
        System.out.println("@@AutocController getAddressList() writer: "+writer);
        List<Address> list = addressAjaxService.getListByNames(writer);
        return list;
    }
}
