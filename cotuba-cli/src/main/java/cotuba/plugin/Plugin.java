package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Ebook;

public interface Plugin {

    String cssDoTema();

    void aposGeracao(Ebook ebook);

    static void gerou(Ebook ebook) {

        ServiceLoader<Plugin> loader = ServiceLoader.load(Plugin.class);
        for (Plugin plugin : loader) {
            plugin.aposGeracao(ebook);
        }

    }

}
