package cotuba.md;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import cotuba.application.RenderizadorMDParaHTML;
import cotuba.domain.Capitulo;

public class RenderizadorMDParaHTMLComCommonMark implements RenderizadorMDParaHTML {

    @Override
    public List<Capitulo> renderiza(Path diretorioDosMD) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        List<Capitulo> capitulos = obterConteudoCapitulos(diretorioDosMD, matcher);
        return capitulos;
    }

    private List<Capitulo> obterConteudoCapitulos(Path diretorioDosMD, PathMatcher matcher) {
        List<Capitulo> capitulos = new ArrayList<Capitulo>();
        try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
            arquivosMD.filter(matcher::matches).sorted().forEach(arquivoMD -> {
                Capitulo capitulo = extrairConteudoArquivosMDparaHTML(arquivoMD);
                capitulos.add(capitulo);
            });
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(),
                    ex);
        }
        return capitulos;
    }

    private Capitulo extrairConteudoArquivosMDparaHTML(Path arquivoMD) {
        Node document = extrairConteudoArquivoMD(arquivoMD);
        return renderizarConteudoParaHtml(arquivoMD, document);
    }

    private Capitulo renderizarConteudoParaHtml(Path arquivoMD, Node document) {
        Capitulo capitulo = new Capitulo();
        document.accept(new AbstractVisitor() {
            @Override
            public void visit(Heading heading) {
                if (heading.getLevel() == 1) {
                    // capítulo
                    String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
                    capitulo.setTitulo(tituloDoCapitulo);
                } else if (heading.getLevel() == 2) {
                    // seção
                } else if (heading.getLevel() == 3) {
                    // título
                }
            }
        });
        try {
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String html = renderer.render(document);
            capitulo.setConteudoHTML(html);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
        }
        return capitulo;
    }

    private Node extrairConteudoArquivoMD(Path arquivoMD) {
        Node document = null;
        try {
            Parser parser = Parser.builder().build();
            document = parser.parseReader(Files.newBufferedReader(arquivoMD));
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
        }
        return document;
    }

}
