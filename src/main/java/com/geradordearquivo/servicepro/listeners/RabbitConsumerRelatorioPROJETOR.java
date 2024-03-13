package com.geradordearquivo.servicepro.listeners;

import com.geradordearquivo.servicepro.models.OrdemDeServicoProjetorMongo;
import com.geradordearquivo.servicepro.models.OrdemDeServicoSalaMongo;
import com.geradordearquivo.servicepro.service.RelatorioProjetorService;
import com.geradordearquivo.servicepro.service.RelatorioSalaService;
import com.geradordearquivo.servicepro.templates.TemplateMSG;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.geradordearquivo.servicepro.utils.FormatadorDeDatas.FormatadorDeDatas;

@RestController("/geradorPDF")
@CrossOrigin("*")
public class RabbitConsumerRelatorioPROJETOR {



    @Autowired
    private RelatorioProjetorService service;

    @RabbitListener(queues = "RELATORIO_PROJETOR")
    public void consumeMessage(TemplateMSG msg)
    {
        System.out.println("Mensagem recebida! ");
        processarLista(msg);
    }



    public void processarLista(TemplateMSG msg){

        List<OrdemDeServicoProjetorMongo> listaDeOS = new ArrayList<OrdemDeServicoProjetorMongo>();

        LocalDateTime datainicial = FormatadorDeDatas(msg.DATAINICIAL);
        LocalDateTime datafinal =FormatadorDeDatas(msg.DATAFINAL);


        if (msg != null){
            service.todasOSprojetor(datainicial,datafinal).forEach(listaDeOS::add);
        }

        geradorDeRelatorio(listaDeOS);
    }



    @GetMapping("/PROJETOR_PDF")
    public ResponseEntity<byte[]> geradorDeRelatorio(List<OrdemDeServicoProjetorMongo> listaDeOS) {
        try {
            byte[] pdfBytes = service.criarPdf(listaDeOS);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio_ordens_servico.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
