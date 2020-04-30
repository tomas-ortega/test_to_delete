pipeline {
    agent any
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
