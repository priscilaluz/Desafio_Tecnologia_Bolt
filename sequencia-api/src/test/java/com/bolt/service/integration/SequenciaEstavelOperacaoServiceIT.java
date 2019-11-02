package com.bolt.service.integration;

import com.bolt.SequenciaApiApplication;
import com.bolt.entity.SequenciaEstavelOperacao;
import com.bolt.repository.SequenciaEstavelOperacaoRepository;
import com.bolt.service.SequenciaEstavelOperacaoService;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author PriscilaLuz
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SequenciaApiApplication.class)
@AutoConfigureMockMvc
public class SequenciaEstavelOperacaoServiceIT {

    @Autowired
    private SequenciaEstavelOperacaoService sequenciaEstavelOperacaoService;
    
    @Autowired
    private SequenciaEstavelOperacaoRepository seqEstavelOperacaoRepository;

    @BeforeEach
    public void init() {
        SequenciaEstavelOperacao seqEstavelOperacao = new SequenciaEstavelOperacao();
        seqEstavelOperacao.setQntOperacoes(0);
        seqEstavelOperacao.setSequencia("{{{}}}");
        seqEstavelOperacaoRepository.save(seqEstavelOperacao);
    }
    
    @AfterEach
    public void end() {
        seqEstavelOperacaoRepository.deleteAll();
    }
    
    @Test
    public void quandoConsultarSequenciasRetonarSalvarOperacoes() {
        assertThat(seqEstavelOperacaoRepository.findAll()).hasSize(1);

        List<String> entrada = Arrays.asList("", "}{", "{}{}", "{{{}", "{{}{}{}}", "}}}{{{");
        List<Integer> encontrado = sequenciaEstavelOperacaoService.salvarQntOperacoesEstabilizar(entrada);

        assertThat(encontrado).hasSize(6);
        List<Integer> esperado = Arrays.asList(0, 2, 0, 1, 0, 4);
        for (int i = 0; i < 6; i++) {
            assertThat(esperado.get(i).equals(encontrado.get(i))).isTrue();
        }
        assertThat(seqEstavelOperacaoRepository.findAll()).hasSize(7);
    }
    
    @Test
    public void naoSalvarQuandoJaEstiverSalvarNoBanco() {
        assertThat(seqEstavelOperacaoRepository.findAll()).hasSize(1);

        List<Integer> encontrado = sequenciaEstavelOperacaoService.salvarQntOperacoesEstabilizar(Arrays.asList("{{{}}}"));

        assertThat(encontrado).hasSize(1);
        List<Integer> esperado = Arrays.asList(0);
        assertThat(esperado.get(0).equals(encontrado.get(0))).isTrue();
        assertThat(seqEstavelOperacaoRepository.findAll()).hasSize(1);
    }
    
}
