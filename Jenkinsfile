pipeline {
    agent any
    
    environment {
        PIPELINE_JSON = 'pipeline.json'
    }
    
    stages {
        stage('Deploy Docker Container') {
            steps {
                script {
                    def aksDeployer = load 'builder.groovy'
                    aksDeployer.deploy(env.PIPELINE_JSON)
                }
            }
        }
    }
}
