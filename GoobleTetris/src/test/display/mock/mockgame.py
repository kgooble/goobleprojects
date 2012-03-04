'''
Created on 2012-03-04

@author: Gooble
'''
from main.movement.direction import LEFT, RIGHT

class MockGame(object):
    def __init__(self):
        self.position = 0
        self.gameover = False
        
    def move(self, direction):
        if direction == LEFT:
            self.position = -1
        if direction == RIGHT:
            self.position = 1
            
    def speed_up(self):
        self.goingfast = True
        
    def slow_down(self):
        self.goingfast = False
        
    def rotate(self):
        self.rotated = True
        
    def drop(self):
        self.dropped = True
        
    def set_game_over(self, gameover):
        self.gameover = True
        
        
    def is_game_over(self):
        return self.gameover