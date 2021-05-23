#   1
def win_str(s1,s2):
    sort1 = []
    sort2 = []
    for i in s1:
        sort1.append(ord(i))
    for i in s2:
        sort2.append(ord(i))
    sort1.sort()
    sort2.sort()
    l = len(sort1)
    for i in range(l):
        if sort2[i] < sort1[i]:
            return False
    return True


def find(s,current):
    l = 0
    last = current - 1
    pol = ''
    while (last != -1) and (current != len(s)):
        if s[last] == s[current]:
            pol = s[last] + pol + s[current]
            l += 1
        else:
            return l,pol
        last -= 1
        current +=1
    return l,pol

def findn(s,current):
    l = 0
    last = current - 1
    pol = s[current]
    current += 1
    while (last != -1) and (current != len(s)):
        if s[last] == s[current]:
            pol = s[last] + pol + s[current]
            l += 1
        else:
            if l != 0:
                return l,pol
            else:
                return l,''
        last -= 1
        current +=1
        l+=1
    return l,pol

#2
def polistr(s):
    l = 0
    pol = ''
    if len(s) == 1:
        return s
    for i in range(1,len(s)):
        now_l, now_pol = find(s,i)
        if now_l > l:
            l = now_l
            pol = now_pol
    for i in range(1,len(s)):
        now_l, now_pol = findn(s,i)
        if now_l > l:
            l = now_l
            pol = now_pol
    return pol

#3
def substr(s):
    l = len(s)/2
    counter = 0
    part_list = []
    for i in range(1,int(l)+1):
        st = s
        while len(st) > 1:
            if st[:i] == st[i:2*i]:
                if st[:i] in part_list:
                    pass
                else:
                    part_list.append(st[:i])
                    counter += 1
            st = st[1:]
    return counter

print(win_str("abc", "xya"))
print(polistr('babad'))
print(substr('abcabcabc'))