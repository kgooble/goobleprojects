'''
Created on 2012-02-04

@author: Gooble
'''
from main.gameplay.board import Board
from main.gameplay.score import Score
from main.gameplay.speed import Speed

class Game(object):
    
    def __init__(self, board=Board()):
        self.board = board
        self.score = Score()
    
    def step(self):
        self.board.step()
        rows_cleared = self.board.get_rows_cleared()
        self.score.add_pts_for_lines(rows_cleared)
        
    def rotate(self):
        self.board.rotate()
        
    def move(self, direction):
        self.board.move(direction)
        
    def drop(self):
        self.board.drop_shape()
        
    def get_score(self):
        return self.score.get_value()
    
    def get_speed(self):
        return Speed().for_score(self.get_score())