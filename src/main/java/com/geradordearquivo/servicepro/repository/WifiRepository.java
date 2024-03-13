package com.geradordearquivo.servicepro.repository;

import com.geradordearquivo.servicepro.models.OrdemDeServicoMongo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WifiRepository extends JpaRepository<OrdemDeServicoMongo,Integer> {

    @Query("SELECT d FROM OrdemDeServicoMongo d WHERE d.diaFechamento BETWEEN :datainicial AND :datafinal")
    List<OrdemDeServicoMongo>findToPeriod(LocalDateTime datainicial, LocalDateTime datafinal);

}
