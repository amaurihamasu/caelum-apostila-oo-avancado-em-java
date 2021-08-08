package cotuba.cli;

import java.nio.file.Path;

import cotuba.application.Cotuba;
import cotuba.application.ParametrosCotuba;

public class Main {

    public static void main(String[] args) {

        Path arquivoDeSaida;
        boolean modoVerboso = false;

        try {

            ParametrosCotuba opcoesCLI = new LeitorOpcoesCLI(args);

            arquivoDeSaida = opcoesCLI.getArquivoDeSaida();
            modoVerboso = opcoesCLI.isModoVerboso();

            Cotuba cotuba = new Cotuba();
            cotuba.executa(opcoesCLI);

            System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (modoVerboso) {
                ex.printStackTrace();
            }
            System.exit(1);
        }
    }

}
