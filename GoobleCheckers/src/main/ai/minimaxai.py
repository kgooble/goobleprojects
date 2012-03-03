'''
Created on 2011-12-26

@author: Gooble
'''
from main.ai.abstractai import AbstractAI
from main.game import origin
from main.ai.minimax import MinimaxMove

class MinimaxAI(AbstractAI):

    def __init__(self, max_depth, origin=origin.BOTTOM):
        AbstractAI.__init__(self, origin)
        self.max_depth = max_depth
        
    def choose_move(self, game, all_moves):
        return MinimaxMove(self.max_depth, self.origin).calculate_for(game)