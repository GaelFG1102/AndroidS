package edu.tesji.agendagamer.bd_repo;

import edu.tesji.agendagamer.modelo.AgendaGamer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdGamer_repo extends CrudRepository<AgendaGamer, Long> {
}
