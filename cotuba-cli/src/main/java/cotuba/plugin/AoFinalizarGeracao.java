package cotuba.plugin;

import java.util.ServiceLoader;

public interface AoFinalizarGeracao {

    void aposGeracao(Ebook ebook);

    static void gerou(Ebook ebook) {

        ServiceLoader<AoFinalizarGeracao> loader = ServiceLoader.load(AoFinalizarGeracao.class);
        for (AoFinalizarGeracao plugin : loader) {
            plugin.aposGeracao(ebook);
        }

    }

}
