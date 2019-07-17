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
  steps {
    def cmd = '''#!/bin/bash +x
    cd devops-course-itea/nginx
    docker build -t my-nginx .
    docker login -u andreypetko -p 1qazse4
    docker tag my-nginx andreypetko/my-nginx
    docker push andreypetko/my-nginx
    '''.stripIndent()

    shell(cmd)
  }
}
