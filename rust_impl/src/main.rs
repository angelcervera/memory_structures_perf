use std::collections::{BTreeMap, HashMap};
use std::error::Error;
use std::fs::File;
use std::io::{self, BufRead};

use clap::{App, Arg, ArgMatches};

use crate::model::Line;
use crate::parser::*;

mod parser;
mod model;

fn get_arguments() -> ArgMatches<'static> {
    App::new("simplexpatial_rust_perf")
        .version("1.0")
        .about("PoC to test memory improvements using different structures to store the spacial data.")
        .author("Angel Cervera Claudio")
        .arg(
            Arg::with_name("nodes")
                .short("n")
                .long("nodes")
                .required(true)
                .takes_value(true)
                .value_name("TYPE_INDEX")
                .possible_values(&["hashmap", "btree"])
                .help("Type of index used to store nodes")
        )
        .arg(
            Arg::with_name("ways")
                .short("w")
                .long("ways")
                .required(true)
                .takes_value(true)
                .value_name("TYPE_INDEX")
                .possible_values(&["hashmap", "btree"])
                .help("Type of index used to store ways")
        )
        .arg(
            Arg::with_name("INPUT")
                .help("Sets the input file to use")
                .required(true)
                .index(1)
        )
        .get_matches()
}

fn main() -> Result<(), Box<dyn Error>> {
    let args = get_arguments();

    // let mut nodes: BTreeMap<u64, Line> = BTreeMap::new();
    let mut line_map: HashMap<u64, Line> = HashMap::new();

    let file = File::open(args.value_of("INPUT").unwrap())?;
    let lines = io::BufReader::new(file).lines();
    for line in lines {
        if let Ok(json) = line {
            let line = JsonLine::parse(&json);
            line_map.insert(line.id, line);
        }
    }

    println!("Indexed!");
    println!("{}", line_map.len());
    Ok(())
}
