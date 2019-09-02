pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'mvn -B -DskipTests clean package'
          }
        }
        stage('Make File Executable') {
          steps {
            sh 'chmod +x jenkins/scripts/deliver.sh'
          }
        }
      }
    }
    stage('Test') {
      post {
        always {
          junit 'target/surefire-reports/*.xml'

        }

      }
      steps {
        sh 'mvn test'
      }
    }
    stage('Deliver') {
      steps {
        sh 'jenkins/scripts/deliver.sh'
      }
    }
  }
  tools {
    maven 'maven'
  }
}