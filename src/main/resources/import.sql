INSERT INTO known_fruits(id, name) VALUES (1, 'Cherry');
INSERT INTO known_fruits(id, name) VALUES (2, 'Apple');
INSERT INTO known_fruits(id, name) VALUES (3, 'Banana');


INSERT INTO whiteboard(id, name, businessId) VALUES (1, 'Brainstorming 12', 'brainstorming_12');
INSERT INTO whiteboard(id, name, businessId) VALUES (2, 'Brainstorming 242', 'brainstorming_242');

INSERT INTO note(id, text, x, y, color, whiteboard_id) VALUES (1, 'My idea', 1,2, 'green', 1);
INSERT INTO note(id, text, x, y, color, whiteboard_id) VALUES (2, 'Another idea', 10,20, 'red', 2);