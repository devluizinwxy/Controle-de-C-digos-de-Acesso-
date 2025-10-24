# Controle-de-C-digos-de-Acesso-
Sistema em Java para controle de códigos de acesso de visitantes e terceirizados. Gera, valida, salva e carrega códigos únicos usando POO, Regex, e manipulação de arquivos (NIO).
# 🧩 Controle de Códigos de Acesso

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Status](https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge)
![License](https://img.shields.io/badge/Licença-Acadêmica-blue?style=for-the-badge)
![UTF-8](https://img.shields.io/badge/Charset-UTF--8-lightgrey?style=for-the-badge)

---

## 👨‍🎓 Informações do Projeto

**Aluno:** Luís de Jesus Fernandes  
**Disciplina:** Programação Orientada a Objetos  
**Professor:** Juno Vitorino  
**Data de Entrega:** 17/10/2025  

---

## 🧠 Sobre o Projeto

O **Controle de Códigos de Acesso** é um sistema desenvolvido em **Java** para gerenciar **códigos únicos de visitantes e terceirizados**.  
Ele permite gerar, validar, salvar e carregar códigos em arquivos `.txt`, aplicando diversos conceitos de **POO** e **boas práticas de desenvolvimento**.

---

## ⚙️ Conceitos e Tecnologias Utilizadas

| Conceito | Descrição |
|-----------|------------|
| 🧱 **Interfaces** | Definem contratos de comportamento entre classes (`CodigoAcesso`). |
| 🔢 **Regex** | Usadas para validar o formato dos códigos (`VIS-AAA9999-X` e `TER-999-AAA-Y`). |
| 🚫 **Tratamento de Exceções** | Impede que erros em leitura/gravação interrompam o programa. |
| 🧩 **Encapsulamento** | Métodos `get` e `set` com validação para consistência dos dados. |
| 💾 **Manipulação de Arquivos** | Leitura e escrita em `.txt` com `Files` e `Path` (`java.nio.file`). |
| ⚡ **Fail Fast Principle** | Interrompe a execução assim que um erro é detectado. |
| 💡 **KISS Principle** | "Keep It Simple, Stupid" — manter o código simples e claro. |

---


---

## 🔍 Descrição das Classes

### 🧩 `CodigoAcesso` (Interface)
Define os métodos obrigatórios:  
`getCodigo()`, `isUsado()`, `usar()`, `isValido()`.

### 👤 `CodigoVisitante`
- Formato: `VIS-AAA9999-X`
- Dígito verificador = soma dos números `%10`
- Marca código como usado e valida formato via regex

### 🧰 `CodigoTerceirizado`
- Formato: `TER-999-AAA-Y`
- Dígito verificador = soma ASCII das letras `%10`
- Estrutura semelhante à classe de visitante

### 🧠 `GerenciadorDeCodigos`
- Gera e armazena códigos de ambos os tipos  
- Salva e carrega arquivos `.txt` em UTF-8  
- Implementa o conceito **Fail Fast** para evitar duplicatas  
- Usa `ArrayList` e `Random` para geração dinâmica

### ▶️ `program` (Classe Principal)
Fluxo principal:
1. Gera códigos de visitantes e terceirizados  
2. Salva no arquivo `codigos.txt`  
3. Carrega novamente os códigos  
4. Marca códigos como usados e exibe resultados no console

---

## 💻 Exemplo de Execução

**Saída real no console:**

Códigos salvos com sucesso!
Códigos carregados com sucesso!
Primeiro código encontrado: VIS-EWH4334-4
Segundo código (não usado): VIS-NET0340-7


---

## 🧪 Resultados e Aprendizados

✔️ Geração de códigos **únicos e válidos**  
✔️ Arquivos salvos e lidos corretamente em **UTF-8**  
✔️ Regex e dígito verificador garantem integridade  
✔️ Aplicação de **POO real** com **interface + implementação**  
✔️ Uso prático de **KISS** e **Fail Fast**

Durante o desenvolvimento, aprendi a:
- Gerar letras aleatórias usando valores **ASCII**  
- Validar entradas e formatos com **Regex**  
- Tratar exceções para evitar travamentos  
- Escrever código limpo, modular e de fácil manutenção

---

## 🚀 Melhorias Futuras

- 🧾 Adicionar logs de execução  
- 🧩 Criar uma interface gráfica (JavaFX ou Swing)  
- 🧪 Implementar testes automatizados (JUnit)  
- 🗃️ Salvar dados em banco de dados (ex: SQLite ou MySQL)

---

## 🧾 Licença

Este projeto foi desenvolvido para fins **acadêmicos**  
como parte da disciplina **Programação Orientada a Objetos**.  
Sinta-se à vontade para utilizar como **referência ou estudo**. ✨

---

> _"Fail fast, learn faster."_  
> — **Luís de Jesus Fernandes**

## 🏗️ Estrutura do Projeto

