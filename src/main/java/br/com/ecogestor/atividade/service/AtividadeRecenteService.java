package br.com.ecogestor.atividade.service;

import br.com.ecogestor.atividade.dto.request.AtividadeRecenteRequest;
import br.com.ecogestor.atividade.dto.response.AtividadeRecenteResponse;
import br.com.ecogestor.atividade.entity.AtividadeRecente;
import br.com.ecogestor.atividade.repository.AtividadeRecenteRepository;
import br.com.ecogestor.atividade.mapper.AtividadeRecenteMapper;
import br.com.ecogestor.shared.enums.EnumTipoAtividade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AtividadeRecenteService {

    @Autowired
    private AtividadeRecenteRepository atividadeRecenteRepository;

    @Autowired
    private AtividadeRecenteMapper atividadeRecenteMapper;

    @Autowired
    private AtividadeHelper atividadeHelper;

    @Transactional
    public void registrarAtividade(AtividadeRecenteRequest request, String usuarioEmail) {
        AtividadeRecente atividade = atividadeRecenteMapper.toEntity(request, usuarioEmail);
        atividadeRecenteRepository.save(atividade);
        log.info("Atividade registrada: {} - {}", request.getTipo(), request.getTitulo());
    }

    @Transactional(readOnly = true)
    public List<AtividadeRecenteResponse> buscarUltimas(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "data"));
        return atividadeRecenteRepository.buscarTodasPaginadas(pageable)
                .stream()
                .map(atividadeRecenteMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Métodos específicos para cada tipo de entidade
    @Transactional
    public void registrarCriacaoPonto(Long pontoId, String nomePonto) {
        AtividadeRecenteRequest request = atividadeRecenteMapper.criarRequestPonto(pontoId, nomePonto);
        registrarAtividade(request, atividadeHelper.getUsuarioEmailLogado());
    }

    @Transactional
    public void registrarCriacaoResiduo(Long residuoId, Double peso, String tipoResiduo) {
        AtividadeRecenteRequest request = atividadeRecenteMapper.criarRequestResiduo(residuoId, peso, tipoResiduo);
        registrarAtividade(request, atividadeHelper.getUsuarioEmailLogado());
    }

    @Transactional
    public void registrarCriacaoLicenca(Long licencaId, String numeroLicenca, String tipoLicenca) {
        AtividadeRecenteRequest request = atividadeRecenteMapper.criarRequestLicenca(licencaId, numeroLicenca, tipoLicenca);
        registrarAtividade(request, atividadeHelper.getUsuarioEmailLogado());
    }

    @Transactional
    public void registrarCriacaoCooperativa(Long cooperativaId, String nomeCooperativa) {
        AtividadeRecenteRequest request = atividadeRecenteMapper.criarRequestCooperativa(cooperativaId, nomeCooperativa);
        registrarAtividade(request, atividadeHelper.getUsuarioEmailLogado());
    }
}
