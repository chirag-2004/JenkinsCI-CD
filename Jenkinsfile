pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = "chirag1804/traineeapi"
        CONTAINER_NAME = "traineeapi-container"
    }

    stages {

        stage('Build JAR') {
            steps {
                dir('TraineeAPI') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('TraineeAPI') {
                    bat 'docker build -t %IMAGE_NAME% .'
                }
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    bat '''
                    docker logout
                    docker login -u %USER% -p %PASS%
                    '''
                }
            }
        }

        stage('Push Image') {
            steps {
                bat 'docker push %IMAGE_NAME%'
            }
        }

        stage('Run Container') {
            steps {
                bat '''
                docker rm -f %CONTAINER_NAME% || exit 0

                docker run -d -p 8081:8081 ^
                -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/SpringBootDB ^
                -e SPRING_DATASOURCE_USERNAME=postgres ^
                -e SPRING_DATASOURCE_PASSWORD=TIGER ^
                --name %CONTAINER_NAME% %IMAGE_NAME%
                '''
            }
        }
    }
}
