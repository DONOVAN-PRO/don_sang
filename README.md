# Réseau Don de Sang — correction conforme au sujet

Cette version retient **Spring Boot monolithique + Thymeleaf + PostgreSQL**. Ce choix est autorisé par le sujet et permet de livrer backend API et interface dans une seule application.

## Checklist du barème

- [x] 10 classes métier + relations JPA
- [x] DTOs pour les échanges API : aucune entité JPA n'est renvoyée directement par les controllers
- [x] Inscription donneur / établissement
- [x] Code d'activation numérique avec expiration et renvoi
- [x] Email généré depuis un template Thymeleaf
- [x] Validation manuelle des établissements par coordinateur
- [x] Connexion email/mot de passe avec session Spring Security
- [x] Connexion Google OAuth2 (réservée aux donneurs)
- [x] Redirection vers le dashboard selon le rôle
- [x] Profil avec méthode, dates, rôle et informations modifiables
- [x] Déconnexion avec confirmation
- [x] Dashboard donneur : dons, éligibilité, vies estimées, demandes actives
- [x] Dashboard établissement : stock, demandes actives, RDV de la semaine, dons du mois
- [x] Dashboard coordinateur : donneurs, établissements actifs, dons réseau, demandes ouvertes, répartition par groupe
- [x] Stock par groupe sanguin
- [x] Demandes urgentes
- [x] Rendez-vous
- [x] Enregistrement des dons + mise à jour du dernier don + stock
- [x] Gestion des comptes par coordinateur
- [x] Alertes ciblées par groupe sanguin + notification interne + email
- [x] Tâche planifiée quotidienne : éligibilité + notifications/emails + clôture des demandes expirées
- [x] Tous les endpoints imposés + endpoints complémentaires utiles
- [x] Swagger/OpenAPI
- [x] PostgreSQL local et production
- [x] Configuration Render par variables d'environnement
- [x] Interface responsive et thème dédié au don de sang

## Lancer en local

Prérequis : Java 21, Maven et PostgreSQL.

Créer la base :

```sql
CREATE DATABASE don_sang;
```

Variables facultatives : `DB_USERNAME`, `DB_PASSWORD`, `DATABASE_URL`, `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD`, `GOOGLE_CLIENT_ID`, `GOOGLE_CLIENT_SECRET`.

Puis :

```bash
mvn clean spring-boot:run
```

Application : `http://localhost:8080`
Swagger : `http://localhost:8080/swagger-ui.html`

## Comptes de démonstration

Pour générer automatiquement les trois comptes, définir `SEED_TEST_DATA=true`.

| Rôle | Email | Mot de passe |
|---|---|---|
| Donneur | donneur@test.com | Donneur1234! |
| Établissement | etablissement@test.com | Etab1234! |
| Coordinateur | coordinateur@test.com | Coord1234! |

L'établissement de démonstration est déjà validé.

## Google OAuth2

Configurer `GOOGLE_CLIENT_ID` et `GOOGLE_CLIENT_SECRET`. La callback Spring Security est :

`{domaine}/login/oauth2/code/google`

L'endpoint demandé `/api/auth/callback-google` est également présent comme route de compatibilité, mais la callback OAuth réelle est gérée par Spring Security.

## Email

Le code d'activation est rendu par `templates/emails/code-activation.html`. Pour Gmail, utiliser un mot de passe d'application.

## Render

Créer un Web Service Java connecté à PostgreSQL. Définir les secrets dans les variables d'environnement, jamais dans Git :

- `DATABASE_URL` : URL JDBC PostgreSQL (`jdbc:postgresql://...`)
- `DB_USERNAME`
- `DB_PASSWORD`
- `MAIL_HOST`
- `MAIL_PORT`
- `MAIL_USERNAME`
- `MAIL_PASSWORD`
- `GOOGLE_CLIENT_ID`
- `GOOGLE_CLIENT_SECRET`
- `SEED_TEST_DATA=false`

Build : `mvn clean package -DskipTests`
Start : `java -jar target/reseau-don-sang-1.0.0.jar`

Le port est pris depuis `PORT` fourni par Render.

## Endpoints imposés

### Authentification
`POST /api/auth/inscription` · `POST /api/auth/verification-code` · `POST /api/auth/renvoi-code` · `POST /api/auth/connexion` · `GET /api/auth/connexion-google` · `GET /api/auth/callback-google` · `POST /api/auth/deconnexion`

### Profil
`GET /api/profil` · `PUT /api/profil`

### Donneur
`GET /api/donneur/tableau-de-bord` · `GET /api/donneur/demandes-urgentes` · `POST /api/donneur/rendez-vous` · `DELETE /api/donneur/rendez-vous/{id}` · `GET /api/donneur/dons`

### Établissement
`GET /api/etablissement/tableau-de-bord` · `GET /api/etablissement/stock` · `PUT /api/etablissement/stock/{groupeSanguin}` · `POST /api/etablissement/demandes-urgentes` · `PUT /api/etablissement/demandes-urgentes/{id}/cloturer` · `GET /api/etablissement/rendez-vous` · `PUT /api/etablissement/rendez-vous/{id}/confirmer` · `POST /api/etablissement/dons`

### Coordinateur
`GET /api/coordinateur/tableau-de-bord` · `GET /api/coordinateur/utilisateurs` · `PUT /api/coordinateur/etablissements/{id}/valider` · `PUT /api/coordinateur/etablissements/{id}/rejeter` · `PUT /api/coordinateur/utilisateurs/{id}/desactiver` · `GET /api/coordinateur/demandes-urgentes` · `POST /api/coordinateur/alertes`
