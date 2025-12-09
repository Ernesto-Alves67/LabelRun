# LabelRun

Aplicativo Android para gestão e corrida com etiquetas, desenvolvido com Kotlin e Jetpack Compose.

## 🚀 Tecnologias

- **Kotlin** - Linguagem principal
- **Jetpack Compose** - UI moderna e declarativa
- **Retrofit** - Cliente HTTP para consumo de API REST
- **Socket.IO** - Comunicação em tempo real via WebSocket
- **Coroutines** - Programação assíncrona
- **ViewModel & StateFlow** - Gerenciamento de estado
- **Navigation Compose** - Navegação entre telas

## 📋 Pré-requisitos

- Android Studio Arctic Fox ou superior
- JDK 11
- Gradle 8.13
- Emulador Android ou dispositivo físico (API 24+)
- Servidor API rodando (veja [API_SETUP.md](API_SETUP.md))

## 🔧 Configuração

### 1. Clone o repositório
```bash
git clone https://github.com/Ernesto-Alves67/LabelRun.git
cd LabelRun
```

### 2. Configure a URL da API
Copie o arquivo de exemplo e configure a URL do servidor:

```bash
cp local.properties.example local.properties
```

Edite `local.properties` e configure a URL:

```properties
# Para emulador Android
API_BASE_URL=http://10.0.2.2:8080/

# Para dispositivo físico (substitua pelo seu IP local)
API_BASE_URL=http://192.168.1.XXX:8080/
```

### 3. Sincronize as dependências
Abra o projeto no Android Studio e aguarde a sincronização do Gradle.

### 4. Execute o projeto
1. Inicie um emulador ou conecte um dispositivo físico
2. Clique em "Run" no Android Studio
3. O app será instalado e executado

## 📱 Funcionalidades

### ✅ Implementadas
- Autenticação (Login/Registro)
- Navegação entre telas
- Integração com API REST
- Comunicação via WebSocket
- Gerenciamento de estado com ViewModel
- Tratamento de erros de rede
- Logging de requisições HTTP

### 🚧 Em Desenvolvimento
- Tela de registro completa
- Recuperação de senha
- Armazenamento seguro de token
- Perfil de usuário
- Dashboard principal

## 🏗️ Arquitetura

O projeto segue uma arquitetura MVVM (Model-View-ViewModel):

```
app/
├── core/
│   └── network/          # Configuração de rede (Retrofit, Socket.IO)
├── data/
│   ├── model/            # Modelos de dados (DTOs)
│   └── repository/       # Repositórios (camada de dados)
├── ui/
│   ├── components/       # Componentes reutilizáveis
│   ├── screens/          # Telas da aplicação
│   ├── theme/            # Tema e estilos
│   └── viewmodel/        # ViewModels (lógica de UI)
└── navigation/           # Configuração de navegação
```

## 🔐 Segurança

- ✅ Permissões de internet configuradas
- ✅ Validação de dados de entrada
- ✅ Tratamento de erros de rede
- ✅ Logging apenas em modo DEBUG
- ⚠️ **TODO**: Implementar armazenamento seguro de token (EncryptedSharedPreferences)
- ⚠️ **TODO**: Adicionar PIN/Biometria para segurança adicional
- ⚠️ **TODO**: Configurar HTTPS em produção

## 📖 Documentação

- [API_SETUP.md](API_SETUP.md) - Guia completo de configuração da API
- [Configuração de rede](app/src/main/java/com/scherzolambda/labelrun/core/network/) - Código de configuração

## 🐛 Troubleshooting

### Erro: "Unable to resolve host"
- Verifique se a URL no `local.properties` está correta
- Certifique-se de que o servidor está rodando
- Para emulador, use `10.0.2.2` ao invés de `localhost`

### Erro: "Connection refused"
- Verifique se o servidor está acessível na porta configurada
- Verifique configurações de firewall
- Em dispositivo físico, use o IP local da máquina

### Build falha
- Certifique-se de ter o JDK 11 instalado
- Execute `./gradlew clean build --refresh-dependencies`
- Verifique a conexão com internet para baixar dependências

## 📝 Logs

Para visualizar os logs da aplicação:

```bash
# Ver todos os logs
adb logcat

# Ver apenas logs de rede
adb logcat -s OkHttp AuthRepository SocketManager

# Ver logs em tempo real com filtro
adb logcat | grep -E "AuthRepository|ApiClient|SocketManager"
```

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## ✨ Autores

- **Ernesto Alves** - [Ernesto-Alves67](https://github.com/Ernesto-Alves67)

## 🙏 Agradecimentos

- Comunidade Android
- Equipe do Jetpack Compose
- Contribuidores open-source
