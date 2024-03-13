package com.geradordearquivo.servicepro.templates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TemplateMSG implements Serializable {
   private static final long serialVersionUID = 1L;

   public String DATAFINAL;
   public String DATAINICIAL;

   @JsonCreator
   public TemplateMSG(@JsonProperty("DATAINICIAL") String DATAINICIAL,
                             @JsonProperty("DATAFINAL") String DATAFINAL) {
      this.DATAINICIAL = DATAINICIAL;
      this.DATAFINAL = DATAFINAL;

   }

}
