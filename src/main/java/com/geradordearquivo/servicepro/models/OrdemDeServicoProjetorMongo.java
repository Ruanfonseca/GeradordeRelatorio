package com.geradordearquivo.servicepro.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;


@Data
public class OrdemDeServicoProjetorMongo {

    @Id
    private long id;

    private LocalDateTime diaFechamento;


    private String nomeFuncionarioResponsavel;
    private String matriculaFuncionario;

    private String requerenteNome;
    private String requerenteMatricula;


    public OrdemDeServicoProjetorMongo(LocalDateTime data, String matriculaFuncionario, String nomeFuncionarioResponsavel,
                                       String matriculaReq, String nomeRequerente) {
        this.diaFechamento = data;
        this.nomeFuncionarioResponsavel = nomeFuncionarioResponsavel;
        this.matriculaFuncionario = matriculaFuncionario;
        this.requerenteNome = nomeRequerente;
        this.requerenteMatricula = matriculaReq;
    }
}
