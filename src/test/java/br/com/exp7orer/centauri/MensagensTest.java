package br.com.exp7orer.centauri;

import br.com.exp7orer.centauri.service.mensagem.entity.CaixaPostalEntity;
import br.com.exp7orer.centauri.service.mensagem.entity.DestinatarioEntity;
import br.com.exp7orer.centauri.service.mensagem.entity.MensagemEntity;
import br.com.exp7orer.centauri.service.mensagem.entity.RemetenteEntity;
import br.com.exp7orer.centauri.service.mensagem.enums.Prioridade;
import br.com.exp7orer.centauri.service.mensagem.exceptions.MensagemException;
import br.com.exp7orer.centauri.service.mensagem.interfaces.Armazem;
import br.com.exp7orer.centauri.service.mensagem.interfaces.Mensageiro;
import br.com.exp7orer.centauri.service.mensagem.interfaces.Mensagem;
import br.com.exp7orer.centauri.service.mensagem.record.Transportador;
import br.com.exp7orer.centauri.service.mensagem.service.ArmazemMensagens;
import br.com.exp7orer.centauri.service.mensagem.service.CorreioMensagem;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

public class MensagensTest {

    @Test
    public void mensagemEnviadaAMesmaRecebida() {
        RemetenteEntity remetente = new RemetenteEntity("Anderson", "email@teste.com");
        MensagemEntity mensagem = new MensagemEntity("Boas Vindas!", "Bem vindo ao Centauri!");
        CaixaPostalEntity caixaPostal = new CaixaPostalEntity(mensagem);
        DestinatarioEntity destinatario = new DestinatarioEntity("Douglas", "douglas@teste.com", caixaPostal);
        Mensageiro correio = new CorreioMensagem();
        correio.recebeMensagem(destinatario, remetente, mensagem, Prioridade.URGENTE);
        Mensagem mensagemRetorno = correio.buscaMensagem(destinatario, mensagem);
        assertEquals(mensagem, mensagemRetorno);
    }

    @Test
    public void excecaoEnviarMensagemConteudoETituloEmBranco() {
        RemetenteEntity remetente = new RemetenteEntity("Anderson", "email@teste.com");
        MensagemEntity mensagem = new MensagemEntity("Boas Vindas!", "");
        CaixaPostalEntity caixaPostal = new CaixaPostalEntity(mensagem);
        DestinatarioEntity destinatario = new DestinatarioEntity("Douglas", "douglas@teste.com", caixaPostal);
        Mensageiro correio = new CorreioMensagem();
        MensagemException mensagemException = assertThrows(MensagemException.class, () -> {
            correio.recebeMensagem(destinatario, remetente, mensagem, Prioridade.URGENTE);
        });
    }

    @Test
    public void enviar1000MensagensAcada5MinutosDurantante20Minutos() {
        RemetenteEntity remetente = new RemetenteEntity("Anderson", "email@teste.com");
        MensagemEntity mensagem = new MensagemEntity("Boas Vindas!", "Bem vindo ao Centauri!");
        CaixaPostalEntity caixaPostal = new CaixaPostalEntity(mensagem);
        DestinatarioEntity destinatario = new DestinatarioEntity("Douglas", "douglas@teste.com", caixaPostal);
        Mensageiro correio = new CorreioMensagem();
        Armazem armazem = new ArmazemMensagens();
        for (int i = 0; i <= 999; i++) {
            System.out.println("Enquanto não enviar todas as mensagem sua aplicação ficará travada!");
            System.out.println("Quantidade de Mensagens: " + i);
            MensagemEntity mensagemNova = new MensagemEntity("Boas Vindas!",
                    "Bem vindo ao Centauri! numero: " + i);
            correio.recebeMensagem(destinatario, remetente, mensagem, Prioridade.URGENTE);
            Mensagem mensagemRetorno = correio.buscaMensagem(destinatario, mensagem);
        }
        List<Mensagem> listaMensagem = (List<Mensagem>) armazem.mensagens(destinatario);
        System.out.println(listaMensagem.size());

    }

    public void enviar1000MensagensAcada10MinutosDurantante20Minutos() {

    }

    public void enviar1000MensagensAcada1MinutosDurantante20Minutos() {
        RemetenteEntity remetente = new RemetenteEntity("Anderson", "email@teste.com");
        MensagemEntity mensagem = new MensagemEntity("Boas Vindas!", "Bem vindo ao Centauri!");
        CaixaPostalEntity caixaPostal = new CaixaPostalEntity(mensagem);
        DestinatarioEntity destinatario = new DestinatarioEntity("Douglas", "douglas@teste.com", caixaPostal);
        Mensageiro correio = new CorreioMensagem();
        for (int i = 0; i <= 999; i++) {
            System.out.println("Enquanto não enviar todas as mensagem sua aplicação ficará travada!");
            System.out.println("Quantidade de Mensagens: " + i);
            correio.recebeMensagem(destinatario, remetente, mensagem, Prioridade.URGENTE);
            Mensagem mensagemRetorno = correio.buscaMensagem(destinatario, mensagem);
        }

    }

}
