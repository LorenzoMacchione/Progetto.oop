# Progetto.oop

La nostra applicazione è Web Service, un sistema software che risponde alle chiamate di un Client (applicazione, sito web, Postman) comunicando tramite il protocollo HTTP, che permette all'client di ottenere informazioni su un data-set di tweet. Attraverso Spring Boot, è stato possibile creare questo software che lancia l'intera applicazione web, compreso il web server integrato.

Il Data-set rappresenta l'elenco dei tweet riguardanti un argomento dato (di default "terremoto") scritti in italiano, di questi tweet viene preso in considerazione:
- per l'autore id, mome, nome rapresentativo e la quantità di follower;
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




AGGIUNGERE IMMAGINE DIAGRAMMA DELLE CHIAMATE

<img src="https://aangrw.db.files.1drv.com/y4mEhRW6T5TB0O0Mho701IYnQXQdDOJF2mQIUPNw_R71E2z5FatOYllR78DkHytWX_s-z2WZ69ykeVfNwLCduu1MFWid4BNliwPp9FYPhB1qqdcoyDOqG2T03lKZzPiVdvYNbpL37gHcxiQJ5Eux9jGGmxijLTeBb1gtI7e4VUQezMYX_q2GLxltMmZADDv3Nse?width=1633&height=961&cropmode=none" width="500"/>






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
|GET             |/getTweetForCity?distanza= "distanza in Km"          |restituisce le città con il numero di tweet entro la distanza |






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
<img src="https://rppelg.db.files.1drv.com/y4maciwzZJ9nH7Wm3HyjkPmrMvktnQiSU_ZemRVRfVzy35kYy5nyRCbNHqaa7B1BK6j7XstmwmpAYtRUrq_O5tk_lr4_lHnS-Vme5Jd9YRFyqcRXg9MfWf6QY_V1Aj7GfpBennaT41-Iwh6-qyvZXxTuhc7bH3A2QWC-IlQx0YUONN_uM-ph9E3E602rsTip20u?width=1683&height=939&cropmode=none" width="800"/>

### Classi
* **Package com.esame.controller**
<img src="https://aamxpq.db.files.1drv.com/y4m5bzw69D848mwxyKNJbZbIHmgWUlc1G1_rJj6LWa_-CJR87ObrwRQBoB274o_s0VbOcn2QALib_DG8VUR6oZzCBGJ0yeyGgpf5k34tEIeonaKBNqrrGVYwTw7-67Xz0RdqD7uImtcw-Sn_jPYWEJ5_TFJePlxl889h6xv0AQHWfYTBvFMVI-vBtWkFJObKSZn?width=933&height=437&cropmode=none" width="400"/>

* **Package com.esame.database**
<img src="https://rp8yxa.db.files.1drv.com/y4mnruecugcm6RTLVwPqrmBuSukU7a0KmlxIGyiSebWmlQ4_1Q8oZeoV_SpPWaadXkkkppIwjNLzDMY6hOzRtJy6KD6wPz84LPtj7rSOVig7U15BYreqCTpmOSV6g_FbZiz0F6uwUC8M8enVCMDIPViwtVwn00DrX8VPSWc6Mx9Dv7lGnbeEwiAhzXimALGPZyP?width=821&height=396&cropmode=none" width="400"/>

* **Package com.esame.model**
<img src="https://aan8ma.db.files.1drv.com/y4mst12CQranENvifwfmvWa4ncDGn63NjpFMfOqQSHoaHnAzgMRSasV5wmJ4TMG2VXTde6XGPzg9skXnnuTVc3mXzUHqlWHeRhzQfVN1ToyebtJOcreboYFDytJVeJ4zTW8BL5OKSppuW1FurY-bZgF3i5_-GIwMiZbSmvduMyjtVqOXtSxkOSyCkA1yMf-exX7?width=1827&height=937&cropmode=none" width="1000"/>

