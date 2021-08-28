package cotuba.cli;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import cotuba.application.RepositorioDeMDs;

public class MDsDoDiretorio implements RepositorioDeMDs {

	private Path diretorioDosMD;

	public MDsDoDiretorio(Path diretoriosDosMD) {
		this.diretorioDosMD = diretoriosDosMD;
	}

	@Override
	public List<String> obtemMDsDosCapitulos() {
		List<String> mds = new ArrayList<>();
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
		try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
			arquivosMD.filter(matcher::matches).sorted().forEach(arquivoMD -> {
				try {
					String md = new String(Files.readAllBytes(arquivoMD));
					mds.add(md);
				} catch (Exception ex) {
					throw new RuntimeException("Erro ao ler arquivo " + arquivoMD, ex);
				}
			});
		} catch (IOException ex) {
			throw new RuntimeException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(),
					ex);
		}
		return mds;
	}

}
