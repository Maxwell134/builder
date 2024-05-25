pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code repository
                checkout scm
                // List files in the workspace to verify the presence of sample.groovy
                    sh 'ls -al'
            }
        }
        stage('Run Groovy Script') {
            steps {
                script {
                    // Load and evaluate the Groovy script
                    def groovyScript = load './sample.groovy'
                    if (groovyScript == null) {
                        error "Failed to load './sample.groovy'"
                    }
                    groovyScript()
                }
            }
        }
    }
}
