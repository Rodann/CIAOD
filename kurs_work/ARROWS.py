from itertools import *


def arrows(bubbles):
    counter = 0
    while len(bubbles) > 0:
        max_count = 0
        max_pop = []
        for i in permutations(bubbles):
            a = i[0][0]
            b = i[0][1]
            local_counter = 0
            to_pop = []
            pop_counter = 0
            for j in i:
                if (j[0] >= a and j[0] <= b) or (j[1] >= a and j[1] <= b):
                    a = max(a,j[0])
                    b = min(b, j[1])
                    local_counter += 1
                    to_pop.append(j)
                pop_counter += 1
            if local_counter > max_count:
                max_count = local_counter
                max_pop = to_pop
            elif local_counter == max_count:
                b = []
                for q in bubbles:
                    b.append(q)
                for i in max_pop:
                    b.remove(i)
                a1 = arrows(b)
                c1 = max_count + a1
                b = []
                for q in bubbles:
                    b.append(q)
                for i in to_pop:
                    b.remove(i)
                a2 = arrows(b)
                c2 = local_counter + a2
                if c1 < c2:
                    pass
                elif c1 == c2:
                    max_count = local_counter
                    max_pop = to_pop
                else:
                    max_count = local_counter
                    max_pop = to_pop
        print(max_pop, bubbles)
        for i in max_pop:
            bubbles.remove(i)
        counter += 1
    return counter

#print(arrows([[10,16],[2,8],[1,6],[7,12]]))
print(arrows([[2,3],[2,3]]))