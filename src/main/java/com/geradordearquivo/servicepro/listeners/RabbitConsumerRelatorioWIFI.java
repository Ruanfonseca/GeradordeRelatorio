package com.geradordearquivo.servicepro.listeners;

import com.geradordearquivo.servicepro.models.OrdemDeServicoMongo;
import com.geradordearquivo.servicepro.service.RelatorioProjetorService;
import com.geradordearquivo.servicepro.service.RelatorioWifiService;
import com.geradordearquivo.servicepro.templates.TemplateMSG;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.geradordearquivo.servicepro.utils.FormatadorDeDatas.FormatadorDeDatas;

@RestController("/geradorPDF")
@CrossOrigin("*")
public class RabbitConsumerRelatorioWIFI {

    @Autowired
    private RelatorioWifiService service;

    @RabbitListener(queues = "RELATORIO_WIFI")
    public void consumeMessage(TemplateMSG msg)
    {
        System.out.println("Mensagem recebida! ");
        processarLista(msg);
    }


    public void processarLista(TemplateMSG msg){

        List<OrdemDeServicoMongo> listaDeOS = new ArrayList<OrdemDeServicoMongo>();

        LocalDateTime datainicial = FormatadorDeDatas(msg.DATAINICIAL);
        LocalDateTime datafinal =FormatadorDeDatas(msg.DATAFINAL);


        if (msg != null){
            service.todasOSwifi(datainicial,datafinal).forEach(listaDeOS::add);
        }

        geradorDeRelatorio(listaDeOS);
    }



    @GetMapping("/WIFI_PDF")
    public ResponseEntity<byte[]> geradorDeRelatorio(List<OrdemDeServicoMongo> listaDeOS) {
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


