# Capacities of the jugs and the target amount
JUG1_CAP = 3
JUG2_CAP = 5
TARGET = 1

# Set to keep track of visited states (jug1, jug2) to prevent infinite loops
visited = set()

def solve_dfs(j1, j2, path):
    # Base Case 1: If we found the target in either jug, print the path and stop
    if j1 == TARGET or j2 == TARGET:
        for state in path:
            print(state)
        return True

    # Base Case 2: If we already checked this water combination, skip it
    if (j1, j2) in visited:
        return False
    visited.add((j1, j2))

    # All possible next moves (Rules)
    moves = [
        (JUG1_CAP, j2),                 # Fill Jug 1
        (j1, JUG2_CAP),                 # Fill Jug 2
        (0, j2),                        # Empty Jug 1
        (j1, 0),                        # Empty Jug 2
        # Pour Jug 1 -> Jug 2
        (max(0, j1 - (JUG2_CAP - j2)), min(JUG2_CAP, j1 + j2)), 
        # Pour Jug 2 -> Jug 1
        (min(JUG1_CAP, j1 + j2), max(0, j2 - (JUG1_CAP - j1)))  
    ]

    # Recursively try each move (Depth-First exploration)
    for next_j1, next_j2 in moves:
        if solve_dfs(next_j1, next_j2, path + [(next_j1, next_j2)]):
            return True # Stop immediately if a deeper path succeeds
            
    return False

# Start the puzzle with both jugs empty (0, 0)
print("Step-by-step states (Jug1, Jug2):")
solve_dfs(0, 0, [(0, 0)])
