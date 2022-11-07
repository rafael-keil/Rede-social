package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Solicitacao;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolicitacaoMapperTest {

    @Test
    public void quandoInformarAutorEPaiDeveRetornarCurtida() {

        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final Usuario usuarioDiferente = UsuarioFixture.usuarioDiferente();

        final Solicitacao solicitacao = SolicitacaoMapper.toDomain(usuario, usuarioDiferente);

        assertEquals(usuario, solicitacao.getRemetente());
        assertEquals(usuarioDiferente, solicitacao.getDestinatario());
    }
}