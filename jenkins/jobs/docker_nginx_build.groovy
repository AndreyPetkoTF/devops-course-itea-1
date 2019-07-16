job("docker-nginx-build") {
  wrappers {
      preBuildCleanup()
  }
  logRotator {
      numToKeep(1)
  }
  steps {
    def cmd = '''#!/bin/bash +x
    apt-get update
    apt-get install docker-ce docker-ce-cli containerd.io
    docker ps
    '''.stripIndent()

    shell(cmd)
  }
}
