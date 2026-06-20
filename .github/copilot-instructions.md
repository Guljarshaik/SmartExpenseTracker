# Copilot instructions for Expenses Tracker

Quick summary
- This is a small Java servlet-based webapp (no build system). Static frontend files in `frontend/` post forms to servlets implemented as plain `.java` files in `backend/` and mapped in `WEB-INF/web.xml`.

Big picture
- Frontend: `frontend/*.html` — static pages and forms (e.g., `signup.html` posts to `InsertUser`).
- Backend: `backend/*.java` — servlet-style classes compiled to `WEB-INF/classes` or packaged into a WAR. There is no package namespace (classes in default package).
- DB: `database/khatabook.sql` — provides schema. Note: SQL creates database `khatabook` but Java code currently connects to `ledger_db` (update one or the other).
- Deployment: meant for a servlet container (Tomcat). `WEB-INF/web.xml` lists servlet mappings (e.g., `InsertUser` -> `/InsertUser`).

Key files to reference when editing behavior
- `frontend/signup.html` — form action: `InsertUser` (POST). Use this to trace user registration flow.
- `backend/InsertUser.java` — performs JDBC insert; shows how parameters map from form names (`username`, `email`, `password`).
- `backend/DBConnection.java`, `backend/DBTest.java` — simple connection examples (hardcoded JDBC URL/user/password). Use them for local DB connectivity checks.
- `database/khatabook.sql` — canonical schema; update if you change table/column names used by Java code.
- `WEB-INF/web.xml` — servlet mappings; update when adding new servlets or changing URLs.

Project-specific conventions & gotchas
- No build tool: project expects you to compile `.java` files yourself and place `.class` files under `WEB-INF/classes` (or create a WAR). There is no Maven/Gradle.
- Default package: backend classes are in the default (unnamed) Java package. When moving to packages, update `web.xml` and classpaths accordingly.
- Hardcoded DB creds: `backend/*.java` contain literal DB credentials and database name. Treat them as secrets and move to environment/config when you refactor.
- DB name mismatch: SQL creates `khatabook` but Java connects to `ledger_db`. Before running, either create `ledger_db` using the SQL or change Java URLs to `khatabook`.

How frontend -> backend wiring works (example)
- `frontend/signup.html` form (fields: `username`, `email`, `password`) posts to URL `InsertUser`.
- `WEB-INF/web.xml` maps servlet name `InsertUser` to servlet class `InsertUser` and URL `/InsertUser`.
- `backend/InsertUser.java` expects parameters and runs an `INSERT INTO users (name,email,password)...` query.

Local dev / build / deploy steps (Windows PowerShell examples)
1) Ensure a servlet container (Apache Tomcat) is installed and running.
2) Get MySQL and create the DB. Either run `khatabook.sql` or create `ledger_db` if you keep current Java code.

PowerShell snippets (adjust paths):
```powershell
# Compile backend java files into WEB-INF/classes (create classes dir first)
mkdir -Force .\WEB-INF\classes
javac -cp ".;C:\path\to\mysql-connector-java.jar" -d .\WEB-INF\classes .\backend\*.java

# Copy MySQL Connector/J into WEB-INF/lib so Tomcat can load JDBC driver
mkdir -Force .\WEB-INF\lib
Copy-Item C:\path\to\mysql-connector-java.jar .\WEB-INF\lib\

# (Optional) Package as WAR for Tomcat: run from project root
jar -cvf ExpensesTracker.war *

# Deploy: copy WAR into Tomcat's webapps folder or place project folder into webapps
Copy-Item .\ExpensesTracker.war C:\path\to\apache-tomcat\webapps\
```

Debugging tips
- Check Tomcat logs (catalina.out / logs directory) for ClassNotFoundException or SQL errors.
- Common errors: wrong DB name, missing JDBC driver in `WEB-INF/lib`, servlet class not found because class compiled into wrong package or wrong folder layout.
- If forms appear to POST to wrong names, open the form (`signup.html`) and confirm `name=` attributes match `InsertUser.java` parameter retrieval.

Small, discoverable rules for edits
- When adding new servlets: add class to `backend/`, compile into `WEB-INF/classes`, and add mapping to `WEB-INF/web.xml` (servlet-name must match servlet-class if using default package).
- Prefer updating `database/khatabook.sql` when altering schema and then update Java SQL strings to match.
- Keep UI URLs stable: frontend uses relative links (e.g., `signup.html` -> `InsertUser`); changing a mapping requires updating the HTML.

Security & maintenance notes
- Remove hardcoded credentials; for now, treat `Guljar@2003` in code as a secret placeholder. Replace with environment-based config before committing to public repos.
- Consider adding a simple README and a build script (Ant/Maven/Gradle) if you plan to extend the project.

What I couldn't infer automatically
- Exact Tomcat version expected and any container-specific config. Assume a recent Tomcat 9+.
- Any CI/workflow expectations — no `.github/workflows` found.

If something is unclear, ask the maintainers these quick questions:
- Which database name should be authoritative: `khatabook` (SQL) or `ledger_db` (Java)?
- Where should DB credentials be sourced from in future work (env vars, properties file, or container JNDI)?

---
If you want, I can (pick one):
- add a short `README.md` with the commands above, or
- convert backend classes into a Maven structure and add a `pom.xml` to simplify builds.
