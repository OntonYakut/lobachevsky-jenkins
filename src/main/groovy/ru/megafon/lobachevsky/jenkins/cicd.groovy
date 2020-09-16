def runPipeline (Script script){
    new PipelineCi(script).run()
}

class PipelineCi implements Serializable {

    static long serialVersionUid = 1L

    Script script

    PipelineCi(Script script) {
        this.script = script
    }

    void run() {
        script.stage('unit') {
            script.sh(script: 'gradle test --no-build-cache --refresh-dependencies  --info', returnStdout: true)
        }

        script.stage('build') {
            script.sh(script: 'gradle build --no-build-cache --refresh-dependencies  --info', returnStdout: true)
        }

        Map parallelStages = [:]
        parallelStages['run'] = {
            script.stage('run') {
                script.sh(script: 'gradle run_app --no-build-cache --refresh-dependencies  --info', returnStdout: true)
            }
        }

        parallelStages['test'] = {
            script.stage('test') {
                script.docker('lobachevsky-app:v').inside() {
                    script.sh 'java -jar lobachevsky-app-v1.0.0.jar'
                    for (i in 0..30) {
                        script.sh 'curl localhost:8099/hello'
                    }
                }
            }
        }

        script.parallel parallelStages
    }

}

return this.&runPipeline