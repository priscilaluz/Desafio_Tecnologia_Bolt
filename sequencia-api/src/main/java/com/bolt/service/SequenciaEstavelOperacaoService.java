package com.bolt.service;

import com.bolt.entity.SequenciaEstavelOperacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.bolt.repository.SequenciaEstavelOperacaoRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Service da SequenciaEstavelOperacao
 * @author priscilaluz
 */
@Service
public class SequenciaEstavelOperacaoService {

    @Autowired
    private SequenciaEstavelOperacaoRepository seqEstavelOperacaoRepository;

    /**
     * Retorna e salva lista com quantidade de operações necessárias para estabilizar sequencias
     * @param sequencias
     * @return lista de inteiros
     */
    public List<Integer> salvarQntOperacoesEstabilizar(List<String> sequencias) {
        List<Integer> retorno = new ArrayList<>();
        
        sequencias.stream().map((sequencia) -> {
            SequenciaEstavelOperacao seqEstavelOperacao = seqEstavelOperacaoRepository.findBySequencia(sequencia);
            if (seqEstavelOperacao == null) {
                seqEstavelOperacao = new SequenciaEstavelOperacao();
                seqEstavelOperacao.setQntOperacoes(buscarQuantidadeOperacoesEstabilizar(sequencia));
                seqEstavelOperacao.setSequencia(sequencia);
                seqEstavelOperacaoRepository.save(seqEstavelOperacao);
            }
            return seqEstavelOperacao;
        }).forEachOrdered((seqEstavelOperacao) -> {
            retorno.add(seqEstavelOperacao.getQntOperacoes());
        });

        return retorno;
    }

    /**
     * Retorna a quantidade de operações para estabilizar a sequencia
     * @param sequencia
     * @return quantidade de operações
     */
    private int buscarQuantidadeOperacoesEstabilizar(String sequencia) {
        String instaveis = removerSequenciasEstaveis(sequencia);

        int operacoes = StringUtils.countMatches(instaveis, "{{");
        instaveis = instaveis.replaceAll("\\{\\{", "");

        operacoes = operacoes + StringUtils.countMatches(instaveis, "}}");
        instaveis = instaveis.replaceAll("\\}\\}", "");

        operacoes += instaveis.length();

        return operacoes;
    }

    /**
     * Remove as partes da senteça que estão estáveis
     * @param sequencia
     * @return sequencia instavel
     */
    private String removerSequenciasEstaveis(String sequencia) {
        StringBuilder instaveis = new StringBuilder();

        sequencia.chars().mapToObj(x -> (char) x).forEach((x) -> {
            if ((x == '{') || (instaveis.lastIndexOf("}") == instaveis.length() - 1)) {
                instaveis.append(x);
            } else {
                instaveis.deleteCharAt(instaveis.length() - 1);
            }
        });

        return instaveis.toString();
    }
}
