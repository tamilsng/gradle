pipeline {
    agent {
        label 'maven'
    }
    stages {
        stage('gradle Test and Package') {
            steps {
                sh "gradle clean build"
            }
        }
        stage('--test--') {
            steps {
                sh "echo test"
            }
        }
        stage('--package--') {
            steps {
                sh "echo done"
            }
        }
    }
}
