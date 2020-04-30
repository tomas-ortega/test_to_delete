pipeline {
    agent any
    tools {
        maven 'Maven 3'
        jdk 'JDK_1_8_ORACLE'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: "${env.NUM_TO_KEEP_ARTIFACTS}", artifactNumToKeepStr: "${env.NUM_TO_KEEP_ARTIFACTS}"))
        timestamps()
    }

    stages {
        stage('Clean WorkSpace') {
            steps {
                cleanWs()
            }
        }

        stage('Compile .war') {
            steps {
                sh "mvn install"
            }
        }
    }
}
