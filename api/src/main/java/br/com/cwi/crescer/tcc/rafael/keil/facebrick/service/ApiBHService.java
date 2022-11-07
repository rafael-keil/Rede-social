package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.ValidacaoNegocioException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper.UsuarioBHMapper;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.ApiBHRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioBHRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.RegistrarUsuarioBHRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiBHService {

    @Autowired
    private ApiBHRepository repository;

    public void registrarUsuario(final CriarUsuarioRequest request) {

        final RegistrarUsuarioBHRequest registrarUsuarioBHRequest = UsuarioBHMapper.toRequest(request);

        try {
            repository.registrarUsuarioApiBH(registrarUsuarioBHRequest);
        } catch (final Exception error) {
            throw new ValidacaoNegocioException("Cadastro inválido.");
        }
    }

    public String logarUsuario(final LogarUsuarioRequest request) {

        final LogarUsuarioBHRequest requestBH = UsuarioBHMapper.toLogin(request);

        try {
            return repository.logarUsuario(requestBH);
        } catch (final Exception error) {
            throw new ValidacaoNegocioException("Cadastro inválido.");
        }
    }
}
