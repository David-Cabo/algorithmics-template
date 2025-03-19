import json

from helper import draw_coloured_map, generate_graph_map

def greedy(map):
    print(map)

    colors = ["red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta", "lime"]
    node_colours = {}
    for node in map.keys():
        node_colours[node] = "red"
    for node in map.keys():
        possible_colors = colors.copy()
        for neigh in map[node]:
            print("node = " + node + ", neighbor = " + str(neigh) + ", node_colours[node] = " + node_colours[node])
            neigh_color = node_colours[str(neigh)]
            if neigh_color in possible_colors:
                possible_colors.remove(neigh_color)
        node_colours[node] = possible_colors[0]

    return node_colours



if __name__ == "__main__":
    n = 64
    map = generate_graph_map(n)
    """
    with open('sols/g64.json') as f:
        map = json.load(f)
        f.close()
    """
    solution = greedy(map["graph"])

    if solution:
        print("Solution found:", solution)
        draw_coloured_map(map, solution)
        with open('solution.json', 'w') as f:
            json.dump(solution, f)
            f.close()
    else:
        print("Solution not found.")