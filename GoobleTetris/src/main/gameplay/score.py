'''
Created on 2012-02-04

@author: Gooble
'''
SCORE_PER_LINE = 100

class Score(object):
    
    def __init__(self):
        self.value = 0 
        self.tetris = False

    def add_pts_for_lines(self, num_lines):
        self.value += num_lines * SCORE_PER_LINE
        self._add_bonus_for_tetris(num_lines)
        
    def get_value(self):
        return self.value

    def _add_bonus_for_double_tetris(self, num_lines):
        if self.tetris:
            self.value += num_lines * SCORE_PER_LINE
        self.tetris = not self.tetris


    def _add_bonus_for_tetris(self, num_lines):
        if num_lines == 4:
            self.value += num_lines * SCORE_PER_LINE
            self._add_bonus_for_double_tetris(num_lines)