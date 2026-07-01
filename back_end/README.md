# Bookstore back_end

## Banco de dados com Docker

Este projeto usa MySQL em `localhost:3307`, com o banco `bookstore_db`.

1. Crie ou ajuste o arquivo `.env` na raiz do projeto:

```env
DATABASE_PASSWORD=sua_senha
DATABASE_NAME=bookstore_db
DATABASE_USERNAME=root
DATABASE_URL=jdbc:mysql://localhost:3307/bookstore_db
```

Caso ocorra um erro , adicione DATABASE_PASSWORD=sua_senha em BackEndApplication > Edit Configurations > Environment variables (se não tiver vá em modify options no edit configurations e adcione o Environment variables)
2. Suba o banco:

```bash
docker compose up -d mysql
```

3. Confira se o container esta rodando:

```bash
docker compose ps
```

4. Rode a aplicacao Spring normalmente

Para parar o banco sem apagar os dados:

```bash
docker compose stop mysql
```

Para apagar o banco e o volume de dados:

```bash
docker compose down -v
```