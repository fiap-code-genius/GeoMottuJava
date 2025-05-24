# GeoMottu - Sistema de Gerenciamento de Motos

O GeoMottu é um sistema de gerenciamento de motos desenvolvido em Java com Spring Boot, projetado para controlar o fluxo de motos em diferentes filiais e pátios da empresa Mottu.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.5
- JPA/Hibernate
- Oracle Database
- Maven

## 📋 Pré-requisitos

- JDK 17
- Maven
- Oracle Database
- IntelliJ IDEA (recomendado)

## 🔧 Configuração do Ambiente

1. Clone o repositório:
```bash
git clone [url-do-repositorio]
```

2. Configure o banco de dados Oracle no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@[seu-host]:1521:[sid]
spring.datasource.username=[seu-usuario]
spring.datasource.password=[sua-senha]
spring.jpa.hibernate.ddl-auto=update
```

3. Importe o projeto no IntelliJ IDEA:
- File > Open > Selecione a pasta do projeto
- Aguarde o Maven baixar as dependências
- Configure o JDK 17 no projeto

## 🏗️ Estrutura do Projeto

### Camadas da Aplicação

1. **Controller**: Responsável por receber as requisições HTTP
2. **Service**: Contém a lógica de negócio
3. **Repository**: Interface com o banco de dados
4. **Model**: Entidades do sistema
5. **DTO**: Objetos de transferência de dados
6. **Annotation**: Anotações personalizadas

### Exemplo de Fluxo (Usando a classe Moto)

1. Controller (`MotoController.java`): Recebe requisições REST
2. DTO (`MotoDto.java`): Valida e transporta dados
3. Service (`MotoService.java`): Processa regras de negócio
4. Repository (`MotoRepository.java`): Realiza operações no banco
5. Entity (`Moto.java`): Representa a tabela no banco

## 📝 Anotação Personalizada @Unique

A anotação `@Unique` foi criada para validar campos únicos no banco de dados:

```java
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {
    String message() default "Valor já existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();
}
```

Exemplo de uso:
```java
@Unique(fieldName = "placa", domainClass = Moto.class)
private String placa;
```

## 🔄 Enums do Sistema

O sistema utiliza três enums principais para controle de estados e tipos:

### EstadoMoto
Controla o estado operacional da moto:
```java
public enum EstadoMoto {
    MANUTENCAO,  // Moto em manutenção
    LIVRE,       // Moto disponível para uso
    ALUGADA      // Moto em uso por um cliente
}
```
Uso no JSON:
```json
{
    "estadoMoto": "LIVRE"
}
```

### TipoMoto
Define os modelos de motos disponíveis:
```java
public enum TipoMoto {
    MOTTUE,      // Modelo premium
    MOTTUSPORT,  // Modelo esportivo
    MOTTUPOP     // Modelo popular
}
```
Uso no JSON:
```json
{
    "tipoMoto": "MOTTUE"
}
```

### PaisesFilial
Indica os países onde a empresa possui filiais:
```java
public enum PaisesFilial {
    BRASIL,
    MEXICO
}
```
Uso no JSON:
```json
{
    "pais": "BRASIL"
}
```

### Exemplo de JSON Completo com Enums

```json
// Criação de uma nova moto
POST /moto
{
    "placa": "ABC-1234",
    "chassi": "9BWZZZ377VT004251",
    "tipoMoto": "MOTTUE",
    "estadoMoto": "LIVRE",
    "patioId": 1
}

// Criação de uma nova filial
POST /filial
{
    "nome": "Filial SP",
    "pais": "BRASIL",
    "endereco": {
        "estado": "São Paulo",
        "siglaEstado": "SP",
        "cidade": "São Paulo",
        "rua": "Av. Paulista, 1000"
    },
    "telefone": "11999999999",
    "email": "filial.sp@geomottu.com"
}
```

## 🌐 Endpoints da API

### Motos
```http
# Criar nova moto
POST /moto
{
    "placa": "ABC-1234",
    "chassi": "9BWZZZ377VT004251",
    "tipoMoto": "MOTTUE",
    "estadoMoto": "LIVRE",
    "patioId": 1
}

# Listar todas as motos
GET /moto

# Buscar moto por ID
GET /moto/{id}

# Buscar moto por placa
GET /moto/placa/{placa}

# Buscar moto por chassi
GET /moto/chassi/{chassi}

# Atualizar moto
PUT /moto/{id}
{
    "id": 1,
    "placa": "ABC-1234",
    "chassi": "9BWZZZ377VT004251",
    "tipoMoto": "MOTTUE",
    "estadoMoto": "MANUTENCAO",
    "patioId": 1
}

# Deletar moto
DELETE /moto/{id}
```

### Pátios
```http
# Criar novo pátio
POST /patio
{
    "nome": "Pátio Central",
    "capacidadeTotal": 100,
    "filialId": 1,
    "motos": []
}

# Listar todos os pátios
GET /patio

# Buscar pátio por ID
GET /patio/{id}

# Atualizar pátio
PUT /patio/{id}
{
    "id": 1,
    "nome": "Pátio Central Atualizado",
    "capacidadeTotal": 150,
    "filialId": 1,
    "motos": ["ABC-1234", "DEF-5678"]
}

# Deletar pátio
DELETE /patio/{id}
```

### Filiais
```http
# Criar nova filial
POST /filial
{
    "nome": "Filial SP",
    "pais": "BRASIL",
    "endereco": {
        "estado": "São Paulo",
        "siglaEstado": "SP",
        "cidade": "São Paulo",
        "rua": "Av. Paulista, 1000"
    },
    "telefone": "11999999999",
    "email": "filial.sp@geomottu.com",
    "usuarios": [],
    "patios": []
}

