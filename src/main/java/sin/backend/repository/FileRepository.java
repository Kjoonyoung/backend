package sin.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sin.backend.domain.FileUp;

public interface FileRepository extends JpaRepository<FileUp,Long>//generic type wrapper class
{

}
