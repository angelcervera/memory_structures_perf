// https://doc.rust-lang.org/beta/std/collections/index.html

use std::collections::HashMap;
use std::fmt;
use std::iter::FromIterator;
use std::rc::Rc;

/// (latitude, longitude) location
struct Location(f32, f32);

type Tags = HashMap<String, String>;

struct Node {
    id: u64,
    location: Location,
    tags: Tags,
}

struct Line {
    id: u64,
    nodes: Vec<Rc<Node>>,
    tags: Tags,
}

impl fmt::Display for Line {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "[LINE, {}, [{}/{}] ({}/{})]",
               self.id,
               self.nodes.len(),
               self.nodes.capacity(),
               self.tags.len(),
               self.tags.capacity()
        )
    }
}

impl fmt::Display for Node {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "[NODE, {}, ({},{}), ({}/{})]",
               self.id,
               self.location.0,
               self.location.1,
               self.tags.len(),
               self.tags.capacity()
        )
    }
}

fn main() {
    let node1 = Rc::new(Node {
        id: 10,
        location: Location(0.5, 0.6),
        tags: HashMap::<String, String>::from_iter(vec![
            ("x".to_string(), "a".to_string()),
            ("y".to_string(), "b".to_string())
        ]),
    });

    let node2 = Rc::new(Node {
        id: 20,
        location: Location(1.5, 1.6),
        tags: HashMap::<String, String>::from_iter(vec![
            ("x".to_string(), "1".to_string()),
            ("y".to_string(), "4".to_string())
        ]),
    });

    let line1 = Line {
        id: 100,
        nodes: vec![Rc::clone(&node1), Rc::clone(&node2)],
        tags: HashMap::<String, String>::from_iter(vec![
            ("xx".to_string(), "1".to_string()),
            ("yy".to_string(), "4".to_string())
        ]),
    };

    let line2 = Line {
        id: 102,
        nodes: vec![Rc::clone(&node1), Rc::clone(&node2)],
        tags: HashMap::<String, String>::from_iter(vec![
            ("xx".to_string(), "1".to_string()),
            ("yy".to_string(), "4".to_string())
        ]),
    };

    println!("{}, {}", node1, node2);
    println!("{}, {}", line1, line2);
}

