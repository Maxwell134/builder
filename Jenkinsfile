pipeline {
    agent any
    stages {
        stage('Run Builder Script') {
            steps {
                script {
                    // Load the builder.groovy script
                    def builder = load 'builder.groovy'

                    if (builder == null) {
                        error "Failed to load 'builder.groovy'"
                    }

                    // Call the deploy method from builder.groovy
                    builder.deploy 'pipeline.json'
                }
            }
        }
    }
}
