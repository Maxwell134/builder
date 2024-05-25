import groovy.json.JsonSlurper

def deploy(inputfile){

    // Read inputs from JSON file
    def input = new JsonSlurper().parse(new File(inputFile))

    def dockerimage = input.docker.image
    def dockerTag = input.docker.tag
    def containerName = input.docker.container_name

    // Delete existing container if available 

    sh 'docker rm -f ${containerName}'

    echo 'Deleting container ${containerName}'
    

    // Build Docker image

    sh 'docker build -t ${dockerimage}:${dockertag} .'

    // Run Docker container
    sh """
    docker run  --rm -d --name ${containerName} -p 80:8080  ${dockerImage}:${dockerTag}
    """






}