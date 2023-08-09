package sin.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sin.backend.dto.ChartDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequestMapping("chart")
@Controller
public class ChartController {
    @GetMapping("chart.do")
    public String showView(){
        return "chart/chart";
    }

    private Random random = new Random();
    @PostMapping("chartData.do") // local:8080/chart/chartData.do Get방식으로 확인 후 다시 Post 변경
    public @ResponseBody List<ChartDTO> getChartData(){
        List<ChartDTO> list = new ArrayList<>();
        String items[] = {"봄", "여름", "가을", "겨울"};

        for(int i=0; i<items.length; i++){
            int number = random.nextInt(100);
            ChartDTO dto =new ChartDTO(items[i], number);
            list.add(dto);
        }
        return list;
    }
}
