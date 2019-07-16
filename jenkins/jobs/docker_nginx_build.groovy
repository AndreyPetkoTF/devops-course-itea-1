job("docker-nginx-build") {
  wrappers {
      preBuildCleanup()
  }
  logRotator {
      numToKeep(1)
  }
  steps {
    def cmd = '''#!/bin/bash +x
    ls
    '''.stripIndent()

    shell(cmd)
  }
}
