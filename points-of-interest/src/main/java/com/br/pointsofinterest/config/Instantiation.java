package com.br.pointsofinterest.config;

import com.br.pointsofinterest.model.Ponto;
import com.br.pointsofinterest.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private PontoRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Ponto(null, "Lanchonete", 27, 12));
        repository.save(new Ponto(null, "Posto", 31, 18));
        repository.save(new Ponto(null, "Joalheria", 15, 12));
        repository.save(new Ponto(null, "Floricultura", 19, 21));
        repository.save(new Ponto(null, "Pub", 12, 8));
        repository.save(new Ponto(null, "Supermercado", 23, 6));
        repository.save(new Ponto(null, "Churrascaria", 28, 2));
    }

}
