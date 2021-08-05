package cotuba.cli;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

class LeitorOpcoesCLI {

    private Path diretorioDosMD;
    private String formato;
    private Path arquivoDeSaida;
    private boolean modoVerboso = false;

    public Path getDiretorioDosMD() {
        return diretorioDosMD;
    }

    public String getFormato() {
        return formato;
    }

    public Path getArquivoDeSaida() {
        return arquivoDeSaida;
    }

    public boolean isModoVerboso() {
        return modoVerboso;
    }

    public LeitorOpcoesCLI(String[] args) {

        CommandLine cmd = extrairArgumentosInformados(args);

        obterNomeDoDiretorioDosMD(cmd);
        obterNomeDoFormatoDoEbook(cmd);
        obterNomeDoArquivoDeSaidaDoEbook(cmd);
        obterModoVerboso(cmd);

    }

    private CommandLine extrairArgumentosInformados(String[] args) {

        Options options = inicializarOpcoesCLI();

        CommandLineParser cmdParser = new DefaultParser();
        HelpFormatter ajuda = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = cmdParser.parse(options, args);
        } catch (ParseException e) {
            ajuda.printHelp("cotuba", options);
            throw new RuntimeException("Opção inválida", e);
        }
        return cmd;
    }

    private void obterNomeDoArquivoDeSaidaDoEbook(CommandLine cmd) {
        String nomeDoArquivoDeSaidaDoEbook = cmd.getOptionValue("output");
        if (nomeDoArquivoDeSaidaDoEbook != null) {
            arquivoDeSaida = Paths.get(nomeDoArquivoDeSaidaDoEbook);
            if (Files.exists(arquivoDeSaida) && Files.isDirectory(arquivoDeSaida)) {
                throw new RuntimeException(nomeDoArquivoDeSaidaDoEbook + " é um diretório.");
            }
        } else {
            arquivoDeSaida = Paths.get("book." + formato.toLowerCase());
        }
    }

    private void obterNomeDoFormatoDoEbook(CommandLine cmd) {
        String nomeDoFormatoDoEbook = cmd.getOptionValue("format");

        if (nomeDoFormatoDoEbook != null) {
            formato = nomeDoFormatoDoEbook.toLowerCase();
        } else {
            formato = "pdf";
        }
    }

    private void obterNomeDoDiretorioDosMD(CommandLine cmd) {
        String nomeDoDiretorioDosMD = cmd.getOptionValue("dir");

        if (nomeDoDiretorioDosMD != null) {
            diretorioDosMD = Paths.get(nomeDoDiretorioDosMD);
            if (!Files.isDirectory(diretorioDosMD)) {
                throw new RuntimeException(nomeDoDiretorioDosMD + " não é um diretório.");
            }
        } else {
            Path diretorioAtual = Paths.get("");
            diretorioDosMD = diretorioAtual;
        }
    }

    private void obterModoVerboso(CommandLine cmd) {
        modoVerboso = cmd.hasOption("verbose");
    }

    private Options inicializarOpcoesCLI() {
        Options options = new Options();
        options.addOption(opcaoDeDiretorioDosMD());
        options.addOption(opcaoDeFormatoDoEbook());
        options.addOption(opcaoDeArquivoDeSaida());
        options.addOption(opcaoModoVerboso());
        return options;
    }

    private Option opcaoDeDiretorioDosMD() {
        return new Option("d", "dir", true,
                "Diretório que contem os arquivos md. Default: diretório atual.");
    }

    private Option opcaoDeFormatoDoEbook() {
        return new Option("f", "format", true,
                "Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf");
    }

    private Option opcaoDeArquivoDeSaida() {
        return new Option("o", "output", true,
                "Arquivo de saída do ebook. Default: book.{formato}.");
    }

    private Option opcaoModoVerboso() {
        return new Option("v", "verbose", false, "Habilita modo verboso.");
    }

}
