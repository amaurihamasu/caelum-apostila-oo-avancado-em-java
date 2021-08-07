package cotuba.pdf;

import cotuba.domain.Ebook;

public interface GeradorPDF {

    void gera(Ebook ebook);

    public static GeradorPDF cria() {
        return new GeradorPDFComIText();
    }

}