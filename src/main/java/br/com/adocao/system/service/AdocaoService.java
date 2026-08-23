package br.com.adocao.system.service;

import br.com.adocao.system.dto.AdocaoRequestDTO;
import br.com.adocao.system.model.Adocao;
import br.com.adocao.system.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository adocaoRepository;

    public AdocaoRequestDTO buscar(Long id) {

        Adocao adocao = adocaoRepository.findById(id);
         .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado"
        ));
         return
    }
}
