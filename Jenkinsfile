pipeline {
    agent any

    stages {
        stage('Deploy') {
            steps {
                script {
                    deploy('input.json')
                }
            }
        }
    }
}
