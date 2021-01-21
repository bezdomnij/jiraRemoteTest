pipeline {
    agent any
    parameters {
            string(name: 'BROWSER', defaultValue: 'both', description: 'Browsers to run: Both, Chrome, Firefox')
            string(name: 'chrome', defaultValue: 'chrome', description: 'Chrome browser')
            string(name: 'firefox', defaultValue: 'firefox', description: 'Firefox browser')
            }

    stages {
        stage('Build') {
            steps {
                echo 'Build phase: '
                sh 'mvn clean'
            }
        }
        stage('Test') {
            parallel {
                stage('run with chrome') {
                   when {
                        expression { params.BROWSER == 'both' || params.BROWSER == 'chrome' }
                    }
                    steps {

                        sh 'echo 1'
                        sh 'echo $BROWSER'
                        sh 'mvn -Dtest=AppTest#shouldAnswerWithTrue test'
                        echo chrome

                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                                            }
                                        }

                }
                stage('run with firefox') {
                   when {
                        expression { params.BROWSER == 'both' || params.BROWSER == 'firefox' }
                    }
                    steps {
                        sh 'echo 2'
                        sh 'echo $BROWSER'
                        sh 'mvn -Dtest=AppTest#shouldAnswerWithTrue test'
                        echo firefox
                    }
                    post {
                          always {
                          junit 'target/surefire-reports/*.xml'}
                                        }
                }
            }
        }
    }
    post {
        always {
            echo 'Cleanup phase: '
            cleanWs()
        }
    }
}