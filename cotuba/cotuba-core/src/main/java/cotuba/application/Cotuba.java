package cotuba.application;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.plugin.AoFinalizarGeracao;
import cotuba.plugin.GeradorEbook;

public class Cotuba {

	public void executa(
			ParametrosCotuba parametros,
			Consumer<String> acaoPosGeracao,
			RepositorioDeMDs repositorioDeMDs) {

		FormatoEbook formato = parametros.getFormato();
		Path arquivoDeSaida = parametros.getArquivoDeSaida();

		RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
		List<Capitulo> capitulos = renderizador.renderiza(repositorioDeMDs);

		Ebook ebook = new Ebook(formato, arquivoDeSaida, capitulos);

		GeradorEbook gerador = GeradorEbook.cria(formato);
		gerador.gera(ebook);

		AoFinalizarGeracao.gerou(ebook);

	}

}
