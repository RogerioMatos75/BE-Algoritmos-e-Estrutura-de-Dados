ATIVIDADE FINAL
Envio de Arquivo  


Projeto Entregável Final: Desafios de Melhoria e Implementação.

Olá, estudante! 

Você chegou à etapa final do nosso módulo, onde terá a oportunidade de aplicar seus conhecimentos em programação de forma prática e desafiadora! Neste projeto, você analisará o código disponibilizado pelo professor neste repositório do GitHub  (https://github.com/ITalents/BE-Algoritmos-e-Estrutura-de-Dados/tree/main/Unidade%2004%2FProjeto ) e implementará mudanças e melhorias significativas. 

Este projeto envolve a implementação de três métodos essenciais que contribuirão para aprimorar a funcionalidade do sistema de gerenciamento de pedidos de pizzas. Vamos às diretrizes: 

1 - Método Alterar Pedido: Neste primeiro desafio, você deverá implementar um método que permita alterar pedidos já existentes. Para isso, respeite os seguintes pontos: 

•	Solicitar o ID do pedido ou o nome do cliente para localizar o pedido desejado. 
•	Possibilitar a adição ou remoção de pizzas do pedido. 
•	Permitir a alteração do sabor da pizza. 
 
2 - Método Gerar Relatório: Agora é hora de reunir dados importantes! Implemente um método que gere relatórios detalhados, respeitando as seguintes diretrizes: 

•	Utilize grafos para representar as ligações entre os sabores de pizza mais pedidos. 
•	Exiba o faturamento total, os sabores mais pedidos e as conexões entre os sabores que frequentemente aparecem na mesma pizza. 
 
3 - Método para Cálculo de Frete: Por último, você vai desenvolver um método que calcule o frete das entregas. Este cálculo deve considerar: 

•	A distância (em quilômetros) entre o local da pizzaria e o destino. 
•	O peso (quantidade de pizzas) do pedido. 

==========================================================================

Ao final, você deverá entregar todo o código modificado, juntamente com um vídeo de aproximadamente 5 minutos explicando detalhadamente todos os pontos que você implementou. Lembre-se: a apresentação em vídeo é obrigatória para a aprovação deste projeto. 

Instruções de Entrega

	Realize todos os exercícios da lista na linguagem de programação Java e aplicando os conceitos solicitados em cada questão. 

	Envie o código para análise, pode ser por repositório do GitHub ou a pasta compactada com seu código.

---

## Plano de Implementação (README do Projeto)

Este documento servirá como um guia para as melhorias a serem implementadas no projeto da Pizzaria. Toda a interação com o usuário será feita através de interfaces gráficas com **JOptionPane**.

### 1. Método Alterar Pedido
- **O que foi implementado:**
    - Toda a interação do usuário foi migrada de console (`Scanner`) para uma interface gráfica com janelas de diálogo (`JOptionPane`).
    - O método `alterarPedido` foi totalmente implementado, permitindo a busca de pedidos por ID ou nome do cliente.
    - Após encontrar o pedido, um menu de ações em loop permite realizar múltiplas alterações:
        - **Adicionar Novas Pizzas:** Um fluxo completo para criação de pizzas foi adicionado.
        - **Remover Pizzas:** O usuário pode selecionar e remover pizzas existentes no pedido através de um menu dropdown.
        - **Alterar Sabores:** Um fluxo completo permite selecionar uma pizza, um sabor antigo e um novo sabor para troca.
    - O valor total do pedido é recalculado e atualizado dinamicamente após cada alteração.
    - A classe `Pedido.java` foi aprimorada com métodos `getters` e `setters` para permitir a manipulação de seus dados.

### 2. Método Gerar Relatório
- **O que foi implementado:**
    - A lógica de geração de relatórios foi encapsulada em uma nova classe de serviço, `Relatorio.java`, promovendo uma arquitetura limpa e a separação de responsabilidades.
    - O faturamento total é calculado somando o valor de todos os pedidos registrados.
    - Foi implementado um sistema de ranking que conta a ocorrência de cada sabor de pizza e os exibe em ordem de popularidade.
    - Para atender ao requisito de grafos, foi utilizada uma estrutura de dados (`Map` aninhado) para mapear as conexões entre sabores que são pedidos juntos na mesma pizza, registrando a frequência de cada par.
    - O relatório final, contendo todas essas informações, é apresentado ao usuário de forma clara e organizada em uma janela de diálogo (`JOptionPane`) com barra de rolagem, garantindo uma boa visualização.

### 3. Método para Cálculo de Frete
- **O que foi implementado:**
    - A classe `Pedido` foi estendida para incluir um campo `valorFrete`, permitindo que o custo da entrega seja armazenado junto aos dados do pedido.
    - Uma função `calcularFrete` foi criada em `Pizzaria.java`, utilizando uma fórmula que considera uma taxa fixa, o custo por quilômetro e um valor por quantidade de pizzas.
    - O cálculo foi integrado ao final do fluxo de *"Fazer Pedido"*:
        - O sistema pergunta se o usuário deseja entrega e solicita a distância.
        - A entrada da distância é tratada de forma robusta, com um loop de validação que limpa o texto digitado pelo usuário *(ex: "km", vírgulas)* e pede a informação novamente em caso de erro, tornando a interação mais inteligente e à prova de falhas.
    - O método `alterarPedido` foi ajustado para que, ao recalcular o valor total após uma modificação, o `valorFrete` original do pedido seja sempre preservado e somado corretamente.

---

### Melhoria Adicional: Persistência de Dados com JSON
- **O que foi implementado:**
    - Para resolver a perda de dados a cada reinicialização do programa, foi implementado um sistema de persistência de dados utilizando arquivos JSON.
    - Foi adicionada a biblioteca **Gson** da Google ao projeto, uma solução robusta e padrão de mercado para serialização/desserialização de objetos Java.
    - A lógica de manipulação de arquivos foi centralizada em uma nova classe `DataManager.java`, responsável por salvar e carregar as listas de clientes e pedidos.
    - O fluxo da aplicação em `Pizzaria.java` foi modificado para:
        - **Carregar** todos os dados dos arquivos `clientes.json` e `pedidos.json` ao iniciar.
        - **Salvar** o estado atual das listas de volta para os arquivos JSON quando o usuário fecha a aplicação.
    - Os arquivos de dados são armazenados em um novo diretório `Projeto/data/` para manter o projeto organizado.
    - Os comandos de compilação e execução foram atualizados para incluir a biblioteca Gson no classpath:
      ```java
      Compilar
      javac -cp ".;Projeto/lib/gson-2.10.1.jar" Projeto/*.java
      ```
      ```java
      Executar
      java -cp ".;Projeto/lib/gson-2.10.1.jar" Projeto.Pizzaria
      ```