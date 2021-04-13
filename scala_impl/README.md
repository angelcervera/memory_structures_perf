```shell
$ java -version                                                                                                   (base) 
openjdk version "1.8.0_282"
OpenJDK Runtime Environment (build 1.8.0_282-8u282-b08-0ubuntu1~20.10-b08)
OpenJDK 64-Bit Server VM (build 25.282-b08, mixed mode)
```

## Usage
```text
Scala performance using HashMap and BTreeMap 0.0.1-SNAPSHOT
Usage: scala-performance-<version>.jar [options] <file>

  --nodes INDEX_TYPE  Type of index used to store nodes
  --ways INDEX_TYPE   Type of index used to store ways
  <file>              Input file to use
```

## Build
```shell
sbt universal:packageZipTarball
```

## Run
Uncompress the tgz file generated inside `target/universal`
```shell
$ time bin/scala-performance \
  --nodes hashmap \
  --ways hashmap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json
  
```

