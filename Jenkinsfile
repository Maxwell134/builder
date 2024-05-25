pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code repository
                checkout scm
            }
        }
        stage('Run Groovy Script') {
            steps {
                script {
                    // Load and evaluate the Groovy script
                    def groovyScript = load 'sample.groovy'
                    groovyScript()
                }
            }
        }
    }
}
