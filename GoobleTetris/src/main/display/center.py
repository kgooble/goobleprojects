'''
Created on 2012-03-04

@author: Gooble
'''

class Center(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end
        
    def objectWithLength(self, length):
        abs_start = int((self.end - self.start - length) / 2)
        return abs_start + self.start