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