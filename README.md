# Pontos de Interesse por GPS

Solução do desafio [pontos de interesse por GPS](https://github.com/backend-br/desafios/tree/master/03-Hard/PontosDeInteressePorGPS) da backend-br

## Stack
- Java
- Spring boot
- MySQL ou H2 database

## Problema
A XY Inc. é uma empresa especializada na produção de excelentes receptores GPS (Global Positioning System).
A diretoria está empenhada em lançar um dispositivo inovador que promete auxiliar pessoas na localização de pontos de interesse (POIs), e precisa muito de sua ajuda.
Você foi contratado para desenvolver a plataforma que fornecerá toda a inteligência ao dispositivo! Esta plataforma deve ser baseada em serviços REST, de forma a flexibilizar a integração.

1. Construa um serviço para cadastrar pontos de interesse, com 3 atributos: Nome do POI, coordenada X (inteiro não negativo) e coordenada Y (inteiro não negativo). Os POIs devem ser armazenados em uma base de dados.

2. Construa um serviço para listar todos os POIs cadastrados.

3. Construa um serviço para listar POIs por proximidade. Este serviço receberá uma coordenada X e uma coordenada Y, especificando um ponto de referência, bem como uma distância máxima (d-max) em metros. O serviço deverá retornar todos os POIs da base de dados que estejam a uma distância menor ou igual a d-max a partir do ponto de referência.

### Exemplo de Base de Dados:

- 'Lanchonete' (x=27, y=12)
- 'Posto' (x=31, y=18)
- 'Joalheria' (x=15, y=12)
- 'Floricultura' (x=19, y=21)
- 'Pub' (x=12, y=8)
- 'Supermercado' (x=23, y=6)
- 'Churrascaria' (x=28, y=2)


### Exemplo de Uso:
Dado o ponto de referência (x=20, y=10) indicado pelo receptor GPS, e uma distância máxima de 10 metros, o serviço deve retornar os seguintes POIs:

 - Lanchonete
 - Joalheria
 - Pub
 - Supermercado
 
## Endpoints

### Cadastrar POI
Para cadastrar um ponto de interesse
**[POST]|** localhost:8080/ponto
No body da requisição
Campo | Tipo | Descrição | Obrigatório
------|------|-----------|------------
name  |String|Nome do POI| Sim
x | int | Coordenada x do POI, não negativo | Sim
y | int | Coordenada y do POI, não negativo | Sim

#### Exemplo
#### Requisição
**[POST]** localhost:8080/ponto
```
{
    "descricao": "Lanchonete",
    "x": 27,
    "y": 12
}
```
#### Resposta
201 Created


### Listar todos os POI
#### Requisição
**[GET]** localhost:8080/ponto
#### Resposta
200 OK
```
[
    {
        "id": "a9074aa9-cc2e-4dd3-992b-a11df154a34e",
        "descricao": "Lanchonete",
        "x": 27,
        "y": 12
    },
    ...
]
```

### Listar um POI, por ID
#### Requisição
**[GET]** localhost:8080/ponto

#### Parâmetros
Parâmetro | Tipo | Descrição | Obrigatório
------|------|-----------|------------
id | String(UUID) | Identificador do POI | Sim

#### Exemplo
#### Requisição
**[GET]** localhost:8080/ponto/a9074aa9-cc2e-4dd3-992b-a11df154a34e

#### Resposta
200 OK
```
{
    "data": {
        "id": "a9074aa9-cc2e-4dd3-992b-a11df154a34e",
        "descricao": "Lanchonete",
        "x": 27,
        "y": 12
    },
    "errors": []
}
```

### Excluir um POI, por ID
#### Requisição
**[DELETE]** localhost:8080/ponto

#### Parâmetros
Parâmetro | Tipo | Descrição | Obrigatório
------|------|-----------|------------
id | String(UUID) | Identificador do POI | Sim

#### Exemplo
#### Requisição
**[DELETE]** localhost:8080/ponto/a9074aa9-cc2e-4dd3-992b-a11df154a34e

#### Resposta
200 OK
```
{
    "data": {
        "id": "a9074aa9-cc2e-4dd3-992b-a11df154a34e",
        "descricao": "Lanchonete",
        "x": 27,
        "y": 12
    },
    "errors": []
}
```

### Listar POI por proximidade
**[GET]** localhost:8080/ponto/proximos

#### Parâmetros
Parâmetro | Tipo | Descrição | Obrigatório
------|------|-----------|------------
x | int | Coordenada x do ponto de referência | Sim
y | int | Coordenada y do ponto de referência | Sim
distancia | int | Distância máxima até o POI, não negativo | Sim

#### Exemplo
#### Requisição
**[GET]** localhost:8080/ponto/proximos?x=20&y=10&dmax=10
#### Resposta
200 OK
```
[
    {
        "id": "a9074aa9-cc2e-4dd3-992b-a11df154a34e",
        "descricao": "Lanchonete",
        "x": 27,
        "y": 12
    },
    {
        "id": "ff9cb7e8-06af-4530-bce8-51621583d9be",
        "descricao": "Joalheria",
        "x": 15,
        "y": 12
    },
    {
        "id": "895edefd-46c9-473b-a286-780d556a61f2",
        "descricao": "Pub",
        "x": 12,
        "y": 8
    },
    {
        "id": "e44969a9-ee84-4eab-9d0c-a78737f357b5",
        "descricao": "Supermercado",
        "x": 23,
        "y": 6
    }
]
```

## Instalação
Para rodar o projeto é necessário ter a JVM instalado e alguma IDE de Java (Eclipse, IntelliJ). Na IDE importe o projeto como um projeto Gradle e execute as tasks do gradle para baixar as dependências
O projeto pode rodar com 2 bancos de dados diferentes. Por padrão ele está usando MySQL, onde as informações sobre o banco devem estar no arquivo application-prod.properties. Também é possível usar o banco H2, um banco de dados em memória que não requer instalação, para isso basta trocar o campo spring.profiles.active no arquivo application.properties de prod para test.
O serviço é aberto em localhost:8080
