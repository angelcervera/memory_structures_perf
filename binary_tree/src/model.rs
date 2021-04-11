use std::collections::HashMap;
use std::fmt;
use std::rc::Rc;

/// (latitude, longitude) location
pub struct Location(pub f32, pub f32);

pub type Tags = HashMap<String, String>;

pub struct Node {
    pub id: u64,
    pub location: Location,
    pub tags: Tags,
}

pub struct Line {
    pub id: u64,
    pub nodes: Vec<Rc<Node>>,
    pub tags: Tags,
}

impl fmt::Display for Line {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(
            f,
            "[LINE, {}, [{}/{}] ({}/{})]",
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
        write!(
            f,
            "[NODE, {}, ({},{}), ({}/{})]",
            self.id,
            self.location.0,
            self.location.1,
            self.tags.len(),
            self.tags.capacity()
        )
    }
}
