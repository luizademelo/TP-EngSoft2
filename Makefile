# Makefile para compilar e executar um programa Java com leitura de arquivos de uma pasta

# Configurações
SRC_DIR := src
RESOURCES_DIR := $(SRC_DIR)/main/resources
MAIN_DIR := $(SRC_DIR)/main
JAVA_DIR := $(MAIN_DIR)/java
PACKAGE_DIR := $(JAVA_DIR)/com/election
OUTPUT_DIR := bin
CLASSPATH := $(OUTPUT_DIR)

# Compilador e opções
JAVAC := javac
JAVAC_FLAGS := -d $(OUTPUT_DIR) -classpath $(CLASSPATH)
JAVA := java
JAVA_FLAGS := -classpath $(CLASSPATH)

# Nome do arquivo principal
MAIN_CLASS := Main

# Encontrar arquivos-fonte Java recursivamente
JAVA_FILES := $(shell find $(JAVA_DIR) -name '*.java')

.PHONY: all clean run

all: $(OUTPUT_DIR)/$(MAIN_CLASS)

$(OUTPUT_DIR)/$(MAIN_CLASS): $(JAVA_FILES)
	@mkdir -p $(OUTPUT_DIR)
	@$(JAVAC) $(JAVAC_FLAGS) $(JAVA_FILES)

run: $(OUTPUT_DIR)/$(MAIN_CLASS)
	@$(JAVA) $(JAVA_FLAGS) $(MAIN_CLASS)

clean:
	@rm -rf $(OUTPUT_DIR)