package cotuba.pdf;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;

import cotuba.application.GeradorEbook;
import cotuba.plugin.Capitulo;

public class GeradorPDF implements GeradorEbook {

    @Override
    public void gera(cotuba.domain.Ebook ebook) {

        Path arquivoDeSaida = ebook.getArquivoDeSaida();

        try (PdfWriter writer = new PdfWriter(Files.newOutputStream(arquivoDeSaida));
                PdfDocument pdf = new PdfDocument(writer);
                Document pdfDocument = new Document(pdf)) {

            for (Capitulo capitulo : ebook.getCapitulos()) {

                String html = capitulo.getConteudoHTML();

                List<IElement> convertToElements = HtmlConverter.convertToElements(html);

                for (IElement element : convertToElements) {
                    pdfDocument.add((IBlockElement) element);
                }

            }

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Erro ao criar arquivo PDF: " + arquivoDeSaida.toAbsolutePath(), ex);
        }
    }

}
