# Use the official Tomcat image with JDK 11
FROM tomcat:9.0-jdk11

# Remove the default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy your WAR file to the Tomcat webapps directory
COPY teacherTT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080 for the application
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
