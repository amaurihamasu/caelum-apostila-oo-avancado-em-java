package cotuba;

import java.util.List;

public class Ebook {

    private String formato;
    private String arquivoDeSaida;
    private List<Capitulo> capitulos;

    public String getFormato() {
        return formato;
    }
    public void setFormato(String formato) {
        this.formato = formato;
    }
    public String getArquivoDeSaida() {
        return arquivoDeSaida;
    }
    public void setArquivoDeSaida(String arquivoDeSaida) {
        this.arquivoDeSaida = arquivoDeSaida;
    }
    public List<Capitulo> getCapitulos() {
        return capitulos;
    }
    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

}
