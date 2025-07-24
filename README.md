
# 🤝 Meli

Uma aplicação Android moderna para explorar e visualizar produtos do [Mercado Livre](https://www.mercadolivre.com.br/).

Projeto desenvolvido **como solução para um desafio técnico de uma vaga mobile**.

## 💡 Visão Geral

Este projeto é uma **aplicação Android nativa** que simula a experiência de busca e visualização de produtos do Mercado Livre. 
O foco principal é demonstrar as capacidades de desenvolvimento moderno em Android, desde a arquitetura do aplicativo até a implementação de uma interface de usuário responsiva.
Ele foi construído com a premissa de ser escalável e de fácil manutenção, seguindo as melhores práticas.

## ✨ Funcionalidades

- **Pesquisa Dinâmica:** 🔍 Busque produtos específicos do Mercado Livre diretamente do App.
- **Detalhes do Produto:** 📊 Visualize informações detalhadas de cada produto.

⚠️ **ALERTA IMPORTANTE:**
Atualmente, a funcionalidade de busca está operando apenas com dados mockados. Isso se deve a um erro `403 Forbidden` ao tentar acessar a API real do Mercado Livre.

**Buscas mockadas aceitas:** iphone, camisa, cafe e arroz.

## 🛠️ Tecnologias Utilizadas 

Este projeto foi construído utilizando as seguintes tecnologias e ferramentas principais:

- **Linguagem:** Kotlin
- **Interface de Usuário (UI):** Jetpack Compose
- **Arquitetura:** Segue os princípios de **MVVM (Model-View-ViewModel)** com uma abordagem de **Clean Architecture**, promovendo a separação de preocupações.
- **Gerenciamento de Estado:** **StateFlow** para reatividade e manipulação assíncrona de dados na UI.
- **Requisições de Rede:** **Retrofit** para consumo de APIs RESTful e **OkHttp** para otimização de requisições HTTP.
- **Serialização de Dados:** **Kotlinx Serialization** para converter JSON em objetos Kotlin e vice-versa, garantindo interoperabilidade com APIs.
- **Injeção de Dependência:** **Koin** para gerenciar e fornecer dependências de forma simples e eficiente.
- **Navegação:** **Jetpack Navigation Compose** para gerenciar a navegação entre as telas do aplicativo.
- **Carregamento de Imagens:** **Coil** para carregamento e cache eficiente de imagens assíncronas.
- **Testes:** **Testes unitários** foram implementados utilizando **JUnit** e bibliotecas de mocking para garantir a confiabilidade do código.
- **Integração Contínua (CI):** **GitHub Actions** para a automação da **execução de testes** no pipeline de desenvolvimento.

## 📂 Estrutura do Projeto

A estrutura de pacotes e módulos é organizada da seguinte forma:

- `data/`: A camada de dados. É responsável pela implementação das interfaces de repositório definidas na camada de domínio. Lida com a origem dos dados, seja de fontes remotas (APIs REST, utilizando DTOs) ou locais (banco de dados).

- `di/`: Módulo de Injeção de Dependência. Contém o módulo Koin responsável por definir e fornecer as dependências do aplicativo, garantindo que os componentes recebam suas dependências de forma organizada e desacoplada.

- `domain/`: A camada de domínio. Contém a definição de exceptions personalizadas, modelos de domínio e interfaces de repositório que orquestram as operações.

- `ui/`: A camada de interface do usuário. Contém as telas (Screens/Composables) e suas respectivas ViewModels, responsáveis por gerenciar a lógica da UI e interagir com a camada de domínio.

## 🗺️ Próximos Passos

- [ ] **Gerenciamento do Access Token:** Implementar um `TokenManager` para lidar com o armazenamento seguro, a exposição e o refresh do `access_token`.

- [ ] **Firebase Remote Config:** Integrar o Firebase Remote Config para gerenciar e buscar dados de configuração de forma remota, permitindo atualizações dinâmicas sem a necessidade de um novo deploy.

- [ ] **Detalhes do Produto (API):** Desenvolver a implementação do `getProductDetails` na camada de dados remota (API).

- [ ] **Melhorias em Testes Unitários:** Refinar e expandir a cobertura dos testes unitários existentes e implementação de testes na camada de ViewModel.

## 📝 Licença

Este projeto está sob a licença MIT.

Feito por Matheus Juan. [Entre em contato](https://www.linkedin.com/in/matheusjuan1/)

<div align="center">
  <img width="60" alt="Image" src="https://github.com/user-attachments/assets/efd1d014-148c-4ae8-8dbd-81850fadf9ba" />
</div>
