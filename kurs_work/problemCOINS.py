import random
random_list = []
for i in range(501):
    random_list.append(random.randint(1, 1000))

def coins(piles):
    piles.sort()
    sum = 0
    while len(piles) > 0:
        piles = piles[1:len(piles)-1]
        sum += piles[len(piles)-1]
        piles = piles[:len(piles)-1]
    return sum
print(random_list)
print(coins(random_list))
