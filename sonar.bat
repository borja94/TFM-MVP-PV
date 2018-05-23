mvn clean verify sonar:sonar
  
REM # In some situation you may want to run sonar:sonar goal as a dedicated step. Be sure to use install as first step for multi-module projects
REM mvn clean install
REM mvn sonar:sonar
 
REM # Specify the version of sonar-maven-plugin instead of using the latest. See also 'How to Fix Version of Maven Plugin' below.
REM mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.4.0.905:sonar