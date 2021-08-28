package cotuba.web.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import cotuba.application.ParametrosCotuba;
import cotuba.domain.FormatoEbook;

public class ParametrosCotubaWeb implements ParametrosCotuba {

	private FormatoEbook formato;
	private Path arquivosDeSaida;

	public ParametrosCotubaWeb(FormatoEbook formato) {
		this.formato = formato;
	}

	@Override
	public FormatoEbook getFormato() {
		return this.formato;
	}

	@Override
	public Path getArquivoDeSaida() {
		if (null != arquivosDeSaida) {
			return arquivosDeSaida;
		}
		try {
			Path diretorio = Files.createTempDirectory("ebooks");
			String nomeDoArquivoDeSaida = "book." + formato.name().toLowerCase();
			this.arquivosDeSaida = diretorio.resolve(nomeDoArquivoDeSaida);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return arquivosDeSaida;
	}

	@Override
	public boolean isModoVerboso() {
		return false;
	}

}
