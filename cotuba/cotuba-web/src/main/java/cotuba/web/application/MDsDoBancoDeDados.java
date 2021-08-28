package cotuba.web.application;

import java.util.ArrayList;
import java.util.List;

import cotuba.application.RepositorioDeMDs;
import cotuba.web.domain.Livro;

public class MDsDoBancoDeDados implements RepositorioDeMDs {

	private Livro livro;

	public MDsDoBancoDeDados(Livro livro) {
		this.livro = livro;
	}

	@Override
	public List<String> obtemMDsDosCapitulos() {
		List<String> mds = new ArrayList<>();
		livro.getCapitulos().forEach(capitulo -> {
			StringBuilder md =
					new StringBuilder()
					.append("# ")
					.append(capitulo.getNome())
					.append("\n")
					.append(capitulo.getMarkdown());
			mds.add(md.toString());
		});
		return mds;
	}

}
