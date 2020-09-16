def execute = { Script script ->
    new PipelineCi(script).run()
}

class PipelineCi implements Serializable {

    static long serialVersionUid = 1L

    Script script

    PipelineCi(Script script) {
        this.script = script
    }

    void run() {
        node() {
            script.stage('test') {
                script.sh(script: 'gradle test --no-build-cache --refresh-dependencies  --info', returnStdOut: true)
            }

            script.stage('build') {
                script.sh(script: 'gradle build --no-build-cache --refresh-dependencies  --info', returnStdOut: true)
            }

            script.stage('image') {
                script.sh(script: 'gradle image --no-build-cache --refresh-dependencies  --info', returnStdOut: true)
            }

            script.stage('deploy') {
                script.docker('lobachevsky-app:v').inside() {
                    script.sh 'java -jar lobachevsky-app-v1.0.0.jar'
                    for (i in 0..10) {
                        script.sh 'curl localhost:8080/hello'
                    }
                }
            }
        }
    }

}