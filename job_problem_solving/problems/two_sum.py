'''
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.
'''


def two_sum(nums, target):
    num_dict = dict()
    for i in range(len(nums)):
        complimentary = target - nums[i]
        if complimentary in num_dict:
            return [i, num_dict[complimentary]]
        else:
            num_dict[nums[i]] = i
    return None


res = two_sum([3,3], 6)
print (res)
