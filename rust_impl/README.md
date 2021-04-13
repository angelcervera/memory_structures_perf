``text
PoC to test memory improvements using different structures to store the spacial data.

USAGE:
rust-performance <INPUT> --nodes <TYPE_INDEX> --ways <TYPE_INDEX>

FLAGS:
-h, --help       Prints help information
-V, --version    Prints version information

OPTIONS:
-n, --nodes <TYPE_INDEX>    Type of index used to store nodes [possible values: hashmap, btree]
-w, --ways <TYPE_INDEX>     Type of index used to store ways [possible values: hashmap, btree]

ARGS:
<INPUT>    Sets the input file to use
``

## Build
```bash
cargo build --release
```

## HashMap
```shell
time target/release/rust-performance \
  /home/angelcc/projects/geodatamarket/spain/lines/part-00000-61d4f5d2-56f3-45fc-984b-da04c098eba1-c000.json \
  -n hashmap \
  -w hashmap

Time:
Executed in   65.27 secs   fish           external 
   usr time   57.49 secs  561.00 micros   57.49 secs 
   sys time    7.72 secs  141.00 micros    7.72 secs 

Reading from disk: near 200MiB/s
Memory: 17.5GiB
```


- https://doc.rust-lang.org/cargo/commands/cargo-bench.html
- https://doc.rust-lang.org/beta/std/collections/index.html
