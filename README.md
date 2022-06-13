# Create Maven Project

1. At VS Code, launch command using cmd + shift + p
2. Type in Maven and search for Create Maven Project
3. Select "maven-archetype-quickstart"
4. Select version (usually the latest)
5. Input java package
6. Select directory
7. Change maven pom.xml <maven.compiler.source> & <mave.compiler.target> value to current java version (use `java --version` to check)
8. Set classpath using `export CLASSPATH="./target/classes"`

## Running a Maven Project

1. Compile the project using `mvn package` or `mvn compile` at the root directory.
2. Check that the snapshot \*.jar file is in the targets folder
3. Run program using `java -cp target/<something>-SNAPSHOT.jar <package>.<app name>`
4. Repeat steps 1 and 3 to run the program.
