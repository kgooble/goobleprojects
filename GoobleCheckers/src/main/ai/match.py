'''
Created on 2011-12-11

@author: Gooble
'''
from main.game import origin

class Match(object):
    
    def __init__(self, game, ai_top, ai_bottom):
        self.game = game
        self.ai_top = ai_top
        self.ai_bottom = ai_bottom
        
    def go(self):
        while not self.game.is_game_over():
            if self.game.current_turn.origin == origin.BOTTOM:
                self.ai_bottom.make_move(self.game)
            else:
                self.ai_top.make_move(self.game)