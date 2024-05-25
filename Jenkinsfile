pipeline {
    agent any
    stages {
        stage('Run Groovy Script') {
            steps {
                script {
                    load './builder.groovy'
                }
            }
        }
    }
}
