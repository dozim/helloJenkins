pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
        sh '''NAME=`mvn help:evaluate -Dexpression=project.name | grep "^[^\\[]"`

VERSION=`mvn help:evaluate -Dexpression=project.version | grep "^[^\\[]"`

unzip -q -c target/${NAME}-${VERSION}.jar META-INF/MANIFEST.MF

java -jar target/${NAME}-${VERSION}.jar'''
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
    stage('Ready for Evaluation') {
      steps {
        sh 'chmod +x jenkins/scripts/deliver.sh'
      }
    }
    stage('Evaluate') {
      steps {
        sh 'jenkins/scripts/deliver.sh'
      }
    }
  }
  tools {
    maven 'maven'
  }
}