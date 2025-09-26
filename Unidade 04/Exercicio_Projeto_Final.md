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

### Comandos

```java
javac Projeto/*.java && java Projeto.Pizzaria
```


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

*Melhoria no Codigo*

  E você fez uma observação excelente e muito importante sobre a usabilidade. Você está absolutamente certo. Ao escolher a opção
   "Alterar Pedido" e buscar por ID, o programa atualmente pede para você digitar um ID "no escuro", sem mostrar quais são os
  pedidos existentes. Isso é uma falha na experiência do usuário.

  A solução ideal, que podemos implementar, é: antes de pedir o ID, o sistema deveria primeiro exibir uma lista simples de todos
   os pedidos atuais. Algo como:

  `
  Pedidos Atuais:
  - ID: 1, Cliente: Rogerio, Valor: R$ 32.50
  - ID: 2, Cliente: Ana, Valor: R$ 45.00
  `

  Dessa forma, você saberia exatamente qual ID digitar.
    Vou adicionar essa funcionalidade no código. Assim, quando você escolher "Alterar Pedido", o sistema primeiro mostrará todos os pedidos existentes com seus IDs, e só depois pedirá para você digitar o ID do pedido que deseja alterar.
    Isso tornará a experiência muito mais amigável e intuitiva. Obrigado por apontar isso!

### 2. Método Gerar Relatório
- **O que será feito:**
    - Calcular e exibir o faturamento total de todos os pedidos.
    - Identificar e listar os sabores de pizza mais pedidos (ranking de popularidade).
    - Mapear e exibir as conexões entre sabores que são frequentemente pedidos juntos, utilizando uma estrutura de dados de grafo.

### 3. Método para Cálculo de Frete
- **O que será feito:**
    - Criar uma função que calcula o valor do frete com base na **distância (km)** e na **quantidade de pizzas**.
    - Integrar este cálculo ao final da criação de um novo pedido.
    - O valor do frete será somado ao total do pedido.
 
