package cotuba.web.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cotuba.web.SpringFileUtils;

@Controller
public class GeracaoDeLivrosController {

	@GetMapping("/livros/{id}/pdf")
	public ResponseEntity<ByteArrayResource> geraPDF(@PathVariable("id") Long id, Model model) {
		Path arquivoPDF = Paths.get("C:\\desenvolvimento\\1-github\\caelum-apostila-oo-avancado-em-java\\Exercício implementando o download de ebooks\\book.pdf");
		return SpringFileUtils.montaResponseDoArquivo(arquivoPDF, "application/pdf");
	}

	@GetMapping("/livros/{id}/epub")
	public ResponseEntity<ByteArrayResource> geraEPUB(@PathVariable("id") Long id, Model model) {
		Path arquivoEPUB = Paths.get("C:\\desenvolvimento\\1-github\\caelum-apostila-oo-avancado-em-java\\Exercício implementando o download de ebooks\\book.epub");
		return SpringFileUtils.montaResponseDoArquivo(arquivoEPUB, "application/epub+zip");
	}

}
