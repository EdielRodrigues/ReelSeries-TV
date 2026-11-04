# Correção do Reel Séries TV Teste
O arquivo `app/build.gradle.kts` estava incorretamente em `app/src/build.gradle.kts`.
O `google-services.json` também estava em `app/src/` e foi movido para `app/google-services.json`.
O workflow agora valida o applicationId contra os clientes Firebase antes da compilação.

## Publicar
Envie o conteúdo desta pasta diretamente à raiz do repositório no GitHub.
Não coloque outra pasta `ReelSeries-TV-Corrigido` envolvendo os arquivos.
Exclua no GitHub os antigos `app/src/build.gradle.kts` e `app/src/google-services.json`.
Acesse Actions > Gerar APK Android > Run workflow.

## Estado
Projeto não compilado nesta sessão (SDK/Gradle indisponíveis).
A versão TV Teste usa `com.reelseries.android.tvteste`, separado do original.
O `google-services.json` fornecido inclui esse identificador.
A sincronização Firebase ainda precisa de teste real e publicação das regras Firestore.
