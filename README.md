# Desafio_Tecnologia_Bolt
Repositório para armazenar a api do desafio tecnologia bolt

# Informações
Para a construção dessa api foram utilizadas as tecnologia Java 8 e Spring Boot. Além disso, tomou-se a decisão de fazer uso do banco de dados H2 que é um banco que funciona em memória.
Destaca-se que foram feitos testes automatizados para validar o service da aplicação.

# Pré requisitos
- Java 8
- IDE (Netbeans)
- Maven
- Postman

# Como rodar
- Abrir o projeto sequencia-api no Netbeans
- Fazer o "Clean and Build"
- Executar a classe SequenciaApiApplication.java

# Acessando o Endpoint
- Acessar o Postman
- Fazer uma requisição POST
- Colocar a url: "http://localhost:8080/api/sequenciaEstavel/cadastrar"
- No header, colocar o Content-Type com a informação "application/json"
- No body, passar a lista de entrada no json. Exemplo: ["{}{}", "{{{}}"]
- Enviar a requisição
- O resultado esperado é uma lista de números que representam a quantidade de operações necessárias para os dados de entrada se tornarem estáveis. Exemplo: [0, 1]
