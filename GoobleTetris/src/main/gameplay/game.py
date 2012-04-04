'''
Created on 2012-02-04

@author: Gooble
'''
from main.gameplay.board import Board
from main.gameplay.score import Score
from main.gameplay.speed import Speed

class Game(object):
    
    def __init__(self, board=None, level=None):
        if board is None:
            board = Board()
        self.board = board
        self.score = Score()
        if level is None:
            level = 1
        self.level = level
        self.speed_modifier = 1
    
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
        return int(Speed().for_start_level_and_curr_score(self.level, self.score.get_value()) / self.speed_modifier)
    
    def get_pieces(self):
        return self.board.get_pieces()
    
    def is_game_over(self):
        return self.board.is_game_over()
    
    def speed_up(self):
        self.speed_modifier = 1.5
        
    def slow_down(self):
        self.speed_modifier = 1
        
    def get_next_shape(self):
        return self.board.get_next_shape()
    
    def get_current_shape(self):
        return self.board.cur_shape
    
    def get_predicted_points(self):
        return self.board.get_predicted_points()