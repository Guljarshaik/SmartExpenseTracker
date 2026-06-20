FROM tomcat:9.0-jdk11

# Copy the WAR into Tomcat's webapps as ROOT.war so app is served at /
COPY ExpensesTracker.war /usr/local/tomcat/webapps/ROOT.war

# Expose default Tomcat port
EXPOSE 8080

# Run Tomcat (image's default CMD will start Tomcat)
