package cotuba.web.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cotuba.domain.FormatoEbook;
import cotuba.web.SpringFileUtils;
import cotuba.web.application.GeracaoDeLivros;

@Controller
public class GeracaoDeLivrosController {

	private final GeracaoDeLivros geracao;

	public GeracaoDeLivrosController(GeracaoDeLivros geracao) {
		this.geracao = geracao;
	}

	@GetMapping("/livros/{id}/pdf")
	public ResponseEntity<ByteArrayResource> geraPDF(@PathVariable("id") Long id, Model model) {
		Path arquivoPDF = geracao.geraLivro(id, FormatoEbook.PDF);
		return SpringFileUtils.montaResponseDoArquivo(arquivoPDF, "application/pdf");
	}

	@GetMapping("/livros/{id}/epub")
	public ResponseEntity<ByteArrayResource> geraEPUB(@PathVariable("id") Long id, Model model) {
		Path arquivoEPUB = geracao.geraLivro(id, FormatoEbook.EPUB);
		return SpringFileUtils.montaResponseDoArquivo(arquivoEPUB, "application/epub+zip");
	}

}
