# Infoglobo
Desafio de Engenharia Backend Java Infoglobo

# Solução

Desafio de Engenharia Backend Java (https://github.com/Infoglobo/desafio-back-end).

Criar um crawler que leia [este feed] (http://revistaautoesporte.globo.com/rss/ultimas/feed.xml) e retorne um json estruturado.

A Solução java com 2 projetos, que seguem:

## Webservice RgCrawlerWS

Webservice crawler. Lê um feed (online ou local) e retorna um json.

Necessita que o usuário seja autenticado.

O webservice tem 2 métodos:

* String getFeedJson(String strUrl) (objevivo do desafio)

Entrada: endereço web onde se encontra o feed.

Saída: StringJson estruturada conforme instruções.

* String getFeedJsonLocal(String strFilePath) (para fins de testes)

Entrada: endereço local onde se encontra o arquivo xml do feed.

Saída: String Json estruturada conforme instruções.

## Cliente RgCrawlerClient

Cliente para chamada dos métodos do webservice RgCrawlerWS.


# Execução

* Primeiro, execute o webservice RgCrawlerWS.
* Após a cerificar-se que o websevice está em execução, execute o cliente RgCrawlerClient.


# Testes

O procedimento de teste (JUnit) é executado no projeto RgCrawlerClient.

É necessário executar o método getFeedJsonLocal (defina o método a ser execuado na classe CrawlerClient.java, conforme comentário na classe).

O teste gera o json do arquivo local (..\test\input_test.xml) e compara com o arquivo de saída (..\test\output_test.json).

Por conveniência, os arquivos input_test.xml e output_test.json contém, respectivamente, o exemplo de entrada e saída descritos em https://github.com/Infoglobo/desafio-back-end