# Listar todas as filiais
GET /filial

# Buscar filial por ID
GET /filial/{id}

# Atualizar filial
PUT /filial/{id}
{
    "id": 1,
    "nome": "Filial SP Atualizada",
    "pais": "BRASIL",
    "endereco": {
        "estado": "São Paulo",
        "siglaEstado": "SP",
        "cidade": "São Paulo",
        "rua": "Av. Paulista, 2000"
    },
    "telefone": "11999999999",
    "email": "filial.sp.novo@geomottu.com",
    "usuarios": ["joao.silva", "maria.santos"],
    "patios": ["Pátio Central", "Pátio Sul"]
}

# Deletar filial
DELETE /filial/{id}
```

### Usuários
```http
# Criar novo usuário
POST /usuario
{
    "nome": "admin",
    "senha": "senha123",
    "tipoPerfil": 1,
    "filialId": 1
}

# Listar todos os usuários
GET /usuario

# Buscar usuário por ID
GET /usuario/{id}

# Buscar usuário por nome
GET /usuario/nome/{nome}

# Atualizar usuário
PUT /usuario/{id}
{
    "id": 1,
    "nome": "admin.atualizado",
    "senha": "novasenha123",
    "tipoPerfil": 2,
    "filial": 1
}

# Deletar usuário
DELETE /usuario/{id}
```

### Respostas Padrão

#### Sucesso
- `200 OK`: Requisição bem-sucedida

#### Erro
- `400 Bad Request`: Erro de validação ou dados inválidos
- `404 Not Found`: Recurso não encontrado
- `500 Internal Server Error`: Erro interno do servidor

### Exemplos de Respostas

#### GET /moto
```json
[
    {
        "id": 1,
        "placa": "ABC-1234",
        "chassi": "9BWZZZ377VT004251",
        "tipoMoto": "MOTTUE",
        "estadoMoto": "LIVRE",
        "patioId": 1
    },
    {
        "id": 2,
        "placa": "DEF-5678",
        "chassi": "9BWZZZ377VT004252",
        "tipoMoto": "MOTTUPOP",
        "estadoMoto": "ALUGADA",
        "patioId": 1
    }
]
```

#### GET /patio/{id}
```json
{
    "id": 1,
    "nome": "Pátio Central",
    "capacidadeTotal": 100,
    "filialId": 1,
    "motos": ["ABC-1234", "DEF-5678"]
}
```

## 🔍 Pesquisas Personalizadas Detalhadas

O sistema implementa buscas personalizadas nos repositories para facilitar consultas específicas. Aqui está o detalhamento de cada uma:

### MotoRepository

1. **findByPlacaIgnoreCase**
   ```java
   Optional<Moto> findByPlacaIgnoreCase(String placa)
   ```
   - **Descrição**: Busca uma moto pela placa, ignorando maiúsculas e minúsculas
   - **Uso**: `GET /moto/placa/{placa}`
   - **Exemplo**: `GET /moto/placa/ABC1234`
   - **Retorno**: Retorna os dados da moto se encontrada, ou vazio se não existir

2. **findByChassiIgnoreCase**
   ```java
   Optional<Moto> findByChassiIgnoreCase(String chassi)
   ```
   - **Descrição**: Busca uma moto pelo número do chassi, ignorando maiúsculas e minúsculas
   - **Uso**: `GET /moto/chassi/{chassi}`
   - **Exemplo**: `GET /moto/chassi/9BWZZZ377VT004251`
   - **Retorno**: Retorna os dados da moto se encontrada, ou vazio se não existir

### UsuarioRepository

1. **findByNomeIgnoreCase**
   ```java
   Optional<Usuario> findByNomeIgnoreCase(String nome)
   ```
   - **Descrição**: Busca um usuário pelo nome, ignorando maiúsculas e minúsculas
   - **Uso**: `GET /usuario/nome/{nome}`
   - **Exemplo**: `GET /usuario/nome/joao.silva`
   - **Retorno**: Retorna os dados do usuário se encontrado, ou vazio se não existir

### Exemplos de Uso

1. Buscar moto pela placa:
```bash
curl -X GET http://localhost:8080/moto/placa/ABC1234
```
Resposta:
```json
{
    "id": 1,
    "placa": "ABC1234",
    "chassi": "9BWZZZ377VT004251",
    "tipoMoto": "MOTTUE",
    "estadoMoto": "LIVRE",
    "patioId": 1
}
```

2. Buscar usuário pelo nome:
```bash
curl -X GET http://localhost:8080/usuario/nome/joao.silva
```
Resposta:
```json
{
    "id": 1,
    "nome": "joao.silva",
    "tipoPerfil": 1,
    "filialId": 1
}
```

### Características das Pesquisas

- Todas as pesquisas são case-insensitive (ignoram maiúsculas/minúsculas)
- Retornam `Optional` para melhor tratamento de resultados nulos
- Integradas com DTOs para retornar apenas os dados necessários

## 🚀 Como Executar

1. Abra o projeto no IntelliJ IDEA
2. Configure o banco de dados Oracle
3. Execute a classe principal `GeomottuApplication.java`
4. A aplicação estará disponível em `http://localhost:8080`

## 📦 Compilação

```bash
mvn clean install
```

## 🧪 Testes

```bash
mvn test
```
