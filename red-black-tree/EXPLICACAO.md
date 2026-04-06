# Red-Black Tree — Explicação Passo a Passo

## O que é?

Uma Red-Black Tree é uma BST (Binary Search Tree) **autobalanceada**. Ela garante que a árvore nunca fique desbalanceada, mantendo a busca sempre em O(log n).

Para isso, cada nó recebe uma **cor** (vermelho ou preto) e a árvore segue 5 regras rígidas.

---

## As 5 Regras

1. Todo nó é **vermelho** ou **preto**
2. A **raiz** é sempre **preta**
3. Todo nó `null` (posição vazia) é considerado **preto**
4. Um nó **vermelho** nunca pode ter filho **vermelho**
5. Todo caminho da raiz até uma folha tem o mesmo número de nós **pretos**

---

## Inserindo números — passo a passo

### Inserindo `10`

```
10 (preto)
```

> O primeiro número inserido vira a raiz.
> Pela **regra 2**, a raiz é sempre preta — então o `10` é pintado de preto.

---

### Inserindo `5`

```
    10 (preto)
   /
  5 (vermelho)
```

> `5 < 10` → vai para a **esquerda**.
> Todo nó novo é inserido como **vermelho** (para não quebrar a regra 5).
> O pai (`10`) é preto, então nenhuma regra foi violada. Nada a corrigir.

---

### Inserindo `15`

```
    10 (preto)
   /         \
  5 (verm.)  15 (verm.)
```

> `15 > 10` → vai para a **direita**.
> Inserido como vermelho. Pai é preto. Sem violação.

---

### Inserindo `3`

```
    10 (preto)
   /         \
  5 (verm.)  15 (verm.)
 /
3 (verm.)
```

> `3 < 10` → esquerda. `3 < 5` → esquerda do `5`.
> Inserido como vermelho — mas agora **pai (`5`) e filho (`3`) são vermelhos**!
> **Regra 4 violada** → `fixInsert` é chamado.

**fixInsert — Caso 1: tio vermelho**

O tio do `3` é o `15` (irmão do pai `5`) — e ele é **vermelho**.

Correção:
- Pinta o **pai** (`5`) de preto
- Pinta o **tio** (`15`) de preto
- Pinta o **avô** (`10`) de vermelho
- Sobe para o avô e verifica novamente

```
    10 (verm.)
   /          \
  5 (preto)  15 (preto)
 /
3 (verm.)
```

> O `10` virou vermelho, mas ele é a raiz — pela **regra 2**, a raiz volta a ser preta.

```
    10 (preto)
   /          \
  5 (preto)  15 (preto)
 /
3 (verm.)
```

Árvore balanceada. ✓

---

### Inserindo `2`

```
    10 (preto)
   /          \
  5 (preto)  15 (preto)
 /
3 (verm.)
/
2 (verm.)
```

> `2 < 10` → esquerda. `2 < 5` → esquerda. `2 < 3` → esquerda do `3`.
> Pai (`3`) e filho (`2`) são vermelhos → **regra 4 violada**.

**fixInsert — Caso 2: tio preto**

O tio do `2` é `null` (posição vazia) — considerado **preto**.

Correção:
- `2` é filho esquerdo e pai (`3`) é filho esquerdo → faz **rightRotate** no avô (`5`)
- Recolore: pai (`3`) vira preto, avô (`5`) vira vermelho

```
    10 (preto)
   /          \
  3 (preto)  15 (preto)
 /    \
2     5
(verm.) (verm.)
```

Árvore balanceada. ✓

---

## Por que inserir sempre como vermelho?

Se inseríssemos como **preto**, adicionaríamos um preto em apenas **um** caminho da árvore — quebrando automaticamente a regra 5 em todos os outros.

Inserindo como **vermelho**, a regra 5 é preservada. A única regra que pode ser violada é a 4 (dois vermelhos seguidos) — e essa é mais fácil de corrigir.

---

## Os dois casos do fixInsert

| Caso | Condição | Correção |
|------|----------|----------|
| **Caso 1** | Tio é **vermelho** | Recolore pai, tio e avô. Sobe para o avô. |
| **Caso 2** | Tio é **preto** | Rotaciona e recolore. |

---

## Rotações

As rotações **reorganizam os nós sem quebrar a ordem da BST**.

### Rotação à esquerda (leftRotate)

```
    10                15
   /  \              /  \
  5   15    →      10   20
     /  \         /  \
    12  20       5   12
```

O `15` sobe, o `10` desce para a esquerda.

### Rotação à direita (rightRotate)

```
      10              5
     /  \    →       / \
    5   15          3   10
   / \                  /  \
  3   7                7   15
```

O `5` sobe, o `10` desce para a direita.

---

## Resumo

```
insert(valor)
    └── insertBST()       → coloca o nó na posição correta (como vermelho)
    └── fixInsert()       → corrige violações das regras
            └── Caso 1 (tio vermelho) → recolore e sobe
            └── Caso 2 (tio preto)   → rotaciona e recolore
```
