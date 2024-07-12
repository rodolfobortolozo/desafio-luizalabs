pipeline{
    agent any

    tools{
        maven 'maven_395'
        jdk 'JAVA21'
    }

    stages{
        stage("Verificar Ferramentas"){
            steps{
                sh "mvn --version"
                sh "java -version"
            }

        }
        stage("Checkout Código"){
            steps{
                git branch: 'main',
                url: "https://github.com/rodolfobortolozo/desafio-luizalabs.git"
            }
        }

        stage("Code coverage") {
           steps {
               jacoco(
                    execPattern: '**/target/**.exec',
                    classPattern: '**/target/classes',
                    sourcePattern: '**/src',
                    inclusionPattern: 'br/com/labslogistica/**',
                    changeBuildStatus: true,
                    minimumInstructionCoverage: '30',
                    maximumInstructionCoverage: '80')
           }
        }

        stage('OWASP Dependency-Check Vunerabilidade') {
            steps {
                dependencyCheck additionalArguments: '''
                -o './'
                -s './'
                -f 'ALL'
                --prettyPrint''', odcInstallation: 'dependency_check8.4.3'
                dependencyCheckPublisher pattern: 'dependency-check-report.xml'

            }
        }

        stage("Qualidade do Código"){
            steps{
                sh "mvn clean verify sonar:sonar -Dsonar.projectKey=Sky_lab -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_d4f25d500467eeec8cc38180462bf8fc14ff28ab"
            }
        }

        stage("Build da Aplicação"){
            steps{
                sh "mvn package"
            }
        }
    }

}