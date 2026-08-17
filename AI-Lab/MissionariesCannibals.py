visited = set()
step = 0

def dfs(m, c, boat):
    global step

    if not (0 <= m <= 3 and 0 <= c <= 3):
        return

    if (m > 0 and m < c) or (3-m > 0 and 3-m < 3-c):
        return

    state = (m, c, boat)
    if state in visited:
        return

    visited.add(state)
    step += 1

    print(f"Step {step}: Left={m}M {c}C | Right={3-m}M {3-c}C | Boat={'Left' if boat == 0 else 'Right'}")

    if m == 0 and c == 0:
        print("Goal Reached!")
        return

    moves = [(1,0), (2,0), (0,1), (0,2), (1,1)]

    for dm, dc in moves:
        if boat == 0:
            dfs(m-dm, c-dc, 1)
        else:
            dfs(m+dm, c+dc, 0)

dfs(3, 3, 0)