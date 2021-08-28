package cotuba.md;

import java.util.ArrayList;
import java.util.List;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import cotuba.application.RenderizadorMDParaHTML;
import cotuba.application.RepositorioDeMDs;
import cotuba.domain.Capitulo;
import cotuba.domain.builder.CapituloBuilder;
import cotuba.tema.AplicadorTema;

public class RenderizadorMDParaHTMLComCommonMark implements RenderizadorMDParaHTML {

	@Override
	public List<Capitulo> renderiza(RepositorioDeMDs repositorioDeMDs) {
		List<Capitulo> capitulos = new ArrayList<Capitulo>();
		repositorioDeMDs.obtemMDsDosCapitulos().forEach(conteudoMD -> {
			Capitulo capitulo = extrairConteudoArquivosMDparaHTML(conteudoMD);
			capitulos.add(capitulo);
		});
		return capitulos;
	}

	private Capitulo extrairConteudoArquivosMDparaHTML(String conteudoMD) {
		Node document = extrairConteudoArquivoMD(conteudoMD);
		return renderizarConteudoParaHtml(document);
	}

	private Capitulo renderizarConteudoParaHtml(Node document) {
		CapituloBuilder capituloBuilder = new CapituloBuilder();
		document.accept(new AbstractVisitor() {
			@Override
			public void visit(Heading heading) {
				if (heading.getLevel() == 1) {
					// capítulo
					String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
					capituloBuilder.comTitulo(tituloDoCapitulo);
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

			AplicadorTema tema = new AplicadorTema();
			String htmlComTemas = tema.aplica(html);

			capituloBuilder.comConteudoHTML(htmlComTemas);

		} catch (Exception ex) {
			throw new RuntimeException("Erro ao renderizar MD para HTML", ex);
		}
		return capituloBuilder.constroi();
	}

	private Node extrairConteudoArquivoMD(String conteudoMD) {
		Node document = null;
		try {
			Parser parser = Parser.builder().build();
			document = parser.parse(conteudoMD);
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao fazer parse do markdown ", ex);
		}
		return document;
	}

}
