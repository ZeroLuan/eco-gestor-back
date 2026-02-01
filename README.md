# 🌿 EcoGestor - Backend

Sistema de Gestão Ambiental Municipal de Irecê, Bahia - API REST

[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)
[![Java Version](https://img.shields.io/badge/java-21-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/spring--boot-4.0.2-brightgreen.svg)](https://spring.io/projects/spring-boot)

## 📋 Sobre o Projeto

O **EcoGestor Backend** é uma API REST robusta e segura desenvolvida com Spring Boot para gestão ambiental municipal. O sistema fornece endpoints completos para:

- 🔐 **Autenticação JWT** - Sistema de login seguro com tokens JWT
- 📊 **Dashboard** - Estatísticas e métricas em tempo real
- 📍 **Pontos de Coleta** - CRUD completo de pontos de coleta seletiva
- 📄 **Licenças Ambientais** - Gestão e controle de licenças
- 👥 **Cooperativas** - Gerenciamento de cooperativas parceiras
- ♻️ **Resíduos** - Registro e monitoramento de resíduos coletados
- 📍 **Endereços** - Integração com API ViaCEP para endereços
- 👤 **Usuários** - Controle de acesso e perfis

## 🚀 Tecnologias

Este projeto foi desenvolvido com as seguintes tecnologias:

- ☕ **[Java 21](https://openjdk.org/)** - Linguagem de programação moderna e robusta
- 🍃 **[Spring Boot 4.0.2](https://spring.io/projects/spring-boot)** - Framework principal
- 🔒 **[Spring Security](https://spring.io/projects/spring-security)** - Autenticação e autorização
- 🎫 **[OAuth2 Resource Server](https://spring.io/projects/spring-security-oauth)** - JWT tokens
- 💾 **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)** - Persistência de dados
- 🐘 **[PostgreSQL](https://www.postgresql.org/)** - Banco de dados relacional
- 📮 **[Spring Mail](https://docs.spring.io/spring-framework/reference/integration/email.html)** - Envio de e-mails
- 🎯 **[Lombok](https://projectlombok.org/)** - Redução de boilerplate
- 🔧 **[Gradle](https://gradle.org/)** - Gerenciamento de dependências e build

## 📦 Estrutura do Projeto

```
eco-gestor-back/
├── src/
│   ├── main/
│   │   ├── java/br/com/ecogestor/
│   │   │   ├── cooperativa/          # Módulo de Cooperativas
│   │   │   │   ├── controller/       # Endpoints REST
│   │   │   │   ├── service/          # Regras de negócio
│   │   │   │   ├── repository/       # Acesso ao banco
│   │   │   │   ├── entity/           # Entidades JPA
│   │   │   │   ├── dto/              # DTOs Request/Response
│   │   │   │   └── mapper/           # Conversores Entity <-> DTO
│   │   │   │
│   │   │   ├── pontocoleta/          # Módulo de Pontos de Coleta
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── entity/
│   │   │   │   └── dto/
│   │   │   │
│   │   │   ├── residuos/             # Módulo de Resíduos
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── entity/
│   │   │   │   └── dto/
│   │   │   │
│   │   │   ├── licenca/              # Módulo de Licenças Ambientais
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── entity/
│   │   │   │   └── dto/
│   │   │   │
│   │   │   ├── endereco/             # Módulo de Endereços
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── entity/
│   │   │   │   └── dto/
│   │   │   │
│   │   │   ├── login/                # Módulo de Autenticação
│   │   │   │   ├── controller/       # TokenController, UsuarioController
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── entity/           # Usuario, Role
│   │   │   │   └── dto/
│   │   │   │
│   │   │   ├── dashboard/            # Módulo de Dashboard
│   │   │   │   ├── controller/       # Estatísticas e métricas
│   │   │   │   └── service/
│   │   │   │
│   │   │   ├── shared/               # Recursos compartilhados
│   │   │   │   ├── config/           # Configurações (Security, Admin)
│   │   │   │   └── enums/            # Enumerações do sistema
│   │   │   │
│   │   │   └── EcoGestorBackApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties  # Configurações da aplicação
│   │       ├── app.pub                 # Chave pública JWT
│   │       └── app.key                 # Chave privada JWT
│   │
│   └── test/
│       └── java/br/com/ecogestor/
│           └── EcoGestorBackApplicationTests.java
│
├── build.gradle              # Configuração de dependências
├── gradlew                   # Gradle wrapper (Unix)
├── gradlew.bat              # Gradle wrapper (Windows)
├── settings.gradle          # Configurações do projeto
└── README.md
```

## 🔧 Pré-requisitos

Antes de começar, você precisa ter instalado:

- [Java JDK 21](https://openjdk.org/) ou superior
- [Gradle](https://gradle.org/) (ou usar o wrapper incluído)
- [PostgreSQL](https://www.postgresql.org/) (ou acesso ao Supabase)
- [Git](https://git-scm.com/)

## 🎯 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/ZeroLuan/eco-gestor-back.git
cd eco-gestor-back
```

### 2. Configure o Banco de Dados

**Opção A: PostgreSQL Local**

Descomente as linhas de configuração local no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecogestor
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
```

**Opção B: Supabase (Produção)**

Configure a variável de ambiente `DB_PASSWORD`:

```bash
export DB_PASSWORD=sua_senha_supabase
```

### 3. Configure as Chaves JWT

As chaves JWT já estão incluídas no projeto em `src/main/resources/`:
- `app.pub` - Chave pública
- `app.key` - Chave privada

**Para gerar novas chaves (opcional):**

```bash
# Gera chave privada
openssl genrsa -out app.key 2048

# Gera chave pública
openssl rsa -in app.key -pubout -out app.pub
```

### 4. Build e execução

**Windows:**
```bash
gradlew.bat bootRun
```

**Linux/Mac:**
```bash
./gradlew bootRun
```

O servidor estará disponível em `http://localhost:8080`

## 📜 Scripts Gradle Disponíveis

```bash
# Compilar o projeto
./gradlew build

# Executar aplicação
./gradlew bootRun

# Executar testes
./gradlew test

# Limpar build
./gradlew clean

# Gerar JAR executável
./gradlew bootJar

# Build completo (limpa + compila + testa)
./gradlew clean build
```

## 🔌 API Endpoints

### 🔐 Autenticação

```http
POST /api/token/login
Content-Type: application/json

{
  "email": "usuario@email.com",
  "password": "senha123"
}

Response: {
  "accessToken": "eyJhbGc...",
  "expiresIn": 1000
}
```

### 📊 Dashboard

```http
GET /api/dashboard/total-pontos-ativos
GET /api/dashboard/total-peso-mes
GET /api/dashboard/residuos-por-tipo
GET /api/dashboard/residuos-ultimos-meses
GET /api/dashboard/licencas-proximas-vencer
```

### 📍 Pontos de Coleta

```http
GET    /api/pontos-coleta              # Listar todos
GET    /api/pontos-coleta/{id}         # Buscar por ID
POST   /api/pontos-coleta              # Criar novo
PUT    /api/pontos-coleta/{id}         # Atualizar
DELETE /api/pontos-coleta/{id}         # Deletar
GET    /api/pontos-coleta/status/{status}  # Filtrar por status
```

### 📄 Licenças Ambientais

```http
GET    /api/licencas-ambientais        # Listar todas
GET    /api/licencas-ambientais/{id}   # Buscar por ID
POST   /api/licencas-ambientais        # Criar nova
PUT    /api/licencas-ambientais/{id}   # Atualizar
DELETE /api/licencas-ambientais/{id}   # Deletar
GET    /api/licencas-ambientais/proximas-vencer/{dias}  # Alertas
```

### 👥 Cooperativas

```http
GET    /api/cooperativas               # Listar todas
GET    /api/cooperativas/{id}          # Buscar por ID
POST   /api/cooperativas               # Criar nova
PUT    /api/cooperativas/{id}          # Atualizar
DELETE /api/cooperativas/{id}          # Deletar
GET    /api/cooperativas/cnpj/{cnpj}   # Buscar por CNPJ
```

### ♻️ Resíduos

```http
GET    /api/residuos                   # Listar todos
GET    /api/residuos/{id}              # Buscar por ID
POST   /api/residuos                   # Registrar novo
PUT    /api/residuos/{id}              # Atualizar
DELETE /api/residuos/{id}              # Deletar
GET    /api/residuos/tipo/{tipo}       # Filtrar por tipo
```

### 📍 Endereços

```http
GET    /api/enderecos/cep/{cep}        # Buscar por CEP (ViaCEP)
GET    /api/enderecos/{id}             # Buscar por ID
POST   /api/enderecos                  # Criar novo
PUT    /api/enderecos/{id}             # Atualizar
DELETE /api/enderecos/{id}             # Deletar
```

### 👤 Usuários

```http
GET    /api/usuarios                   # Listar todos
GET    /api/usuarios/{id}              # Buscar por ID
POST   /api/usuarios                   # Criar novo
PUT    /api/usuarios/{id}              # Atualizar
DELETE /api/usuarios/{id}              # Deletar
```

## 🔒 Segurança

### Autenticação JWT

O sistema utiliza **JWT (JSON Web Token)** para autenticação:

1. O cliente faz login com email e senha
2. O servidor valida as credenciais
3. Um token JWT é gerado e retornado
4. O cliente inclui o token no header de requisições autenticadas:

```http
Authorization: Bearer eyJhbGciOiJSUzI1NiJ9...
```

### Configuração CORS

Configure CORS no frontend para permitir requisições:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

### Roles e Permissões

O sistema implementa controle de acesso baseado em roles:
- **ADMIN** - Acesso total ao sistema
- **USER** - Acesso limitado (leitura)

## 🗃️ Banco de Dados

### Conexão PostgreSQL

O sistema está configurado para PostgreSQL com as seguintes características:

```properties
# Pool de Conexões HikariCP
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.max-lifetime=600000
spring.datasource.hikari.connection-timeout=30000
```

### JPA/Hibernate

```properties
spring.jpa.hibernate.ddl-auto=update    # Atualiza schema automaticamente
spring.jpa.show-sql=true                # Mostra SQL no console
```

### Principais Entidades

- **Usuario** - Usuários do sistema
- **Role** - Perfis de acesso
- **PontoColeta** - Pontos de coleta seletiva
- **Residuo** - Resíduos coletados
- **LicencaAmbiental** - Licenças ambientais
- **Cooperativa** - Cooperativas parceiras
- **Endereco** - Endereços completos

## 🎨 Funcionalidades

### ✅ Autenticação e Autorização
- Login com email e senha
- Tokens JWT com expiração configurável
- Controle de acesso baseado em roles
- Criptografia de senhas com BCrypt

### ✅ CRUD Completo
- Pontos de Coleta
- Resíduos
- Licenças Ambientais
- Cooperativas
- Usuários
- Endereços

### ✅ Dashboard e Estatísticas
- Total de pontos de coleta ativos
- Peso total de resíduos no mês
- Resíduos agrupados por tipo
- Histórico dos últimos 6 meses
- Licenças próximas do vencimento

### ✅ Integrações
- API ViaCEP para busca de endereços
- Sistema de envio de e-mails (Spring Mail)
- Supabase PostgreSQL em produção

### ✅ Validações e Segurança
- Validação de dados com Bean Validation
- Tratamento de exceções personalizado
- Logs estruturados com SLF4J
- Pool de conexões otimizado (HikariCP)

## 🔄 Enumerações do Sistema

### EnumStatus
```java
ATIVO, INATIVO, PENDENTE, CANCELADO
```

### EnumTipoResiduo
```java
PLASTICO, PAPEL, VIDRO, METAL, ORGANICO, ELETRONICO
```

### EnumTipoLicenca
```java
LI, LP, LO, LAU  // Instalação, Prévia, Operação, Autorização
```

### EnumEstados
```java
AC, AL, AP, AM, BA, CE... // Todos os estados brasileiros
```

## 🚀 Deploy

### Gerando JAR para produção

```bash
./gradlew clean bootJar
```

O arquivo JAR será gerado em: `build/libs/eco-gestor-back-0.0.1-SNAPSHOT.jar`

### Executando o JAR

```bash
java -jar build/libs/eco-gestor-back-0.0.1-SNAPSHOT.jar
```

### Variáveis de Ambiente

Configure as seguintes variáveis em produção:

```bash
DB_PASSWORD=sua_senha_banco
SPRING_PROFILES_ACTIVE=production
SERVER_PORT=8080
```

## 📝 Logs

O sistema utiliza **SLF4J** com **Logback** para logging estruturado:

```java
@Slf4j
public class MeuController {
    public void metodo() {
        log.info("Mensagem informativa");
        log.warn("Aviso importante");
        log.error("Erro crítico", exception);
    }
}
```

## 🧪 Testes

Execute os testes com:

```bash
./gradlew test
```

Relatório de testes disponível em:
`build/reports/tests/test/index.html`

## 📖 Convenções de Código

- **Nomenclatura**: CamelCase para classes, camelCase para métodos
- **Pacotes**: Organização modular por domínio
- **DTOs**: Separação entre Request e Response
- **Services**: Lógica de negócio isolada
- **Controllers**: Apenas roteamento e validação básica
- **Repositories**: Interface JPA sem lógica

## 🤝 Integração com Frontend

Para integrar com o frontend React/Vite:

1. Configure CORS para aceitar `http://localhost:5173`
2. Inicie o backend na porta `8080`
3. Configure `VITE_API_URL=http://localhost:8080/api` no frontend
4. Use os endpoints documentados acima

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

Desenvolvido com ☕ e 🍃 para a gestão ambiental de Irecê, Bahia
