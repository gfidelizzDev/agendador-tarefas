package com.gfidelizzdev.agendadortarefas.infraestructure.Repository;

import com.gfidelizzdev.agendadortarefas.infraestructure.Entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String > {
}
