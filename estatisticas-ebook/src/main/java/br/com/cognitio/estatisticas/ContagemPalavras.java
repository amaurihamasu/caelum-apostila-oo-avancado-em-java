package br.com.cognitio.estatisticas;

import java.util.HashMap;

public class ContagemPalavras extends HashMap<String, Integer> {

    private static final long serialVersionUID = 2929121974026143051L;

    public void adicionaPalavra(String palavra) {

        Integer contagem = get(palavra);

        if (null != contagem) {
            contagem++;
        } else {
            contagem = 1;
        }

        put(palavra, contagem);
    }

}
