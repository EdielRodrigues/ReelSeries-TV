# Reel Séries: sincronização Firebase por usuário

Código derivado do ZIP ReelSeries_Continuar_Assistindo.zip.

## Estado compartilhado
- Favoritos (reel_favorites)
- Continuar assistindo (reel_continue_history)
- Posição de reprodução (reel_playback_progress)
- Episódios assistidos (reel_watched_episodes)

Cada chave é um documento em usuarios/{uid}/estado/{store}/itens/{sha256-chave}, com cache local SharedPreferences.
Requer login na mesma conta em ambos os aparelhos. A sincronização usa Firestore e seu cache offline.

## IMPORTANTE
1. Publicar firestore.rules no projeto Firebase antes de testar. O ZIP não publica regras automaticamente.
2. Compilar APK via GitHub Actions. Não foi executada compilação neste ambiente.
3. Para receber atualização de tela imediatamente, telas precisam observar o broadcast CLOUD_STATE_CHANGED; sem isso, reabrir a tela.
4. A migração inicial só cria documentos inexistentes; em contas com dados divergentes, verificar a consistência antes do uso em produção.
5. O catálogo e player foram preservados. Sem login, dados ficam apenas locais.
6. Este ZIP atualiza a base Android compartilhada; adaptação final do controle remoto e versões Tizen/webOS permanecem separadas.
7. Atualização de estado pode ter conflitos em dois aparelhos simultâneos; prevalece a última escrita do documento.
