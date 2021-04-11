use std::collections::HashMap;
use std::rc::Rc;

use serde::Deserialize;

use crate::model::{Line, Location, Node};

#[derive(Deserialize, Debug)]
struct JsonNode {
    id: u64,
    latitude: f32,
    longitude: f32,
    tags: HashMap<String, String>,
}

#[derive(Deserialize, Debug)]
pub(crate) struct JsonLine {
    id: u64,
    nodes: Vec<JsonNode>,
    tags: HashMap<String, String>,
}

impl JsonLine {
    pub fn parse(line: &str) -> Line {
        let des: JsonLine = serde_json::from_str(line).unwrap();
        Line {
            id: des.id,
            tags: des.tags,
            nodes: des
                .nodes
                .iter()
                .map(|json| {
                    Rc::new(Node {
                        id: json.id,
                        location: Location(json.latitude, json.longitude),
                        tags: json.tags.clone(),
                    })
                })
                .collect(),
        }
    }

}

#[cfg(test)]
mod tests {
    use std::fs;

    use crate::parser::JsonLine;

    #[test]
    fn it_works() {
        let json_example = fs::read_to_string("resources/test/ex_1.json").unwrap();
        let line = JsonLine::parse(&json_example);
        assert_eq!(3, line.tags.len());
        assert_eq!(4, line.nodes.len());
        assert_eq!(2, line.nodes[0].tags.len());
        assert_eq!(0, line.nodes[1].tags.len());
    }
}
