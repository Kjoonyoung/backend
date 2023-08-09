package sin.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChartDTO {
    private String item; //key
    private int number; //value

    @Override //Object toString
    public String toString(){
        return "#ChartDTO item: "+item+", number: "+number;
    }
}
