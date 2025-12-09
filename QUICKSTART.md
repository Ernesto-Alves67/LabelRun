# 🚀 Quick Start - LabelRun API Configuration

Este guia rápido te ajudará a configurar e conectar à API em **5 minutos**.

## ✅ Checklist Rápida

- [ ] Servidor API rodando
- [ ] Android Studio instalado
- [ ] Projeto clonado e aberto
- [ ] `local.properties` configurado
- [ ] App executado com sucesso

## 📝 Passo a Passo

### 1️⃣ Configure a URL da API (1 min)

Copie o arquivo de exemplo:
```bash
cp local.properties.example local.properties
```

Edite `local.properties` com sua URL:

**Usando Emulador Android?**
```properties
API_BASE_URL=http://10.0.2.2:8080/
```

**Usando Dispositivo Físico?**
```properties
API_BASE_URL=http://SEU_IP_LOCAL:8080/
```

> 💡 **Dica**: Para descobrir seu IP local:
> - Windows: `ipconfig`
> - Mac/Linux: `ifconfig` ou `ip addr show`

### 2️⃣ Verifique o Servidor (30 seg)

Certifique-se de que seu servidor está rodando e acessível:

```bash
# Para emulador (10.0.2.2 = localhost da máquina host)
curl http://10.0.2.2:8080/

# Para dispositivo físico
curl http://SEU_IP_LOCAL:8080/
```

### 3️⃣ Abra o Projeto no Android Studio (1 min)

1. Abra o Android Studio
2. File → Open → Selecione a pasta do projeto
3. Aguarde a sincronização do Gradle (pode levar alguns minutos na primeira vez)

### 4️⃣ Execute o App (2 min)

1. Inicie um emulador ou conecte um dispositivo físico
2. Clique no botão "Run" (▶️) ou pressione `Shift+F10`
3. Aguarde o build e instalação

### 5️⃣ Teste a Conexão (30 seg)

1. Abra o app no dispositivo
2. Tente fazer login com credenciais de teste
3. Observe o Logcat para verificar a conexão:

```bash
adb logcat -s AuthRepository OkHttp
```

## 🎯 Resultados Esperados

### ✅ Sucesso
```
D/AuthRepository: Login bem-sucedido para: usuario@exemplo.com
I/OkHttp: --> POST http://10.0.2.2:8080/api/auth/login
I/OkHttp: <-- 200 OK (123ms)
```

### ❌ Erro Comum
```
E/AuthRepository: Não foi possível conectar ao servidor
```

**Soluções:**
- ✅ Servidor está rodando?
- ✅ URL correta no `local.properties`?
- ✅ Firewall bloqueando a conexão?
- ✅ Dispositivo na mesma rede Wi-Fi?

## 🔍 Verificação de Conectividade

### Do Emulador para o Host
```bash
adb shell ping -c 3 10.0.2.2
```

### Do Dispositivo Físico
```bash
# Descubra o IP do dispositivo
adb shell ip addr show wlan0

# Tente pingar o servidor
adb shell ping -c 3 SEU_IP_SERVIDOR
```

## 📚 Arquivos Importantes

| Arquivo | Descrição |
|---------|-----------|
| `local.properties` | **Sua URL da API (não commitar!)** |
| `API_SETUP.md` | Guia detalhado de configuração |
| `README.md` | Documentação completa do projeto |
| `app/build.gradle.kts` | Configuração do BuildConfig |
| `ApiClient.kt` | Cliente Retrofit configurado |

## 🎨 Estrutura da API

O app já está configurado com os seguintes endpoints:

### POST /api/auth/login
```json
Request:
{
  "email": "usuario@exemplo.com",
  "pass": "senha123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### POST /api/auth/register
```json
Request:
{
  "username": "usuario123",
  "email": "usuario@exemplo.com",
  "pass": "senha123"
}

Response:
{
  "message": "Usuário registrado com sucesso"
}
```

## 🐛 Troubleshooting Rápido

| Erro | Solução |
|------|---------|
| "Unable to resolve host" | Verifique URL no `local.properties` |
| "Connection refused" | Verifique se servidor está rodando |
| "Network error" | Verifique conexão de internet |
| Build falha | Execute `./gradlew clean build` |

## 📞 Próximos Passos

Tudo funcionando? Explore mais:

1. 📖 Leia [API_SETUP.md](API_SETUP.md) para configuração avançada
2. 🔐 Configure armazenamento seguro de token
3. 🎨 Personalize as telas da aplicação
4. 🧪 Adicione testes unitários
5. 🚀 Prepare para produção

## 💬 Ajuda

Problemas? 
- Consulte [API_SETUP.md](API_SETUP.md) para troubleshooting detalhado
- Verifique os logs: `adb logcat -s AuthRepository`
- Abra uma issue no GitHub

---

**Configuração completa em menos de 5 minutos!** ⚡
