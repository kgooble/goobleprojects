'''
Created on 2011-08-21

@author: Gooble
'''

class DataStorage(object):

    data = {}

    def __init__(self):
        pass
        
    def add_data(self, key, value):
        self.data[key] = value
        