* **Package com.esame.service**
<img src="https://rp9orq.db.files.1drv.com/y4mlQ7vBw8uVCWRk2IapGMC2I1dLY5ZgVqw0eYcWPdgwqpf0gKrmeTwCrDmuypZcIcbXjfMJKp8KH-4dPML_xgf0a2QsEwEJnOOF9YPmEMiJGEd8H8N4eOdlDLTWh4qUT-1HEAN_0ABvOJ9ArAPxuc3Irsi4rAPvJheE6c0-8j9GhT4OZCuMv_Na6EjVkECuN7k?width=1993&height=881&cropmode=none" width="1000"/>

* **Package com.esame.exception**
<img src="https://aaoygq.db.files.1drv.com/y4mAsvRthPQ5MgTsGJ-XYvf58M80wunSVoPNPTyWqQm3rFPVaLrLDA8qCRz7T_h2Lj2VTKpqdQLGf3l6N3MkUCC7v2OfHrzzPsYcD5TFWc418nrdTQEvc52pkM6qdSz4Wy0Niyi7gSm7VjoNc9DSG1exJaxdEl9QzCWZJdcRQAJ_Far-r_ygLYSgAt2pJ9RqY2D?width=1605&height=897&cropmode=none" width="1000"/>

* **Package com.esame.util.filter**
<img src="https://aannjg.db.files.1drv.com/y4mRe4nkI2sZHTfzoUOhl5VQzbQCNTQzkehgM3jzCfuIXKwbyFJ3_HQkmp6OSAGOiDQWWsHvz6mvT0AkAKCihxnMrimR3yjGKMlxYQAsK9XKRdGRKHGg5PQ3N5ry3w_jehwrbFi-hjckttqFHt_zkUs9bx0Iw2_LKSQZY2D4TEP9lF_fKH8pJe-c3RfCUuHUJQm?width=2191&height=927&cropmode=none" width="1000"/>

* **Package com.esame.util.statistics**
<img src="https://aaphuw.db.files.1drv.com/y4mRjse4Z7iBHx3XFvqaEHepcsuecehoiYqbgD1_J7f11scOMKPk7cryrGDn6I7ZhdkOnTjW-BKSUyPMl3ejWLUoqa8pQc8kkF-TH6KgFj5MfZg8bfZfF48Y-QpxxT-g-iG-fLFHfQ_Sq20CAiQN20ANwoSfxj71-PGxtm3n2osNiywIStxMPmStpQfcOyO-gyT?width=1727&height=837&cropmode=none" width="1000"/>


### Chiamate

* **Chiamata GET /metadata**
ControllerClass esegue una chiamata tramite il metodo `getArrayMetadata`, il quale inizializza un ArrayList di metadata e lo restituisce. ControllerClass trasforma quest ultimo in Json e lo ritorna al client.
<img src="https://rpqn0a.db.files.1drv.com/y4mkdQHH_Pa33tVJuMamcWrvjIjrXnrRZVW0xQ9mGEJyXwnD_3q-sIDR5AOVWyu1jvLzTLHZFAq8x3hJWBUUgxCQHVI6KiMOMFZvJEve0KDmmGp8tJx4yat-iDa1-l0RgrCK1NvHRJFpHsNA76rTKROeFBnReGhmgTeOrE6hruyr1At0_sXHdUf1KDT-940q3Ue?width=1950&height=407&cropmode=none" width="1000"/>

* **Chiamata GET /data**
ControllerClass esegue una chiamata tramite il metodo `getRecords`, il quale restituisce l'intero ArrayList di Record. ControllerClass trasforma quest ultimo in Json e lo ritorna al client.
<img src="https://rp8dug.db.files.1drv.com/y4mbH1IgQ_orEo7XdkN503RzT0b-r4DAnOLFlmZzzoZQpqnQMIiLre9d4b_WW-dfBJEKDrm0tY0LovdwqtAVDP7pVt8JOeKEZ2Z-kDsZAfsaecyxaOuCDX1paW5P5rAxP19vTI7wIbkfNm9N2WaqPhquJvBY2Hxvc9kzDk4l1Y3_KyMel0t1yIFzoi2wpHxVcHF?width=1894&height=391&cropmode=none" width="1000"/>

