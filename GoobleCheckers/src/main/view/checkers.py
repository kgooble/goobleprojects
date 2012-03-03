'''
Created on 2011-11-20

@author: Gooble
'''

import main.game.gameplay
import main.view.slotting
from main.view.dimensions import DEFAULT_HEIGHT, DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_WIDTH

class Checkers(object):

    def __init__(self, ai=None):
        self.game = main.game.gameplay.GamePlay()
        self.slotting = main.view.slotting.Slotting(self.game)
        self.ai = ai
        
    def quit_game(self):
        self.done = True
        
    def new_game(self):
        self.game = main.game.gameplay.GamePlay()
        self.slotting = main.view.slotting.Slotting(self.game)
        
    def get_num_rows(self):
        return DEFAULT_ROWS
    
    def get_num_cols(self):
        return DEFAULT_COLS
    
    def get_tile_width(self):
        return DEFAULT_WIDTH / DEFAULT_COLS
    
    def get_tile_height(self):
        return DEFAULT_HEIGHT / DEFAULT_ROWS
    
    def get_piece(self, row, col):
        return self.game.get_piece(row, col)
    
    def is_holding_piece(self, row, col):
        return self.slotting.is_holding_piece(row, col)
    
    def is_computers_turn(self):
        return self.game.current_turn.is_computers_turn(self.ai)
    
    def select_piece(self, pos):
        self.slotting.select_piece(pos)
        
    def release_piece(self, pos):
        self.slotting.release_piece(pos)
        
    def make_ai_move(self):
        self.ai.make_move(self.game)
        
    def is_game_over(self):
        return self.game.is_game_over()
