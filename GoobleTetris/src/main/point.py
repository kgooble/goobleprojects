'''
Created on 2012-01-29

@author: Gooble
'''


class Point(object):
    
    def __init__(self, row, col):
        self.row = row
        self.col = col
        
    def __eq__(self, other):
        return self.__dict__ == other.__dict__
    
    def __ne__(self, other):
        return not self == other
    
    def __repr__(self):
        return "(" + str(self.row) + ", " + str(self.col) + ")"
