def check_unvisited_node(unvisited):
    for u in unvisited:
        if u == 1:
            return True
    return False


def get_unvisited_node(unvisited):
    for index, node in enumerate(unvisited):
        if node == 1:
            return index
    return -1


def find_best_route(node_no, travel_route, min_distance):
    shortest_travel_route = travel_route[0]
    shortest_min_distance = min_distance.item(0)
    for start_node in range(0, node_no):
        if min_distance[start_node] < shortest_min_distance:
            shortest_min_distance = min_distance.item(start_node)
            shortest_travel_route = travel_route[start_node]

    print("min distance is: " + str(shortest_min_distance))
    print("travel route is: ")
    print(shortest_travel_route)

    return shortest_min_distance, shortest_travel_route


def in_travel_route(node, travel_route):
    for t in travel_route:
        if t == node:
            return True
    return False
