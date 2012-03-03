'''
Created on 2012-02-26

@author: Gooble
'''

class GameMode(object):
    
    def __init__(self, difficulty="Medium"):
        self.difficulty = difficulty
        
    def get_mode(self):
        return self.difficulty
    
    def set_difficulty(self, diff):
        self.difficulty = diff