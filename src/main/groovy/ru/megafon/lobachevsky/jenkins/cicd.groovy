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
            script.sh(script: 'gradle test --info', returnStdout: true)
        }

        script.stage('build') {
            script.sh(script: 'gradle build --info', returnStdout: true)
        }

        Map parallelStages = [:]
        parallelStages['run'] = {
            script.stage('run') {
                script.sh(script: 'gradle run_app --info', returnStdout: true)
            }
        }

        parallelStages['test'] = {
            script.stage('test') {
                script.sleep(time: 10, unit: 'SECONDS')
                for (i in 0..3) {
                    script.sh 'curl localhost:8099/hello'
                }
            }
        }

        script.parallel parallelStages
    }

}

return this.&runPipeline