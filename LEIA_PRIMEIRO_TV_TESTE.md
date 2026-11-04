# Reel Séries TV Teste — APK separado para celular Android

## Objetivo
Instalar o app de teste lado a lado com o Reel Séries original para validar catálogo, player, favoritos e Continuar Assistindo antes da adaptação definitiva para TV.

- Pacote original: `com.reelseries.android`
- Pacote de teste: `com.reelseries.android.tvteste`
- Namespace Java mantido: `com.reelseries.android`
- Versão original preservada: 18.1 / 63
- Firebase original: `smart-tv-c175a`

## Como compilar
1. Crie um repositório GitHub separado (por exemplo `ReelSeries-TV-Teste`).
2. Extraia o ZIP e envie **o conteúdo dentro de ReelSeries-Android-main** para a raiz do repositório, incluindo `.github/workflows/android-apk.yml`.
3. Em Actions, execute `Gerar APK Android` pelo `Run workflow`.
4. Baixe o artifact com o APK de debug e instale no Android. Não substitui o app original.

## Firebase: atenção antes do teste de login
O `google-services.json` inclui uma entrada de teste duplicada apenas para permitir a compilação usando o projeto Firebase existente. Para configuração correta e estável, registre um **novo aplicativo Android** no mesmo projeto Firebase com o identificador `com.reelseries.android.tvteste`, baixe o novo `google-services.json` e substitua o arquivo de exemplo. Se o Firebase Auth exigir configuração de SHA-1/SHA-256 ou OAuth, cadastre a assinatura de debug usada no build. O APK debug do GitHub pode ter assinatura diferente do APK original.

## Sincronização
O projeto contém `CloudUserState.java` e `firestore.rules` da versão anterior. Publique as regras somente após revisá-las; não substitua regras existentes sem mesclar. A sincronização ainda precisa de testes e correções, sobretudo a propagação de mudanças nas telas, a consistência da migração e o histórico. Não considere o compartilhamento celular-TV pronto para produção.

## Limites
Este APK de teste é **para celular Android**; não valida navegação por controle remoto de TV. O player Media3/ExoPlayer foi mantido. O mesmo Firebase e catálogo são usados, não um banco novo. Não foi compilado nesta sessão; erros de build e runtime podem exigir correção.
