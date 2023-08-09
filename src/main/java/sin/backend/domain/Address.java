package sin.backend.domain;
//(DTO)

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity//추가
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name="ADDRESS_SEQ_GENERATOR" ,sequenceName = "ADDRESS_SEQ", initialValue = 1, allocationSize = 1)//추가
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ADDRESS_SEQ_GENERATOR" )//Oracle일 경우 추가 db마다 다름
    private long seq;
    //@Column(name = "username") db컬럼이름이 username일 경우
    private String name;
    private String addr;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp //추가 SYSDATE 안써도 가능하게 해줌,.
    private Date rdate;
}

