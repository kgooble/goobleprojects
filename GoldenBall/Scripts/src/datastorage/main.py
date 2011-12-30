'''
Created on 2011-08-21

@author: Gooble
'''

from datastorage.pickler import Pickler
from datastorage.data import DataStorage

def get_input():
    return input(">>>")


if __name__ == '__main__':
#    pickler = Pickler()
#    x = pickler.load()
#    print (x)
    x = get_input()
    datastorer = DataStorage()
    while (x != 'q'):
        x = get_input()
        stuff = x.split()
        if len(stuff) > 1:
            datastorer.add_data(stuff[0], stuff[1])
        
    print (datastorer.data)
    pickler = Pickler()
    pickler.save(datastorer)
    