package cotuba.domain;

import cotuba.application.GeradorEbook;
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;

public enum FormatoEbook {

    EPUB(new GeradorEPUB()), PDF(new GeradorPDF());

    private GeradorEbook gerador;

    private FormatoEbook(GeradorEbook gerador) {
        this.gerador = gerador;
    }

    public GeradorEbook getGerador() {
        return this.gerador;
    }

}
