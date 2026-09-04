COLASANTO CLIENT NATIVE v2 · ANDROID + iOS

COSA CAMBIA
- L'interfaccia cliente è inclusa DENTRO l'app: non apre Chrome/Safari.
- Backend dati: Supabase COLASANTO già esistente.
- Login: telefono + PIN personale già generato dal gestionale.
- Funzioni incluse: prenotazioni, sedute residue, certificato medico, scheda allenamento.
- Sessione salvata sul dispositivo: dopo il primo accesso resta collegato finché i dati locali non vengono cancellati.
- Il pulsante PWA "Installa app" è rimosso nella build nativa.

ANDROID - TEST APK
Metodo più semplice: GitHub Actions.
1. Carica questa cartella in un repository GitHub.
2. Apri Actions > Build COLASANTO Android APK > Run workflow.
3. A fine workflow scarica l'artifact COLASANTO_CLIENT_ANDROID_TEST.
4. Dentro trovi COLASANTO_CLIENT_ANDROID_TEST.apk.

Oppure su Windows con Android Studio già configurato:
- doppio click BUILD_ANDROID_TEST.bat
- l'APK verrà copiato nella cartella principale.

ANDROID - PUBBLICAZIONE
Per la release definitiva servono firma/keystore COLASANTO e AAB per Google Play.
NON distribuire come definitiva l'APK debug prodotto dal test.

iPHONE / iOS
Per iOS serve macOS + Xcode per generare il progetto, firmare e pubblicare.
Su Mac: esegui PREPARA_IOS_MAC.command, poi configura Apple Team/Bundle ID in Xcode.
Bundle ID: it.colasanto.personalstudio
Nome app: COLASANTO

IMPORTANTE
- Questa è una base TEST nativa. Non è ancora la build App Store/Play definitiva.
- L'App Store richiederà account Apple Developer, firma e revisione.
- Quando avremo i link finali Android/iPhone, il gestionale userà il link unico "Installa COLASANTO" nei messaggi WhatsApp.
