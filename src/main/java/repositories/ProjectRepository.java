package repositories;
import domain.Project;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
@Repository
public interface ProjectRepository extends CrudRepository<Project,Long>
{
	Project findByProjectIdentifier(String projectId);
	@Override
	Iterable<Project> findAll();
}
