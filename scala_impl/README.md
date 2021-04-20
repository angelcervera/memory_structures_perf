```shell
$ java -version                                                                                                   (base) 
openjdk version "1.8.0_282"
OpenJDK Runtime Environment (build 1.8.0_282-8u282-b08-0ubuntu1~20.10-b08)
OpenJDK 64-Bit Server VM (build 25.282-b08, mixed mode)
```

## Usage
Uncompress the tgz file generated inside `target/universal`

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

## Performance

> :alert: At the moment, only indexing lines.

### nodes immutable HashMap / ways immutable HashMap
```shell
$ time JAVA_OPTS="-Xms25g -Xmx60g" bin/scala-performance \
  --nodes i_hashmap \
  --ways i_hashmap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json

________________________________________________________
Executed in  185.72 secs   fish           external 
   usr time  307.01 secs  694.00 micros  307.01 secs 
   sys time   20.84 secs  285.00 micros   20.84 secs 

Memory: 29GiB
```


### nodes immutable TreeMap / ways immutable TreeMap
```shell
time JAVA_OPTS="-Xms25g -Xmx60g" bin/scala-performance \
  --nodes i_treemap \
  --ways i_treemap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json
  
________________________________________________________
Executed in  186.97 secs   fish           external 
   usr time  311.36 secs   32.68 millis  311.33 secs 
   sys time   19.79 secs   21.63 millis   19.76 secs 

Memory: 25GiB
```

### nodes mutable HashMap / ways mutable HashMap
```shell
$ time JAVA_OPTS="-Xms25g -Xmx60g" bin/scala-performance \
  --nodes m_hashmap \
  --ways m_hashmap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json

```

### nodes mutable TreeMap / ways mutable TreeMap
```shell
time JAVA_OPTS="-Xms25g -Xmx60g" bin/scala-performance \
  --nodes m_treemap \
  --ways m_treemap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json

```

