# Progetto.oop

La nostra applicazione è Web Service, un sistema software che risponde alle chiamate di un Client (applicazione, sito web, Postman) comunicando tramite il protocollo HTTP, che permette all'client di ottenere informazioni su un data-set di tweet. Attraverso Spring Boot, è stato possibile creare questo software che lancia l'intera applicazione web, compreso il web server integrato.

Il Data-set rappresenta l'elenco dei tweet riguardanti un argomento dato (di default "terremoto") scritti in italiano, di questi tweet viene preso in considerazione:
- per l'autore id, nome, nome rapresentativo e la quantità di follower;
- per il tweet, testo, data di creazione, e coordinate corrispondenti al luogo dove è stato creato.

La nostra applicazione permette di richiedere mediante API REST (GET) con rotte distinte:

- Restituzione dei metadati, formato JSON, ovvero l’elenco degli attributi, alias degli stessi e tipo di dati contenuti.
- Restituzione dei dati riguardanti ogni tweet, formato JSON.
- Restituzione dei capoluoghi di regione,formato JSON
- Restituzione del numero di tweet di ogni capoluogo di regione.
- Restituzione dei dati riguardanti tweet filtrati, formato JSON.
- Restituzione delle statistiche sui dati di uno specifico campo.
- Restituzione delle statistiche sui dati di uno specifico campo, su tweet filtrati.
- Cambiare l'argomento e la quantità di tweet su cui si lavora.






<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Use%20Case%20Diagram.jpg" width="600"/>






### Eseguire richieste

Per eseguire le richieste GET si può installare un API testing, (ad esempio: Postman). 
La seguente tabella mostra le richieste possibili. 



|    TIPO        |rotta                          |descrizione                                              |
|----------------|-------------------------------|---------------------------------------------------------|
|GET             |/getAllTweet                   |restituisce i tweet con i relativi dati                  |
|GET             |/getAllCity                    |restituisce le città con le relative coordinate          |
|GET             |/getAllMetadata                |restituisce gli alias utilizzati                         |
|GET             |/data                          |restituisce i tweet con i relativi dati                  |
|GET             |/data?filter="filtro"          |restituisce i tweet con i relativi dati filtrati secondo il filtro passato|
|GET             |/Stat?field="nome"             |restituisce una statistica sul "nome" del dato o di un capoluogo di regione            specificato fatta sull'intero dataset     |
|GET             |/Stat?field="nome"&filter="filtro"  |restituisce una statistica sul "nome" del dato o di un capoluogo di regione            specificato fatta sul data-set filtrato (i filtri sulle città richiedono di passare anche la distanza "dist") espressa in Km|
|GET             |/getTweetForCity?distanza= "distanza in Km"          |restituisce le città con il numero di tweet entro la    distanza |
|GET            |/setAllTweet?qt=quantità&arg="argomento"   |modifica i tweet nel data-set






I filtri vanno composti seguendo una delle forme nella seguente tabella

