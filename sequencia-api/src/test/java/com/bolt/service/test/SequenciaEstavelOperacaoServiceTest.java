package com.bolt.service.test;

import com.bolt.SequenciaApiApplication;
import com.bolt.entity.SequenciaEstavelOperacao;
import com.bolt.repository.SequenciaEstavelOperacaoRepository;
import com.bolt.service.SequenciaEstavelOperacaoService;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author PriscilaLuz
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SequenciaApiApplication.class)
@AutoConfigureMockMvc
public class SequenciaEstavelOperacaoServiceTest {

    @Autowired
    private SequenciaEstavelOperacaoService sequenciaEstavelOperacaoService;
    
    @MockBean
    private SequenciaEstavelOperacaoRepository seqEstavelOperacaoRepository;

    @Test
    public void quandoConsultarComInformacaoNoBancoDadosRetornarDadosDoBanco() {
        Mockito.when(seqEstavelOperacaoRepository.findBySequencia(any())).thenReturn(getMockSequenciaEstavelOperacao());
        List<Integer> lista = sequenciaEstavelOperacaoService.salvarQntOperacoesEstabilizar(Arrays.asList("{{}}}{"));
        assertThat(lista).hasSize(1);
        assertThat(lista.get(0).equals(2));
    }
    
    @Test
    public void quandoConsultarSemInformNoBancoDadosRetornarInforProcessada() {
        Mockito.when(seqEstavelOperacaoRepository.save(any())).thenReturn(getMockSequenciaEstavelOperacao());
        List<Integer> lista = sequenciaEstavelOperacaoService.salvarQntOperacoesEstabilizar(Arrays.asList("{{}}}{"));
        assertThat(lista).hasSize(1);
        assertThat(lista.get(0).equals(2));
    }
    
    private SequenciaEstavelOperacao getMockSequenciaEstavelOperacao() {
        SequenciaEstavelOperacao sequencia = new SequenciaEstavelOperacao();
        sequencia.setId(1L);
        sequencia.setQntOperacoes(2);
        sequencia.setSequencia("{{}}}{");
        return sequencia;
    }
}
