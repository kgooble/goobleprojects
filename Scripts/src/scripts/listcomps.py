'''
Created on 2011-08-19

@author: Gooble
'''

def its_true(i):
    return i % 3 == 0

def print_some_cakes(i):
    print("cakes!" + str(i))

if __name__ == '__main__':
    print([i for i in range(5)])
    [print_some_cakes(i) for i in range(10) if its_true(i)]