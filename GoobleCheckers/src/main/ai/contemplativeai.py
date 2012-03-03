'''
Created on 2011-12-22

@author: Gooble
'''
from main.ai.abstractai import AbstractAI
from main.ai.bestmovedepth import BestMoveWithDepth
from main.game import origin

class ContemplativeAI(AbstractAI):
    
    def __init__(self, max_depth, origin=origin.BOTTOM):
        AbstractAI.__init__(self, origin)
        self.max_depth = max_depth

    def choose_move(self, game, all_moves):
        return BestMoveWithDepth(self.max_depth, self.origin).calculate_for(game)
