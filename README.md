# Restaurant Guide (MVVM + Coroutines Flow)

## Versão 1.0.0

Aqui na FreteBras sempre estamos buscando conhecimento para evoluir nossas tecnologias, bem como nossa stack de trabalho. Neste contexto, o intuito da tech talk foi mostrar como podemos evoluir do RxJava/Android para Kotlin Coroutines Flow, focando nas chamadas assíncronas realizadas pelo repository (Utilizado principalmente para acesso a nossa API e ao banco de dados local).

Não precisamos falar como a linguagem de programação Kotlin influenciou todo o ecossistema do Android, então, já vamos deixar um agradecimento especial para a JetBrains. Cada vez mais estão surgindo bibliotecas desenvolvidas com Kotlin, que provam que podem performar melhor que bibliotecas desenvolvidas em Java, e, o Kotlin Coroutines Flow, não fica fora desta lista.

### Apresentação 

#### Migrando uma arquitetura MVVM com RxJava/RxAndroid para MVVM com Kotlin Coroutines Flow

[Slides](https://docs.google.com/presentation/d/1P7-65aDQiSj4226SN1SVDK0CbC0_gYI8o-nTDiDMq1M/edit?usp=sharing)

[Talk](https://www.youtube.com/watch?v=lxsaKiOdQC0&t=1s)

### Tem algo a mais no projeto do GitHub?

- Aplicação modularizada;
- Dagger com Activity Builder;
- Jetpack Navigation;
- Unit Tests.


## Versão 2.0.0

 - Atualizar bibliotecas e arquivos gradle
 - Remover kotlin synthetic
 - Change AppCoroutines to livecycleScope, viewModelScope
