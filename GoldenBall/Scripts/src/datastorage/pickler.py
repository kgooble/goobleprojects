'''
Created on 2011-08-21

@author: Gooble
'''

import pickle

class Pickler(object):
    
    def __init__(self):
        pass
    
    def save(self, obj):
        f = open('testpickle', 'wb')
        pickle.Pickler(f).dump(obj)
    
    def load(self):
        f = open('testpickle', 'rb')
        return pickle.Unpickler(f).load().__init__()
    
    