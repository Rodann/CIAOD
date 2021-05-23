import math
import numpy as np
import numpy.random as rand

#Треугольник с максимальным периметром
def triangle(nums):
    if(len(nums)<3):
        return 0
    last = 2
    middle = 1
    first = 0
    num_last = len(nums)-1
    num_middle = num_last-1
    num_first = num_middle-1
    sum=0
    while first<=num_first:
        if nums[last]+nums[first]+nums[middle] > sum:
            if (nums[last]<nums[first]+nums[middle]) and (nums[first]<nums[last]+nums[middle]) and (nums[middle]<nums[last]+nums[first]):
                #проверка условия что каждая сторона меньше суммы двух других
                sum = nums[last]+nums[first]+nums[middle]
        if last<num_last:
            last+=1
        else:
            if middle<num_middle:
                middle+=1
                last=middle+1
            else:
                first+=1
    return sum
#Максимальное число
def maxnum(nums):
    strums = []
    for i in nums:
        strums.append(str(i))
    result = []
    startlen = len(strums)
    while len(result)<startlen:
        max='0'
        for i in strums:
            if len(i)<len(max):
                if int(max)<int(i)*10**(len(max)-len(i)):
                    max=i
            elif len(i)>len(max):
                if int(i)>int(max)*10**(len(i)-len(max)):
                    max=i
            else:
                if int(i)>int(max):
                    max=i
        result.append(max)
        strums.remove(max)
    resstr=''
    for i in result:
        resstr=resstr+i
    return resstr


# выясняем размерность и если строк больше чем столбцов транспонируем
def diagonal(matrix, m, n):
    if (n >= m):
        return diagonalsort(matrix, m, n)
    else:
        m1 = matrix.transpose()
        m1 = diagonalsort(m1, n, m)
        return m1.transpose()


# сортировка по диагонали
def diagonalsort(matrix, m, n):
    k = abs(m - n)
    start_coll = 0
    flag = 0
    if (flag == 0):
        # cортируем середину
        while k >= 0:
            list_to_sort = []
            for i in range(m):
                list_to_sort.append(matrix[i][start_coll + i])
            sorted_list = np.sort(np.array(list_to_sort))
            for i in range(m):
                matrix[i][start_coll + i] = sorted_list[i]
            start_coll += 1
            k -= 1
        lines_col = 1
        # верхняя часть
        while start_coll < n:
            list_to_sort = []
            for i in range(m - lines_col):
                list_to_sort.append(matrix[i][start_coll + i])
            sorted_list = np.sort(np.array(list_to_sort))
            for i in range(m - lines_col):
                matrix[i][start_coll + i] = sorted_list[i]
            start_coll += 1
            lines_col += 1
        start_line = 1
        # нижняя часть
        while start_line < m:
            list_to_sort = []
            k = 0
            for i in range(start_line, m):
                list_to_sort.append(matrix[i][k])
                k += 1
            sorted_list = np.sort(np.array(list_to_sort))
            k = 0
            for i in range(start_line, m):
                matrix[i][k] = sorted_list[k]
                k += 1
            start_line += 1
    return matrix

print(triangle([3,6,2,3]))
print(maxnum([3,30,34,5,9]))
m = 2
n = 2
min_limit = 1
max_limit = 10
matrix = rand.randint(min_limit,max_limit,(m,n))
print(matrix)
print(diagonal(matrix,m,n))