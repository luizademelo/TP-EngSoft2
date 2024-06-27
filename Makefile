# Configurações
SRC_DIR := src
RESOURCES_DIR := $(SRC_DIR)/main/resources
MAIN_DIR := $(SRC_DIR)/main
JAVA_DIR := $(MAIN_DIR)/java
TEST_DIR := $(SRC_DIR)/test
PACKAGE_DIR := $(JAVA_DIR)/com/election
OUTPUT_DIR := bin
LIB_DIR := lib
JUNIT_JAR := $(LIB_DIR)/junit-platform-console-standalone-1.9.3.jar
CLASSPATH := $(OUTPUT_DIR):$(JUNIT_JAR)

# Compilador e opções
JAVAC := javac
JAVAC_FLAGS := -d $(OUTPUT_DIR) -classpath $(CLASSPATH)
JAVA := java
JAVA_FLAGS := -classpath $(CLASSPATH)

# Nome do arquivo principal
MAIN_CLASS := com.election.Main

# Encontrar arquivos-fonte Java recursivamente
JAVA_FILES := $(shell find $(JAVA_DIR) -name '*.java')
# Encontrar testes recursivamente
TEST_FILES := $(shell find $(TEST_DIR) -name '*.java')

.PHONY: all clean run test setup

all: $(OUTPUT_DIR)/$(MAIN_CLASS)

$(OUTPUT_DIR)/$(MAIN_CLASS): $(JAVA_FILES) $(TEST_FILES)
	@mkdir -p $(OUTPUT_DIR)
	@$(JAVAC) $(JAVAC_FLAGS) $(JAVA_FILES) $(TEST_FILES)

run: $(OUTPUT_DIR)/$(MAIN_CLASS)
	@$(JAVA) $(JAVA_FLAGS) $(MAIN_CLASS)

test: $(OUTPUT_DIR)/$(TEST_CLASS)
	@$(JAVA) $(JAVA_FLAGS) org.junit.platform.console.ConsoleLauncher --classpath $(CLASSPATH) --scan-classpath

clean:
	@rm -rf $(OUTPUT_DIR)

setup:
	@mkdir -p $(LIB_DIR)
	@curl -L -o $(JUNIT_JAR) https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar
