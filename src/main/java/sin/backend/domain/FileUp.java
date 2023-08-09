package sin.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@SequenceGenerator(name="FILEUP_SEQ_GENERATOR",sequenceName = "FILEUP_SEQ",initialValue = 1,allocationSize = 1)
@Entity
@NoArgsConstructor
@Data
@Table(name = "fileup")//entity 이름과 테이블이름이 같아서 지금은 무방
public class FileUp {
    @Id//persistence
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "FILEUP_SEQ_GENERATOR")
    @Column(name="file_id")
    private long id;
    private String orgnm;
    private String savednm;
    private String savedpath;

    @Builder//domain 쓸때 추천
    public FileUp(long id, String orgnm, String savednm, String savedpath){
        this.id = id;
        this.orgnm = orgnm;
        this.savednm = savednm;
        this.savedpath = savedpath;
    }
}
