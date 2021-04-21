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

  --ways INDEX_TYPE  Type of index used to store ways
  <file>             Input file to use
```

## Build
```shell
sbt universal:packageZipTarball
```

## Performance

### immutable HashMap
```shell
time JAVA_OPTS="-Xms16g -Xmx40g" bin/scala-performance \
  --ways i_hashmap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json

________________________________________________________
Executed in  192.10 secs   fish           external 
   usr time  361.46 secs  777.00 micros  361.46 secs 
   sys time   18.18 secs  375.00 micros   18.18 secs 

Heap Memory:
  Reserved: 36.3G
  Used: 24.9G

System Memory (RAM): 28.0G 
```

### TreeMap
```shell
time JAVA_OPTS="-Xms16g -Xmx40g" bin/scala-performance \
  --ways i_treemap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json

________________________________________________________
Executed in  191.09 secs   fish           external 
   usr time  355.51 secs  618.00 micros  355.50 secs 
   sys time   18.55 secs  313.00 micros   18.55 secs 

Heap Memory:
  Reserved: 34.5G
  Used: 25.1G

System Memory (RAM): 28.7G
```

### mutable HashMap
```shell
time JAVA_OPTS="-Xms16g -Xmx40g" bin/scala-performance \
  --ways m_hashmap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json

________________________________________________________
Executed in  187.91 secs   fish           external 
   usr time  463.21 secs  609.00 micros  463.21 secs 
   sys time   16.56 secs  333.00 micros   16.56 secs 
   
Heap Memory:
  Reserved: 35.9G
  Used: 23.8G

System Memory (RAM): 27.9G
```

### mutable TreeMap
```shell
time JAVA_OPTS="-Xms16g -Xmx40g" bin/scala-performance \
  --ways m_treemap \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json

________________________________________________________
Executed in  199.06 secs   fish           external 
   usr time  382.74 secs  608.00 micros  382.74 secs 
   sys time   18.02 secs  325.00 micros   18.02 secs 
   
Heap Memory:
  Reserved: 36.0G
  Used: 25.5G

System Memory (RAM): 28.3G
```
