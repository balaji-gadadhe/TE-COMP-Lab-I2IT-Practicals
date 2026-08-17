# Simple Reflex Agent - Vacuum Cleaner

def vacuum_agent(location, status):
    if status == "Dirty":
        return "Suck"
    elif location == "A":
        return "Move Right"
    else:
        return "Move Left"


# Initial environment
rooms = {
    "A": "Dirty",
    "B": "Dirty"
}

location = "A"

print("Vacuum Cleaner World")
print("--------------------")

for step in range(1, 5):

    status = rooms[location]
    action = vacuum_agent(location, status)

    if action == "Suck":
        rooms[location] = "Clean"

    elif action == "Move Right":
        location = "B"

    else:
        location = "A"

    print(f"Step {step}: Location={location}, "
          f"A={rooms['A']}, B={rooms['B']}, Action={action}")

print("--------------------")
print("Final: A =", rooms["A"], ", B =", rooms["B"])