* **Chiamata POST /data**
ControllerClass esegue una chiamata tramite `jsonParserColumn` alla classe JsonParser, che insieme a `jsonParserOperator` effetueranno il parsing del body ricevuto in modo ciclico. Estrapolate le informazioni relative al filtraggio richiesto, verranno utilizzate da `instanceFilter` per istanziare nuovi oggetti filtro prendedoli della classi contenute nel package com.esame.util.filter. A questo punto tramite `runFilter` si potrà eseguire il filtraggio e restituire a ControllerClass l'Arraylist di Record filtrato da consegnare al Client in formato Json. 
<img src="https://aaoddw.db.files.1drv.com/y4mwkin2INnRgju0sxyVK0HJtkbNBEyC8jhb4p7mCstWce3Nn1WqeWR2I73_GYlyMYxx2Ke5jsURAdKonsHU6TR4iIvoIB2tPFIhQTFDhdikZcTkT6HYKx47yAsDzPujeiUFo-LbRxwpyNlQ9zNz3H7iqM9MfTEZTNRt6fcRPzCABahPQzkr0gcv8RQd4f7p-jA?width=1920&height=644&cropmode=none" width="1000"/>

* **Chiamata GET /stats?field="nome"**
L'arrayList di Record sul quale fare il calcolo delle statisctiche viene ottenuto nel modo spiegato nella richiesta *GET /data*.
Viene passato il nome del campo su cui si desidera effettuare la statistica a `instanceStatsCalculator`, il quale instanzia l'oggetto `StasCalculator` corretto dalle classi contenute nel package com.esame.util.statistics.
Quest'ultimo tramite il metodo `run` eseguirà il calocolo statistico che verrà incapsulato come oggetto stats, e restituito in formato Json al client
<img src="https://rpq8cg.db.files.1drv.com/y4mqN_XoixpKzN2XSjgqCpET4UC0nZyeijxp7cN1ECPoJZJcNMsKXh1_uanj2Q_oTSN_cSiY0KmlYp51ltFvw5et3g7Bo1sQOKHagwd7odz0amXwz9_ghYZGr7xYyViC1CnqEdkOetP4RZHAntaWdpG5BfpIYBksOrWFsHE7W-ner0NZ8cyUWz2Vj6Ffc79Ajza?width=1902&height=587&cropmode=none" width="1000"/>

* **Chiamata POST /stats?field="nome"**
L'arrayList di Record sul quale calcolare le statisctiche viene ottenuto nel modo spiegato nella richiesta *POST /data*.
Il calcolo statistico viene eseguito come spiegato nella richiesta *GET /stats?field="nome"*
<img src="https://rp85ow.db.files.1drv.com/y4m7xIPFBfoBq4XEhcwG1jFk31GgrW9wpRnrzkVkDOPuN-gp-HQASwJwpkceTeAInI6UbkIuKP7nlKddclBxIQI0cpg8Tqp_GlGvxwoIe8UP31IsU4RrjpHVcDxRXNoYESYcJaAWHeL6ijlJEwiSpKDmhMpeZVmzevg4TkTCQKB7g4e2iE_a7S2JTHEwhSimaWt?width=1922&height=860&cropmode=none" width="1000"/>


## Softwere utilizzati

* [Eclipse](https://www.eclipse.org/) - ambiente di sviluppo integrato
* [Spring Boot](https://spring.io/projects/spring-boot) - framework per  sviluppo applicazioni Java
* [Maven](https://maven.apache.org/) - strumento di gestione di progetti

## Autori

* **Lorenzo Macchione** - [GitHub](https://github.com/LorenzoMacchione)
* **Donato Mariano** - [GitHub](https://github.com/Doonato)
