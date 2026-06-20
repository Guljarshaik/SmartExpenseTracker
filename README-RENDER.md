Render deployment instructions for Expenses Tracker

1) Create a new Web Service on Render
   - Choose "Docker" or connect your GitHub repo (Render will use Dockerfile)
   - Root directory: repository root
   - Build command: leave empty (Dockerfile handles build)
   - Start command: leave empty (Dockerfile/Tomcat default)

2) Add environment variables in Render (Service > Environment)
   - `DB_URL`   e.g. `jdbc:mysql://<host>:<port>/<database>`
   - `DB_USER`  your MySQL username
   - `DB_PASSWORD` your MySQL password

3) Create a managed MySQL Database on Render (optional)
   - Go to Render > Databases > New > MySQL
   - After the DB is provisioned, copy the connection string and set `DB_URL`, `DB_USER`, `DB_PASSWORD` for the service

4) Deploy
   - If connected to GitHub, push to main and Render will build automatically
   - If you uploaded directly, trigger a manual deploy

Notes:
- The Dockerfile copies `ExpensesTracker.war` into Tomcat's webapps as `ROOT.war` so your app is available at the root URL.
- Make sure the WAR in the repository is up-to-date (recreate before pushing if you modify Java sources).
- Set the Render service to use at least 512MB memory for Tomcat.

Example `DB_URL` for Render-managed MySQL:
`jdbc:mysql://<host>:3306/ledger_db?useSSL=false&serverTimezone=UTC`
