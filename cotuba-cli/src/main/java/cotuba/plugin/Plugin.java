package cotuba.plugin;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface Plugin {

    // Exercício: carregando o service provider
    // (desafio) O método estático load, da classe ServiceLoader,
    // vasculha o Classpath em busca de implementações da SPI,
    // o que é um processo lento. Otimize o código armazenando a instância de ServiceLoader retornada.
    static final ServiceLoader<Plugin> LOADER = ServiceLoader.load(Plugin.class);

    String cssDoTema();

    static List<String> listaDeTemas() {

        // Exercício: carregando o service provider
        // (opcional) No método listaDeTemas da classe Plugin, use recursos do Java 8 como
        // Lambdas e Streams para trabalhar com o ServiceLoader.
        List<String> temas =
                LOADER
                    .stream()
                    .map(loaderPlugin -> loaderPlugin.get().cssDoTema())
                    .collect(Collectors.toList());

        return temas;
    }

}
