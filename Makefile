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
JAVA_FILES := $(JAVA_DIR)/Main.java \
              $(JAVA_DIR)/com/election/helper/VoteInterfaceHelper.java \
              $(JAVA_DIR)/com/election/helper/MajorityVoteHelper.java \
              $(JAVA_DIR)/com/election/helper/WeightedVoteHelper.java \
              $(JAVA_DIR)/com/election/controller/MunipalElectionController.java \
              $(JAVA_DIR)/com/election/controller/ElectionController.java \
              $(JAVA_DIR)/com/election/controller/UDepartmentElectionController.java \
              $(JAVA_DIR)/com/election/controller/StateElectionController.java \
              $(JAVA_DIR)/com/election/controller/PresidentialElectionController.java \
              $(JAVA_DIR)/com/election/view/ReadAndPrintPresidential.java \
              $(JAVA_DIR)/com/election/view/ReadAndPrintState.java \
              $(JAVA_DIR)/com/election/view/ReadAndPrintUDepartment.java \
              $(JAVA_DIR)/com/election/view/ReadAndPrintMunicipal.java \
              $(JAVA_DIR)/com/election/view/ReadAndPrint.java \
              $(JAVA_DIR)/com/election/entity/Election.java \
              $(JAVA_DIR)/com/election/entity/PresidentialElection.java \
              $(JAVA_DIR)/com/election/entity/UDepartamentElection.java \
              $(JAVA_DIR)/com/election/entity/StateElection.java \
              $(JAVA_DIR)/com/election/entity/UVoter.java \
              $(JAVA_DIR)/com/election/entity/Voter.java \
              $(JAVA_DIR)/com/election/entity/Vote.java \
              $(JAVA_DIR)/com/election/entity/MunicipalElection.java \
              $(JAVA_DIR)/com/election/entity/Candidate.java \
              $(JAVA_DIR)/com/election/entity/CertifiedProfessional.java \
              $(JAVA_DIR)/com/election/enums/ElectionTypeEnum.java \
              $(JAVA_DIR)/com/election/enums/RoleEnum.java \
              $(JAVA_DIR)/com/election/enums/ElectionRoundEnum.java \
              $(JAVA_DIR)/com/election/enums/ElectionStatusEnum.java \
              $(JAVA_DIR)/com/election/enums/VoterRoleEnum.java
# Encontrar testes recursivamente
TEST_FILES := $(TEST_DIR)/VoteHelperTest.java \
              $(TEST_DIR)/ElectionControllerTest.java \
              $(TEST_DIR)/ElectionTest.java \
              $(TEST_DIR)/TestReadAndPrint.java

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
