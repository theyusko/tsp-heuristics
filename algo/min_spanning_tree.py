from helper import util
import numpy as np
import sys


def algorithm():
    # Read the first line for node number
    node_no = 4
    graph = [[-1, 411, 44, 19],
             [411, -1, 0, 21],
             [44, 0, -1, 0],
             [19, 21, 0, -1]]

    min_distance = np.zeros((node_no,), dtype=float)  # distances with starting node as min_distance[i]
    travel_route = [[] for y in range(0, node_no)]
    parents = [[0 for x in range(0, node_no)] for y in range(0, node_no)]

    # Step 1
    print("Prim's mst:")
    for start_node in range(0, node_no):
        parents[start_node] = prims_algorithm(start_node, node_no, graph)
    
    # Assume triangle inequality holds for nodes, if it doesn't, min spanning tree doesnt give 
    # a solution closer to optimal
    
    # For each mst with a start_node
    for start_node, parent in enumerate(parents):
        print("\nStartnode:" + str(start_node))
        print()
        travel_route[start_node].append(start_node)
        
        # For each node in a specific mst, find the travel route
        index = 1
        while index < node_no:
            list = []
            for node, parent_node in enumerate(parent):
                if util.in_travel_route(parent_node, travel_route[start_node]) and not util.in_travel_route(node, travel_route[start_node]):
                    list.append(node)
                    index = index + 1
            for l in list:
                travel_route[start_node].append(l)

        # Find distance of travel route
        prev_node = start_node
        cur_node = -1
        for i in range(1, node_no):
            cur_node = travel_route[start_node][i]
            if graph[prev_node][cur_node] <= 0:
                min_distance[start_node] = float('inf')
            else:
                min_distance[start_node] = min_distance[start_node] + graph[prev_node][cur_node]
            print("from " + str(prev_node) + " to " + str(cur_node) +" distance: " + str(graph[prev_node][cur_node]))
            prev_node = cur_node

        if graph[cur_node][start_node] <= 0:
            min_distance[start_node] = float('inf')
        else:
            min_distance[start_node] = min_distance[start_node] + graph[cur_node][start_node]

    print("Prim's heuristic:")
    [shortest_min_distance, shortest_travel_route] = util.find_best_route(node_no, travel_route, min_distance)

    return shortest_min_distance, shortest_travel_route


def prims_algorithm(start_node, node_no, graph):
    keys = [float('inf') for x in range(0, node_no)]
    parent = [-1 for x in range(0, node_no)]

    # Step 3
    unvisited = np.ones((node_no,), dtype=int)  # all nodes are unvisited
    keys[start_node] = 0

    # Step 2
    iteration = 1
    while util.check_unvisited_node(unvisited) and iteration < node_no:
        # Find the unvisited node with minimum key
        min_key_val = sys.maxsize
        min_node = node_no
        for index, key_val in enumerate(keys):
            if unvisited[index] == 1 and key_val < min_key_val:
                min_key_val = key_val
                min_node = index

        unvisited[min_node] = 0

        # Update adjacent nodes
        for node, val in enumerate(keys):
            if keys[node] > graph[min_node][node] > 0 and unvisited[node] == 1:
                keys[node] = graph[start_node][node]
                parent[node] = min_node

        iteration = iteration + 1

    print_mst(parent, node_no, graph)

    return parent


def print_mst(parent, node_no, graph):
    print("Edge \tWeight")
    for i in range(0, node_no):
        print(parent[i], "-", i, "\t", graph[i][parent[i]])


if __name__ == '__main__':
    algorithm()
