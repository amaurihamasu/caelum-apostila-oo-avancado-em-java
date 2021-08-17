package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;

public interface GeradorEbook {

    public static GeradorEbook cria(FormatoEbook formato) {

        GeradorEbook gerador = formato.getGerador();
        return gerador;
    }

    void gera(Ebook ebook);

}
