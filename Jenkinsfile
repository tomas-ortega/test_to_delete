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

        stage('Sync Git Repo') {
           steps {
              checkout([$class: 'GitSCM',
		  branches: [[name: "*/$BRANCH_NAME"]],
		  doGenerateSubmoduleConfigurations: false,
		  extensions: [[$class: 'SubmoduleOption',
		                recursiveSubmodules: true
		                ]], 
		  submoduleCfg: [],
		  userRemoteConfigs: [[url: "https://github.com/tomas-ortega/jenkins-java-maven-sonar.git"]]])

           }
        }

        stage('Compile .war') {
            steps {
                sh "mvn install"
            }
        }

        stage('Clean destination folder files') {
            steps {
                sh "ssh jenkins@10.0.2.15 'rm -f /home/jenkins/jenkins-ci/nominas_backend/files_to_image/*.war'"
            }
        }

        stage('Delivery .war') {
            steps {
                sh "scp ./target/*.war jenkins@10.0.2.15:/home/jenkins/jenkins-ci/nominas_backend/files_to_image/"
            }
        }
    }
}
