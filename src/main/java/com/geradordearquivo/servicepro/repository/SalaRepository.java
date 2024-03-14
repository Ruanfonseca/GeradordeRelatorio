package com.geradordearquivo.servicepro.repository;

import com.geradordearquivo.servicepro.models.OrdemDeServicoMongo;
import com.geradordearquivo.servicepro.models.OrdemDeServicoSalaMongo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalaRepository extends MongoRepository<OrdemDeServicoSalaMongo,Integer> {


    @Query("{ 'diaFechamento': { $gte: ?0, $lte: ?1 } }")
    List<OrdemDeServicoSalaMongo> findToPeriod(LocalDateTime datainicial, LocalDateTime datafinal);
}
