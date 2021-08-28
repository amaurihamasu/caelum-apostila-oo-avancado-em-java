package cotuba.web.application;

import org.springframework.stereotype.Service;

import cotuba.domain.FormatoEbook;
import cotuba.web.domain.Livro;

@Service
public class GeracaoDeLivros {

	private final CadastroDeLivros livros;

	public GeracaoDeLivros(CadastroDeLivros livros) {
		this.livros = livros;
	}

	public Path geraLivro(Long id, FormatoEbook formato) {
		Livro livro = livros.detalha(id);
	}
	
}
