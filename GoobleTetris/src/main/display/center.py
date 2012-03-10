'''
Created on 2012-03-04

@author: Gooble
'''

class Center(object):
    def __init__(self, start=0, end=0, total_length=0):
        self.start = start
        if total_length == 0:
            self.total_length = end - start
        else:
            self.total_length = total_length
        
    def object_with_length(self, length):
        abs_start = int((self.total_length - length) / 2)
        return abs_start + self.start