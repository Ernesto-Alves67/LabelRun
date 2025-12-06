# Configuração da API - LabelRun

Este documento descreve como configurar a conexão com a API no aplicativo LabelRun.

## Pré-requisitos

1. Servidor da API rodando e acessível
2. Android Studio instalado
3. Emulador ou dispositivo físico configurado

## Configuração Rápida

### 1. Configure a URL da API

Copie o arquivo de exemplo e configure a URL:

```bash
cp local.properties.example local.properties
```

Edite o arquivo `local.properties` e configure a `API_BASE_URL`:

```properties
# Para emulador Android (localhost da máquina host)
API_BASE_URL=http://10.0.2.2:8080/

# Para dispositivo físico na mesma rede Wi-Fi
API_BASE_URL=http://192.168.1.XXX:8080/

# Para servidor de produção
API_BASE_URL=https://api.seudominio.com/
```

### 2. Descubra o IP local (para dispositivo físico)

**Windows:**
```cmd
ipconfig
```
Procure por "Endereço IPv4" na conexão ativa.

**Linux/Mac:**
```bash
ifconfig
# ou
ip addr show
```
Procure pelo endereço IP da interface de rede ativa (geralmente começa com 192.168.x.x).

### 3. Compile e Execute

Após configurar a URL, compile o projeto:

```bash
./gradlew build
```

Execute no dispositivo/emulador através do Android Studio.

## Endpoints Disponíveis

### Autenticação

#### Login
- **POST** `/api/auth/login`
- Body:
```json
{
  "email": "usuario@exemplo.com",
  "pass": "senha123"
}
```
- Resposta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### Registro
- **POST** `/api/auth/register`
- Body:
```json
{
  "email": "usuario@exemplo.com",
  "pass": "senha123",
  "name": "Nome do Usuário"
}
```
- Resposta:
```json
{
  "success": true,
  "message": "Usuário registrado com sucesso"
}
```

## Recursos Implementados

### ApiClient
- ✅ Configuração via BuildConfig
- ✅ Logging de requisições (apenas em DEBUG)
- ✅ Timeouts configurados (30s)
- ✅ Retry automático em falhas de conexão
- ✅ Conversor JSON (Gson)

### SocketManager
- ✅ Configuração via BuildConfig
- ✅ Reconnection automática (até 5 tentativas)
- ✅ Logging de eventos de conexão
- ✅ Tratamento de erros

## Troubleshooting

### Erro: "Unable to resolve host"
- **Causa**: URL da API não configurada ou inacessível
- **Solução**: Verifique se a `API_BASE_URL` está correta no `local.properties` e se o servidor está rodando

### Erro: "Connection refused"
- **Causa**: Servidor não está rodando ou firewall bloqueando
- **Solução**: 
  - Verifique se o servidor está ativo
  - Verifique configurações de firewall
  - Para dispositivo físico, certifique-se de estar na mesma rede Wi-Fi

### Erro: "Network Security Configuration"
- **Causa**: Android não permite HTTP em produção por padrão
- **Solução**: Use HTTPS em produção ou configure `network_security_config.xml` para desenvolvimento

### Socket não conecta
- **Causa**: URL do Socket.IO incorreta ou servidor não suporta WebSocket
- **Solução**: 
  - Verifique se o servidor Socket.IO está rodando na mesma porta
  - Verifique logs com `adb logcat -s SocketManager`

## Próximos Passos

1. Implementar armazenamento seguro do token (SharedPreferences criptografado)
2. Adicionar interceptor de autenticação para adicionar token automaticamente
3. Implementar refresh token
4. Adicionar health check endpoint
5. Implementar cache de requisições
6. Adicionar tratamento específico de erros HTTP (401, 403, 500, etc)

## Logs e Debugging

Para ver os logs de rede:

```bash
# Ver todos os logs
adb logcat

# Ver apenas logs de rede
adb logcat -s OkHttp SocketManager

# Ver logs do app
adb logcat -s LabelRun
```

## Estrutura de Arquivos

```
app/src/main/java/com/scherzolambda/labelrun/
├── core/
│   └── network/
│       ├── ApiClient.kt       # Cliente Retrofit configurado
│       ├── ApiService.kt      # Definição dos endpoints
│       └── SocketManager.kt   # Gerenciador de WebSocket
├── data/
│   └── model/
│       ├── LoginRequest.kt
│       ├── LoginResponse.kt
│       ├── RegisterRequest.kt
│       └── DefaultResponse.kt
└── ...
```

## Segurança

⚠️ **IMPORTANTE:**
- Nunca commite o arquivo `local.properties` (já está no .gitignore)
- Use HTTPS em produção
- Não armazene credenciais hardcoded no código
- Implemente armazenamento seguro de tokens
- Configure ProGuard/R8 para ofuscar código em release

## Suporte

Para problemas ou dúvidas, consulte a documentação da API ou entre em contato com a equipe de desenvolvimento.
