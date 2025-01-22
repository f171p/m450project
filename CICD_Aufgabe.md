## Aufgabe 1: Auswahl der Softwarelösungen für Deployment-Umgebungen

Nach einer kurzen Recherche zu den vorgestellten Softwarelösungen habe ich folgende Entscheidungen für den Einsatz in den verschiedenen Deployment-Umgebungen getroffen:

- **Development Environment:** Für die Entwicklungsumgebung ist **Vagrant** optimal geeignet. Mit Vagrant lassen sich Entwicklungsumgebungen schnell und konsistent einrichten, was ideal für die lokale Entwicklung ist. Es ermöglicht eine einfache Konfiguration und Isolation der Entwicklungsumgebung.

- **Testing Environment:** Für die Testumgebung eignet sich **Docker Compose** sehr gut. Docker Compose erlaubt es, die verschiedenen Dienste der Anwendung in Containern zu orchestrieren, was das Testen der gesamten Anwendung in einer isolierten Umgebung erleichtert.

- **Staging Environment:** Für die Staging-Umgebung würde ich **Kubernetes** verwenden. Kubernetes ist perfekt, um eine produktionsähnliche Umgebung aufzusetzen, in der Skalierbarkeit und Ausfallsicherheit getestet werden können. Es bietet zudem gute Möglichkeiten zur Verwaltung von Ressourcen und zur Überwachung.

- **Production Environment:** Für die Produktionsumgebung ist **Terraform** die beste Wahl. Terraform ermöglicht es, die Infrastruktur als Code zu definieren und zu verwalten, was eine robuste und wiederholbare Bereitstellung der Infrastruktur sicherstellt.

## Aufgabe 2: Umsetzung einer automatisierten Umgebung

### Entscheidung und Umsetzung
Ich habe mich für **Docker Compose** entschieden, da es sich besonders gut für die Testing-Umgebung eignet. Die Idee ist, die "Recipe Application" zu verwenden, die aus einem Spring Boot-Backend und einem React-Frontend besteht und keine Datenbank benötigt.

### Schritte zur Umsetzung
1. **Vorbereitung:**
   - Das Projekt wurde lokal geklont.
   - Die Anwendung besteht aus zwei Services:
     - Einem Spring Boot-Backend
     - Einem React-Frontend

2. **Erstellung der `docker-compose.yml`:**
   - Die Konfigurationsdatei wurde so erstellt, dass das Backend und das Frontend in separaten Containern laufen.

```yaml
version: '3.8'
services:
  backend:
    build: ./recipe-planner-backend
    ports:
      - "8080:8080"
    command: ./mvnw spring-boot:run

  frontend:
    build: ./recipe-planner-frontend
    ports:
      - "3000:3000"
    command: npm start
```

3. **Aufsetzen der Umgebung:**
   - Die Umgebung wurde mit `docker-compose up` gestartet.
   - Die beiden Services kommunizieren über das interne Docker-Netzwerk.

4. **Probleme und Lösungen:**
   - Es gab anfänglich Probleme mit der Port-Konfiguration, die durch Anpassungen in der `application.properties` des Backends gelöst wurden.
   - Einige Abhängigkeiten mussten manuell installiert werden, was durch ein Update der `Dockerfile` behoben wurde.

### Fazit
Die Verwendung von Docker Compose war einfach und effizient. Es ermöglicht eine schnelle Bereitstellung und das Testen der Anwendung in einer isolierten Umgebung. Für zukünftige Projekte ist Docker Compose ideal, wenn es um das Testen mehrerer Dienste geht, die zusammenarbeiten müssen. Besonders hilfreich ist es in Teams, um sicherzustellen, dass alle Entwickler mit derselben Umgebung arbeiten.
