# Prova Finale di Ingegneria del Software - a.a. 2022-2023

**Gruppo AM37**<br>

![Testo alternativo](deliverables/home.jpg)



Scopo del progetto è quello di implementare il gioco da tavola [MyShelfie](https://www.craniocreations.it/prodotto/my-shelfie), seguendo il pattern architetturale Model View Controller.
Il risultato finale copre completamente le regole definite dal gioco e permette di interagirci sia con una interfaccia da linea di comando (CLI) che grafica (GUI), la rete è stata gestita sia con il tradizionale approccio delle socket che con una tecnologia specifica di Java (RMI).<br>

# Documentazione

### UML<br>

I seguenti diagrammi delle classi rappresentano rispettivamente gli UML di alto livello e di dettagli del server e dei client, e l'UML del protocollo di comunicazione:<br>

- [UML alto livello](URL del link)
- [UML dettaglio](URL del link)
- [UML protocollo](URL del link)


### JavaDoc <br>

La seguente documentazione include una descrizione delle classi e dei metodi utilizzati, seguendo le tecniche di documentazione di Java.<br>

- [JavaDocs](URL del link)



### Librerie e plugins<br>

| Libreria/Plugin| Descrizione |
| :--------------| :------    |
| Maven          |    strumento di gestione per software basati su Java e build automation      | 
| junit          |      framework dedicato a Java per unit testing    | 
| gson           |    libreria per il supporto al parsing di file in formato json | 
| JavaFx         |     libreria grafica di Java |

### Jars<br>

I seguenti jar permettono il lancio del gioco secondo le funzionalità descritte nell'introduzione. Le funzionalità realizzate secondo la specifica del progetto sono elencate sotto. La cartella in cui si trovano il software del client e del server si trova al seguente indirizzo: <br>

- [Jars](URL del link)

# Esecuzione dei Jars

### Client <br> 

Il client viene eseguito scegliendo l'interfaccia con cui giocare, le possibili scelte sono da linea di comando o interfaccia grafica. Le seguenti sezioni descrivono come eseguire il client in un modo o nell'altro.<br>

**CLI**<br>
Per lanciare il client in modalità CLI digitare il seguente comando:<br>

      java -jar AM37.jar cli
 

**GUI**<br>
Per poter lanciare il client con l'interfaccia grafica è necessario importare le dipendenze di JavaFx. 
A questo punto digitare il seguente comando che importa le dipendenze e lancia il client:

      java -jar AM37.jar gui


### Server<br>

L'esecuzione del server avviene attraverso il seguente comando:

      hhhh  

# Funzionalità

### Funzionalità sviluppate<br>

- Regole Complete <br>
- TUI <br>
- GUI <br>
- RMI <br>
- Socket <br>
- 1 FA<br>

### Funzionalità avanzate<br>

| Funzionalità                   |          |
| :----------------------------- | :------: |
| Patite multiple                |   ✅     | 
| Persistenza                    |   ❌     | 
| Resilienza alle disconnessioni |   ❌     | 
| Chat                           |    ❌    |


## Componenti del gruppo

- Bartocci Alessandra
- Bernasconi Margherita 
- Cicero Ramiro
- Cozzolino Nicola
