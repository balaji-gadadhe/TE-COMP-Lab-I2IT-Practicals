visited = set()
step = 0

def dfs(state):
    global step

    if state in visited:
        return

    visited.add(state)
    step += 1

    print(f"Step {step}: State={state}")

    if state == "ABC":
        print("Goal Reached!")
        return

    for i in range(3):
        for j in range(i + 1, 3):
            a = list(state)
            a[i], a[j] = a[j], a[i]
            dfs("".join(a))

dfs("CBA")
