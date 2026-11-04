# Reel Séries TV Teste V3

## Implementado
- Alternância de modo TV no Perfil (paisagem, grade 5 colunas e seleção pelo controle remoto).
- Mesmo player Media3/ExoPlayer, catálogo, Firebase Auth e configurações originais.
- Favoritos, episódios assistidos, histórico e posição no Firestore por UID.
- Histórico enviado ao Firestore quando a reprodução é registrada.
- Limpeza do cache de dados pessoais ao trocar ou sair da conta para evitar mistura entre usuários.
- Mantém APK de teste com applicationId separado do aplicativo original.

## Limitações importantes
- Não foi possível compilar nem testar em aparelho real nesta sessão.
- O modo TV é uma adaptação inicial: telas internas podem precisar de foco/DPAD adicionais.
- A sincronização exige regras do Firestore publicadas e login na mesma conta.
- Esta versão usa a nuvem como fonte principal dos dados: favoritos/histórico locais antigos não são migrados automaticamente. Isso evita misturar dados de contas diferentes.
- A leitura de dados remotos ocorre de modo assíncrono. Se entrar numa tela antes de sincronizar, volte e abra novamente.
- Compatível com testes no celular; o APK Android TV definitivo exigirá manifesto Leanback e testes em emulador/TV.

## Como compilar
Enviar conteúdo do ZIP à raiz do repositório, preservando `.github/workflows/android-apk.yml`.
GitHub Actions > Gerar APK Android > Run workflow.

V3.1: removida mudanca de orientacao em onCreate para evitar reinicializacao ao ativar modo TV. A orientacao deve ser alterada pelo Android para testar em paisagem.
