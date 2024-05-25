pipeline {
    agent any
    stages {
        stage('Deploy Application') {
            steps {
                script {
                    // Load the builder.groovy script
                    def builder = load 'builder.groovy'
                    
                    // Call the deploy method from builder.groovy
                    builder.deploy 'pipeline.json'
                }
            }
        }
    }
}
