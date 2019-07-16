job("docker-nginx-build") {
  wrappers {
      preBuildCleanup()
  }
  logRotator {
      numToKeep(1)
  }
  steps {
    def cmd = '''#!/bin/bash +x
    sudo apt-get update
    sudo apt-get install docker-ce docker-ce-cli containerd.io
    docker ps
    '''.stripIndent()

    shell(cmd)
  }
}
