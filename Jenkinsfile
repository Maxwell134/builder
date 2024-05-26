pipeline {
    agent any
    stages {
        stage('Run Builder Script') {
            steps {
                script {
                    // Load the builder.groovy script
                        gv = load 'builder.groovy'

                    if (gv == null) {
                        error "Failed to load 'builder.groovy'"
                    }

                    // Call the deploy method from builder.groovy
                    gv.deploy(inputFile) 
                    
                }
            }
        }
    }
}
