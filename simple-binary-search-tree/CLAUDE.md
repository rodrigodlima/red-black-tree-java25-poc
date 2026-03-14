# CLAUDE.md — Simple Binary Search Tree (BST)

## Contexto do projeto

Este projeto é uma **jornada de aprendizado progressivo** com o objetivo final de implementar uma **Red-Black Tree em Java**. O caminho segue esta ordem:

```
Binary Search (algoritmo em array) ← apenas conceito, não implementado aqui
    ↓
Binary Search Tree — BST (estrutura de dados) ← ESTAMOS AQUI
    ↓
Red-Black Tree (BST autobalanceada) ← objetivo final
```

## Estilo de ensino (IMPORTANTE)

O usuário está aprendendo de forma progressiva e didática. Ao guiar o desenvolvimento:

- **Não mostrar código pronto** — apenas dar direcionamentos e dicas
- Apontar erros explicando o conceito por trás, não corrigindo diretamente
- Fazer perguntas para guiar o raciocínio antes de dar a resposta
- Validar o progresso antes de avançar para o próximo passo
- Usar analogias simples quando o conceito for abstrato

## Estado atual do código

### Classes implementadas

**`Node`** — representa um único nó da árvore
- `int value` — o valor do nó
- `Node leftChild` — referência para o filho esquerdo
- `Node rightChild` — referência para o filho direito
- Construtor atribuindo os três atributos

**`BinarySearchTree`** — representa a árvore inteira
- `Node root` — referência para o nó raiz
- Construtor recebendo a raiz

**`Main`** — ponto de entrada
- Cria a árvore com `new BinarySearchTree(null)` (árvore vazia)
- Chama `tree.insert(10)` — método ainda não implementado corretamente

### Próximo passo: implementar `insert(int value)`

O método `insert` deve ficar em `BinarySearchTree` e seguir esta lógica:

1. **Caso especial:** se a raiz for `null`, o novo nó vira a raiz
2. **Caso recursivo:** a partir da raiz, navegar pela árvore:
   - Valor menor que o nó atual → vai para a esquerda
   - Valor maior que o nó atual → vai para a direita
   - Posição vazia (null) → insere ali

O método auxiliar privado tem a assinatura:
```
private Node insert(Node atual, int valor)
```
Ele retorna um `Node` porque reconstrói a referência correta a cada chamada recursiva.

**Problema atual no código:** o `insert` está com lógica incorreta — `int` é primitivo e nunca é null, então `Objects.isNull(value)` não faz sentido. A checagem correta é sobre a raiz (`root`), não sobre o valor.

## Roteiro completo de aprendizado

| Etapa | Tema | Status |
|-------|------|--------|
| 1 | Modelar `Node` e `BinarySearchTree` | Concluído |
| 2 | Implementar `insert()` | Em andamento |
| 3 | Implementar `leftRotate()` e `rightRotate()` | Pendente |
| 4 | Implementar `fixInsert()` para Red-Black Tree | Pendente |
| 5 | Implementar `delete()` | Pendente |

## Conceitos já compreendidos pelo usuário

- Diferença entre **Binary Search** (algoritmo em array) e **BST** (estrutura de dados em árvore)
- Auto-referência em Java: um `Node` pode ter atributos do tipo `Node`
- Separação de responsabilidades: `Node` guarda dados, `BinarySearchTree` guarda a lógica, `Main` apenas orquestra
- Diferença entre **classe** e **método**
- Por que Red-Black Tree existe: resolver o desbalanceamento da BST pura

## Recursos recomendados

- **Visualizador interativo:** https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
- **Vídeo didático:** Abdul Bari no YouTube — "Red Black Tree"
- **Referência formal:** CLRS — Capítulo 13
- **Implementação real:** source do `java.util.TreeMap`
