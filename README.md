<b>Grupo:</b> Dennys Barros, Ítalo Egypto, Pedro Pires, Rodrigo Cunha

<b>Produto:</b> Youtube

<b>Execução:</b> Execute a classe CucumberRunner. O browser a ser utilizado deve ser o Google Chrome (o driver já está no projeto). Caso Windows seja usado, descomente as linhas 17 e 18 da classe DriverFactory, e comente as linhas 14 e 15.

<b>Classes:</b>

  <b>Util</b>
  
  util.BasePage - Contém métodos básicos que auxiliam no desenvolvimento de todo o projeto, tais como waitAndClick, mouseOver, etc. <br>
  util.Constants - Contém as constantes usadas no projeto<br>
  util.DriverFactory - Contém métodos relacionados ao Driver, tais como iniciar o driver, fechar o driver e iniciar o wait.<br>
  
  <b>Features:</b>

  Channel.features - Testes de channels escritos em gherkin<br>
  Videos.features - Testes de videos escritos em gherkin<br>
    
  <b>Steps (definem as frases escritas nas Features)</b>
  
  steps.BasicInteractions - Contém os métodos gerais do Youtube, tais como abrir o youtube, logar, procurar por um vídeo.<br>
  steps.ChannelListInteractions - Contém os métodos e assertions da lista de canais do Youtube<br>
  steps.Hooks - Contém os métodos pra serem rodados antes ou depois das features @Before e @After<br>
  steps.Recommendations - Contém os métodos relacionados às interações de recomendações de vídeo<br>
  steps.VideoInteractions - Contém os métodos relacionados às interações de vídeo, tais como curtir um vídeo, criar uma playlist, etc<br>
  
  <b>Runner</b>
  
  runner.CucumberRunner - Contém as definições do que deve ser rodado e onde estão definidos os steps, features, tags, etc<br>
  
  <b>Pages</b>

  pages.GoogleAccountPage - Contém o mapping dos elementos de login do Google e métodos relacionados<br>
  pages.YoutubeChannelsListPage - Contém o mapping dos elementos da pagina de canais e métodos relacionados<br>
  pages.YoutubeHistoryListPage - Contém o mapping dos elementos da pagina de historico de videos e métodos relacionados<br>
  pages.YoutubeLikedVideosListPage - Contém o mapping dos elementos da pagina de videos curtidos e métodos relacionados<br>
  pages.YoutubeMainPage - Contém o mapping dos elementos da pagina inicial do Youtube e métodos relacionados<br>
  pages.YoutubePlaylistPage - Contém o mapping dos elementos da pagina de playlists e métodos relacionados<br>
  pages.YoutubeVideoPage - Contém o mapping dos elementos da pagina de video e métodos relacionados <br>
  pages.YoutubeVideoSearchPage - Contém o mapping dos elementos da pagina de pesquisa de videos e métodos relacionados <br>
  pages.YoutubeWatchLaterListPage - Contém o mapping dos elementos da pagina de "Watch Later" e métodos relacionados <br>
 