| filtro	 | Descrizione                                |Esempio                                     |
|----------------|--------------------------------------------|--------------------------------------------|
|{"dato":{"operatore":valore}} |applica l'operatore sul dato        |{"followers":{"$gt":1000}}            	   |
|{"dato":{"descrittore":valore}} |applica il descrittore sul dato        |{"followers":{"$not":1000}}            	   |
|{"$and":[test1,test2,...] |applica tutti i filtri insieme      |{"$and":[{"followers":{"$gt":1000}}, {"followers":{"$not":1000}}]} |
|{"$or":[test1,test2,...] |applica tutti i filtri ma basta uno solo per ammettere il dato|{"$or":[{"followers":{"$gt":1000}}, {"followers":{"$not":1000}}]} |

La tabella seguente illustra gli operatori disponibili

| Nome operatore | Descrizione                                |Esempio                                     |
|----------------|--------------------------------------------|--------------------------------------------|
|$gt             |maggiore (valido per campi numerici)        |{"followers":{"$gt":1000}}            	   |
|$gte            |maggiore o uguale (valido per campi numerici)        |{"followers":{"$gte":1000}}            	   |
|$lt             |minore (valido per campi numerici)          |{"followers":{"$lt":1000}}                  |
|$lte            |minore o uguale (valido per campi numerici)          |{"followers":{"$lt":1000}}                  |
|$bt		 |compreso tra  (valido per campi numerici)   |{"lat":{"$bt":[100,5000]}}      |     
|$cc		 |cerchio intorno a una città (valido solo per geo) |{"geo":{"$cc":{"city":"Ancona","range":100}}} |
|$cp             |cerchio intorno a un punto* (valido solo per geo)|	{"geo":{"$cp":{"lat":45.735,"lon":7.35,"range":100}}}|
|Nin             |non trova una corrispondeza con i valori dell'array (valido per stringhe)|	{"ProvDest":{"Nin":["Roma","Viterbo"]}}|

*L'italia ha latitudine compresa tra 36.7 e 46.7, longitudine compresa tra 6.8 e 15.5.

La tabella seguente illustra i descrittori disponibili
| Nome operatore | Descrizione                                |Esempio                                     |
|----------------|--------------------------------------------|--------------------------------------------|
|$not            |diverso (valido per campi numerici)        |{"followers":{"$not":1000}}            	   |
|$in		 |uguale ad almeno uno dei valori  (valido per campi numerici)   |{"lat":{"$in":[100,5000]}}      | 
|$nin		 |diverso da tutti i valori  (valido per campi numerici)   |{"lat":{"$nin":[100,5000]}}      | 



## Sviluppo

### Package
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Package.jpg" width="800"/>

### Classi
* **Package it.univpm.twitterProject.controller**
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Controller%20Class%20Diagram.jpg" width="400"/>

* **Package it.univpm.twitterProject.database**
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Database%20Class%20Diagram.jpg" width="400"/>

* **Package it.univpm.twitterProject.model**
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Model%20Class%20Diagram.jpg" width="1000"/>

* **Package it.univpm.twitterProject.service**
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Service%20Class%20Diagram.jpg" width="1100"/>

* **Package it.univpm.twitterProject.exception**
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Exception%20Class%20Diagram.jpg" width="1000"/>

* **Package it.univpm.twitterProject.utils.filter**
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Filter%20Class%20Diagram.jpg" width="1000"/>

* **Package it.univpm.twitterProject.utils.stats**
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/Stats%20Class%20Diagram.jpg" width="1000"/>


### Chiamate

* **Chiamata GET /getAllTweet**
Il controller esegue una chiamata tramite il metodo `getAllTweet`, il quale restituisce il Json contenente i tweet. ControllerClass lo ritorna al client.
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/_getAllTweet2.jpg" width="1000"/>

* **Chiamata GET /getAllCity**
Il controller esegue una chiamata tramite il metodo `getAllCity`, il quale restituisce il Json contenente le città. ControllerClass lo ritorna al client.
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/_getAllCityJo2.jpg" width="1000"/>

* **Chiamata GET /getAllMetadata**
Il controller esegue una chiamata tramite il metodo `getAllMetadata`, il quale restituisce il Json contenente i metadata. ControllerClass lo ritorna al client. 
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/_getAllMetadata2.jpg" width="1000"/>

* **Chiamata GET /data**
Il controller crea un filtro tramite `GenericFilterTweet` che poi sarà usato da `Filtring` per filtrare l'array di tweet.
Tramite `GetFilteredTweet` il controller otterà l'array filtrato che poi sara fatto tornare al client.
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/_getData2.jpg" width="1000"/>

* **Chiamata GET /Stat?field="nome"**
Il controller crea un filtro tramite `GenericFilterTweet` che poi sarà usato da `Filtring` per filtrare l'array di tweet.
Tramite `GetFilteredTweet` il controller otterà l'array filtrato che poi sara passato a al costruttore di `Stat`.
Tramite `GetStatJo` il controller ottiene le statistiche come Json che poi verrano ritornate al client.
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/_getStat.jpg" width="1000"/>

* **Chiamata GET /getTweetForCity?distanza= "distanza in Km"**
Il controller genera la statistica `TweetForCity` che poi verrà applicata tramite `AppStat` ritornandola come json che verra restituita al client.
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/_getTweetForCity2.jpg" width="1000"/>

* **Chiamata GET /setAllTweet?qt=quantità&arg="argomento"**
Il controller chiama `setAlltTweet` per andare a modificare l'array di tweet.
<img src="https://github.com/LorenzoMacchione/Progetto.oop/blob/master/uml/_setAlltweet2.jpg" width="1000"/>

## Software utilizzati

* [Eclipse](https://www.eclipse.org/) - ambiente di sviluppo integrato
* [UMLDesigner] (http://www.umldesigner.org/) - ambiente di creazione uml
* [Spring Boot](https://spring.io/projects/spring-boot) - framework per  sviluppo applicazioni Java
* [Maven](https://maven.apache.org/) - strumento di gestione di progetti

## Autori

* **Lorenzo Macchione** - [GitHub](https://github.com/LorenzoMacchione)
* **Donato Mariano** - [GitHub](https://github.com/Doonato)
