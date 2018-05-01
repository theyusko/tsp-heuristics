# Step 1. Pick any starting node
# Step 2. Look at all the arcs coming out of the starting node that have not been visited and
# choose the next closest node.
# Step 3. Repeat the process until all the nodes have been visited at least once.
# Step 4. Check and see if all nodes are visited. If so return to the starting point which gives us a
# tour.
# Step 5. Draw and write down the tour, and calculate the distance of the tour.

import numpy as np


def algorithm():
    # Read the first line for node number
    node_no = 10
    cities = [[-1, 315011971, 1946908451, 63145644, 45460322, 0, 422396217, 2121472905, 395423511, 742752790],
              [315011971, -1, 1758068920, 1120861991, 910840094, 1628732704, 0, 679900370, 1881826564, 805039021],
              [1946908451, 1758068920, -1, 0, 1069416540, 0, 1066155702, 286629233, 885178146, 84327624],
              [63145644, 1120861991, 0, -1, 1505146634, 409381804, 979486790, 1706448539, 1293557175, 0],
              [45460322, 910840094, 1069416540, 1505146634, -1, 292917507, 1527507816, 407369854, 1799861331, 1910893808],
              [0, 1628732704, 0, 409381804, 292917507, -1, 1512116143, 0, 688441910, 2062551722],
              [422396217, 0, 1066155702, 979486790, 1527507816, 1512116143, -1, 894190745, 273931139, 2057830403],
              [2121472905, 679900370, 286629233, 1706448539, 407369854, 0, 894190745, -1, 0, 27991223],
              [395423511, 1881826564, 885178146, 1293557175, 1799861331, 688441910, 273931139, 0, -1, 502309305],
              [742752790, 805039021, 84327624, 0, 1910893808, 2062551722, 2057830403, 27991223, 502309305, -1]]
    min_distance = np.zeros((node_no,), dtype=int)  # distances with starting node as min_distance[i]
    travel_route = [[0 for x in range(0, node_no)] for y in range(0, node_no)]

    # Step 1
    for start_node in range(0, node_no):
        # Step 3
        unvisited = np.ones((node_no,), dtype=int)  # all nodes are unvisited
        unvisited[start_node] = 0
        travel_route[start_node][0] = start_node  # travel route starts with start_node

        node = start_node
        iteration = 1
        while check_unvisited_node(unvisited) or iteration < node_no:
            # Step 2
            closest_arc = float('inf')
            closest_node = node_no

            for node2 in range(0, node_no):
                if unvisited[node2] == 1 and 0 < cities[node][node2] < closest_arc:
                    closest_arc = cities[node][node2]
                    closest_node = node2

            if closest_node >= node_no:

                print("Error: Argument is not complete graph " +
                      "(No arc exists from a given node to another while there exists unvisited node(s))" +
                      " while starting node is " + str(start_node) + " and current node is " + str(node))
                print("travel route is: ")
                print(travel_route[start_node])

                return

            node = closest_node
            unvisited[node] = 0
            min_distance[node] = min_distance[node] + closest_arc
            print(min_distance[node])
            travel_route[start_node][iteration] = node
            iteration = iteration + 1

        # There shouldn't be any unvisited node left
        if check_unvisited_node(unvisited):
            print("Error: Argument is not complete graph (Unvisited node exists)")
            return

        # There is no unvisited node left

    shortest_travel_route = travel_route[0]
    shortest_min_distance = min_distance.item(0)
    for start_node in range(0, node_no):
        if min_distance[start_node] < shortest_min_distance:
            shortest_min_distance = min_distance.item(start_node)
            shortest_travel_route = travel_route[start_node]

    print()
    print("min distance is: " + str(shortest_min_distance))
    print("travel route is: ")
    print(shortest_travel_route)

    return shortest_min_distance, shortest_travel_route


def check_unvisited_node(unvisited):
    for u in unvisited:
        if u == 1:
            return True
    return False


if __name__ == '__main__':
    algorithm()