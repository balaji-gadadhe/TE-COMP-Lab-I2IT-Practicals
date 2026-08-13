maze = [
    [0, 1, 0, 0, 0],
    [0, 1, 0, 1, 0],
    [0, 0, 0, 1, 0],
    [0, 0, 0, 1, 0],
    [1, 1, 1, 0, 0]
]

rows = len(maze)
cols = len(maze[0])

start = (0, 0)
end = (4, 4)

visited = []
path = []

def dfs(r, c):
    # Check invalid position
    if r < 0 or r >= rows or c < 0 or c >= cols:
        return False

    if maze[r][c]==1 or (r,c) in visited:
        return False
    
    visited.append((r, c))
    path.append((r, c))
    
    if (r, c) == end:
        return True
    
    if (dfs(r + 1, c) or
        dfs(r, c + 1) or
        dfs(r - 1, c) or
        dfs(r, c - 1)):
        return True

    # Backtrack
    path.pop()
    return False


if dfs(start[0], start[1]):
    print("Path found:")
    for i in range (0,len(path)):
        print(path[i])
    
else:
    print("No path found")