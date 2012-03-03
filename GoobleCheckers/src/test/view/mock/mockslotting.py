'''
Created on 2012-02-26

@author: Gooble
'''

class MockSlotting(object):
    def __init__(self, row=None, col=None):
        self.row = row
        self.col = col
    
    def is_holding_piece(self, row, col):
        return row == self.row and col == self.col