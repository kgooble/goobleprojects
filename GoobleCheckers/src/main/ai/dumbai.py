'''
Created on 2011-12-11

@author: Gooble
'''
from main.ai.abstractai import AbstractAI

class DumbAI(AbstractAI):
    
    def choose_move(self, game, all_moves):
        if len(all_moves) > 0:
            return all_moves[0][0]
