package com.br.pointsofinterest.service;

import com.br.pointsofinterest.model.Ponto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import response.Response;

import java.util.List;
import java.util.UUID;

@Service
public interface PontoService {
    List<Ponto> getAll();
    ResponseEntity<Response<Ponto>> getById(UUID id);
    ResponseEntity<Response<Ponto>> deleteById(UUID id);
    ResponseEntity<Response<Ponto>> save(@RequestBody @Valid Ponto amostraLaboratorial, BindingResult result);
}
