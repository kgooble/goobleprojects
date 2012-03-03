'''
Created on 2011-12-08

@author: Gooble
'''
from main.ai.abstractai import AbstractAI
import main.ai.maxmove as maxmove

class EasyAI(AbstractAI):

    def choose_move(self, game, all_moves):
        return maxmove.MaxMove().get_max_move(all_moves)