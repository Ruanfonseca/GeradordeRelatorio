package com.geradordearquivo.servicepro.models;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
public class OrdemDeServicoMongo {

    @Id
    private long id;

    private LocalDateTime diaFechamento;
    private String nomeFuncionarioResponsavel;
    private String matriculaFuncionario;

    private String requerenteNome;
    private String requerenteMatricula;

    public OrdemDeServicoMongo(LocalDateTime data, String matriculaFuncionario, String nomeFuncionarioResponsavel,
                               String matriculaReq, String nomeRequerente) {
        this.diaFechamento = data;
        this.nomeFuncionarioResponsavel = nomeFuncionarioResponsavel;
        this.matriculaFuncionario = matriculaFuncionario;
        this.requerenteNome = nomeRequerente;
        this.requerenteMatricula = matriculaReq;
    }
}
