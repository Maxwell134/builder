def buildDockerImage(String imageName, String buildNumber) {
    sh "docker build -t ${imageName}:${buildNumber} ."
}


def runDockerContainer(String imageName, String buildNumber, String port) {
    def containerName = "${imageName.replace('/', '_')}-${buildNumber}"
    sh "docker stop ${containerName} || true"
    sh "docker rm ${containerName} || true"
    sh "docker run -d --name ${containerName} -p ${port}:${port} ${imageName}:${buildNumber}"
}

return this
