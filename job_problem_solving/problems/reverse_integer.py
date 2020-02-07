'''
Given a 32-bit signed integer, reverse digits of an integer.
'''

import math


def reverse_integer(x):
    pmax = math.pow(2, 31) -1
    nmax = -1 * math.pow(2, 31)

    negative = False
    if x < 0:
        negative = True
        x = x * -1

    reverse = 0
    while x > 0:
        reverse = (reverse * 10) + x % 10
        x = int(x/10)

    if negative:
        reverse = -1 * reverse

    if reverse > pmax or reverse < nmax:
        return 0

    return reverse


test_case = [0, 121, -654, 234, -234, 100, -100, 1534236469]
for i in test_case:
    print (i, reverse_integer(i))