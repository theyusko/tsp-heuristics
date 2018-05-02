# Step 1. Pick any starting node
# Step 2. Look at all the arcs coming out of the starting node that have not been visited and
# choose the next closest node.
# Step 3. Repeat the process until all the nodes have been visited at least once.
# Step 4. Check and see if all nodes are visited. If so return to the starting point which gives us a
# tour.
# Step 5. Draw and write down the tour, and calculate the distance of the tour.

from helper import util
import numpy as np
import math


def algorithm():
    # Read the first line for node number
    node_no = 8
    graph = [[-1, 0, 302, 595, 0, 598, 215, 378],
             [0, -1, 643, 867, 258, 849, 0, 0],
             [302, 643, -1, 745, 314, 293, 131, 36],
             [595, 867, 745, -1, 0, 622, 623, 947],
             [0, 258, 314, 0, -1, 632, 827, 616],
             [598, 849, 293, 622, 632, -1, 538, 272],
             [215, 0, 131, 623, 827, 538, -1, 987],
             [378, 0, 36, 947, 616, 272, 987, -1]]

    min_distance = np.zeros((node_no,), dtype=float)  # distances with starting node as min_distance[i]
    travel_route = [[0 for x in range(0, node_no)] for y in range(0, node_no)]

    # Step 1
    for start_node in range(0, node_no):
        # Step 3
        unvisited = np.ones((node_no,), dtype=int)  # all nodes are unvisited
        unvisited[start_node] = 0
        travel_route[start_node][0] = start_node  # travel route starts with start_node

        node = start_node
        iteration = 1
        while util.check_unvisited_node(unvisited) and iteration < node_no:
            # Step 2
            closest_arc = float('inf')
            closest_node = node_no

            for node2 in range(0, node_no):
                if unvisited[node2] == 1 and 0 < graph[node][node2] < closest_arc:
                    closest_arc = graph[node][node2]
                    closest_node = node2

            if closest_node >= node_no:
                # print("Error: Argument is not complete graph " +
                #      "(No arc exists from a given node to another while there exists unvisited node(s))" +
                #      " while starting node is " + str(start_node) + " and current node is " + str(node))
                # print("travel route is: ")
                # print(travel_route[start_node])
                min_distance[start_node] = float('inf')
                break

            node = closest_node
            unvisited[node] = 0
            min_distance[start_node] = min_distance[start_node] + closest_arc
            # print(min_distance[start_node])
            travel_route[start_node][iteration] = node
            iteration = iteration + 1

        if not math.isinf(min_distance[start_node]):
            last_visited = travel_route[start_node][node_no-1]
            if graph[last_visited][start_node] > 0:
                min_distance[start_node] = min_distance[start_node] + graph[last_visited][start_node]
            else:
                min_distance[start_node] = float('inf')

        # There shouldn't be any unvisited node left
        # if check_unvisited_node(unvisited):
        #    print("Error: Argument is not complete graph (Unvisited node exists)")
        #    return

        # There is no unvisited node left
        print("min distance is: " + str(min_distance[start_node]))
        print(travel_route[start_node])
        print()

    print("Nearest Neighbor heuristic:")
    [shortest_min_distance, shortest_travel_route] = util.find_best_route(node_no, travel_route, min_distance)

    return shortest_min_distance, shortest_travel_route


if __name__ == '__main__':
    algorithm()