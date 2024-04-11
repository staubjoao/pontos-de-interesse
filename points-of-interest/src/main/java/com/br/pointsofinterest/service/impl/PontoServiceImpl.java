package com.br.pointsofinterest.service.impl;

import com.br.pointsofinterest.model.Ponto;
import com.br.pointsofinterest.repository.PontoRepository;
import com.br.pointsofinterest.service.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import response.Response;

import java.util.List;
import java.util.UUID;

@Service
public class PontoServiceImpl implements PontoService {

    @Autowired
    private PontoRepository repository;

    @Override
    public List<Ponto> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<Response<Ponto>> getById(UUID id) {
        Response<Ponto> response = new Response<>();
        Ponto obj = null;
        try {
            obj = repository.findById(id).get();
        } catch (Exception e) {
            response.addError("Ponto não encontrado");
            response.addError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(obj);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Ponto>> deleteById(UUID id) {
        Response<Ponto> response = new Response<>();
        Ponto obj = null;
        try {
            obj = repository.findById(id).get();
            repository.delete(obj);
        } catch (Exception e) {
            response.addError("Erro ao excluir o ponto ou ponto não encontrado");
            response.addError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(obj);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Ponto>> save(Ponto ponto, BindingResult result) {
        Response<Ponto> response = new Response<Ponto>();
        response.setData(ponto);
        if(ponto.getX() < 0 || ponto.getY() < 0){
            response.addError("Não é aceito pontos negativos");
            return ResponseEntity.badRequest().body(response);
        }
        if (result.hasErrors()) {
            for (ObjectError erros : result.getAllErrors()) {
                response.addError(erros.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(response);
        }
        try {
            repository.save(ponto);
        }catch (Exception e) {
            response.addError(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

}
