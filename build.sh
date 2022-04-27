# Clear up
function clearup {
  rm -rf org cn ndosplugin mf.txt META-INF version.properties
}

function uncompressLibs {
  unzip -qq -o ./js-engine-core.jar -d ./
}

function build {
  javac -d . -encoding UTF-8 -cp js-engine-core.jar:../Nameless-DOS/ndos.jar src/src/*.java

  if [ $? != 0 ]
  then
    echo "编译错误!"
    exit -1
  fi
}

function compress {
  jar -cf jsrunner.jar ./cn ./org ./com ./plugin_meta
}

# Execute
clearup
uncompressLibs
build
compress
clearup
