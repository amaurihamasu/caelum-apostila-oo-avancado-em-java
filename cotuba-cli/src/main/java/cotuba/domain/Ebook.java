package cotuba.domain;

import java.nio.file.Path;
import java.util.List;

public class Ebook implements cotuba.plugin.Ebook {

    private FormatoEbook formato;
    private Path arquivoDeSaida;
    private List<cotuba.plugin.Capitulo> capitulos;

    public FormatoEbook getFormato() {
        return formato;
    }
    public void setFormato(FormatoEbook formato) {
        this.formato = formato;
    }
    public Path getArquivoDeSaida() {
        return arquivoDeSaida;
    }
    public void setArquivoDeSaida(Path arquivoDeSaida) {
        this.arquivoDeSaida = arquivoDeSaida;
    }
    public List<cotuba.plugin.Capitulo> getCapitulos() {
        return capitulos;
    }
    public void setCapitulos(List<cotuba.plugin.Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

}
