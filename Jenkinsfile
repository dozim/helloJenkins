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
      parallel {
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
        stage('Print MANIFEST') {
          steps {
            sh '''NAME=`mvn help:evaluate -Dexpression=project.name | grep "^[^\\[]"`

VERSION=`mvn help:evaluate -Dexpression=project.version | grep "^[^\\[]"`

unzip -q -c target/${NAME}-${VERSION}.jar META-INF/MANIFEST.MF

java -jar target/${NAME}-${VERSION}.jar'''
          }
        }
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