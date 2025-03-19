from helper import draw_coloured_map, generate_graph_map
from graph_colouring import greedy
import time, json

if __name__ == "__main__":
    #n = 64
    #map = generate_graph_map(n)
    size = 4
    times = []

    while size <= 65536:
        with open('sols/g' + str(size) + '.json') as f:
            map = json.load(f)
            f.close()
        start_time = time.time()
        solution = greedy(map["graph"])
        times.append([size, int((time.time() - start_time)*1000)])
        size *= 2
    print("--- TIMES ---")
    for i in times:
        print("--- %s nodes -> %s ms ---" % (i[0],i[1]))