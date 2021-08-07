package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;

public class Cotuba {

    public void executa(ParametrosCotuba opcoesCLI) {

        Path diretorioDosMD = opcoesCLI.getDiretorioDosMD();
        String formato = opcoesCLI.getFormato();
        Path arquivoDeSaida = opcoesCLI.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        GeradorEbook gerador;
        
        if ("pdf".equals(formato)) {
            
            gerador = new GeradorPDF();

        } else if ("epub".equals(formato)) {

            gerador = new GeradorEPUB();

        } else {
            throw new RuntimeException("Formato do ebook inválido: " + formato);
        }

        
        gerador.gera(ebook);
    }

}
