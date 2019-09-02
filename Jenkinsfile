pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
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
      parallel {
        stage('Deliver') {
          steps {
            sh 'jenkins/scripts/deliver.sh'
          }
        }
        stage('Make File Executable') {
          steps {
            sh 'sh chmod +x jenkins/scripts/deliver.sh'
          }
        }
      }
    }
  }
  tools {
    maven 'maven'
  }
}