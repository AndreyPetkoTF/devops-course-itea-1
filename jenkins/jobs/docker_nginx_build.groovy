job("docker-nginx-build") {
  wrappers {
      preBuildCleanup()
  }
  logRotator {
      numToKeep(1)
  }
  scm {
      git {
          remote {
              github("AndreyPetkoTF/devops-course-itea", 'https')
              branch("master")
          }
      }
  }
  parameters {
        stringParam('DOCKER_LOGIN', '', 'Docker login')
        stringParam('DOCKER_PASSWORD', '', 'Docker password')
  }
  steps {
    def cmd = '''#!/bin/bash +x
    def parameters = build?.actions.find{ it instanceof ParametersAction }?.parameters
    cd devops-course-itea/nginx
    docker build -t my-nginx .
    docker login -u $DOCKER_LOGIN -p $DOCKER_PASSWORD
    docker tag my-nginx andreypetko/my-nginx
    docker push andreypetko/my-nginx
    '''.stripIndent()

    shell(cmd)
  }
}
