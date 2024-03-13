package com.geradordearquivo.servicepro.service;

import com.geradordearquivo.servicepro.models.OrdemDeServicoMongo;
import com.geradordearquivo.servicepro.models.OrdemDeServicoProjetorMongo;
import com.geradordearquivo.servicepro.repository.ProjetorRepository;
import com.geradordearquivo.servicepro.repository.WifiRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RelatorioProjetorService {

    @Autowired
    public ProjetorRepository repository;
    public List<OrdemDeServicoProjetorMongo> todasOSprojetor(LocalDateTime datainicial, LocalDateTime datafinal) {
        return repository.findToPeriod(datainicial,datafinal);
    }

    public byte[] criarPdf(List<OrdemDeServicoProjetorMongo> listaDeOS) {
        try{
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, baos);

            document.open();

            // Adicionando imagem ao PDF
            URL imageUrl = new URL("https://github.com/Ruanfonseca/Sistema_servicePro/raw/729e563093d78de37e536aeb88de391a4a08ad42/src/main/resources/static/images/logoUERJ.png");
            Image image = Image.getInstance(imageUrl);
            image.scaleToFit(100, 100);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);

            // Adicionando título ao documento
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
            Paragraph title = new Paragraph("Relatório de Ordens de Serviço", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            for (OrdemDeServicoProjetorMongo ordemDeServico : listaDeOS) {
                Paragraph paragraph = new Paragraph();
                paragraph.setFont(contentFont);
                paragraph.add("ID: " + ordemDeServico.getId() +
                        "\nData de Fechamento: " + ordemDeServico.getDiaFechamento() +
                        "\nNome Funcionário Responsável: " + ordemDeServico.getNomeFuncionarioResponsavel() +
                        "\nMatrícula Funcionário: " + ordemDeServico.getMatriculaFuncionario() +
                        "\nNome Requerente: " + ordemDeServico.getRequerenteNome() +
                        "\nMatrícula Requerente: " + ordemDeServico.getRequerenteMatricula() +
                        "\n "
                );
                document.add(paragraph);
            }

            document.close();

            return baos.toByteArray();
        }catch (DocumentException | IOException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar o PDF");
        }

    }


}
