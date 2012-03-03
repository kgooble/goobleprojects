'''
Created on 2011-11-19

@author: Gooble
'''

class PositionChange(object):
    
    def __init__(self, from_loc, to_loc):
        self.from_loc = from_loc
        self.to_loc = to_loc

    def is_jump(self):
        return abs(self.from_loc[0] - self.to_loc[0]) > 1 \
            and abs(self.from_loc[1] - self.to_loc[1]) > 1
            
    def get_jumped_piece(self):
        row_between = int((self.from_loc[0] + self.to_loc[0]) / 2)
        col_between = int((self.from_loc[1] + self.to_loc[1]) / 2)
        return row_between, col_between
    
    def is_backwards_version_of(self, from_loc, to_loc):
        return self.get_backwards_move() == PositionChange(from_loc, to_loc)
    
    def get_backwards_move(self):
        return PositionChange(self.to_loc, self.from_loc)
    
    def __str__(self):
        return "PositionChange from " + self.from_loc[0] + ", " + self.from_loc[1] + " to " \
            + str(self.to_loc[0]) + ", " + str(self.to_loc[1])
            
    def __eq__(self, other):
        if other is None or type(other) is not PositionChange: return False
        
        return self.from_loc == other.from_loc and self.to_loc == other.to_loc
    
    def __neq__(self, other):
        return not self==other
    
    def __repr__(self):
        return str(self.from_loc) + "->" + str(self.to_loc)