'''
Created on 2011-12-11

@author: Gooble
'''
from main.ai.abstractai import AbstractAI
import random

class RandomAI(AbstractAI):
    
    def choose_move(self, game, all_moves):
        random_move = random.Random().randint(0, len(all_moves) - 1)
        return all_moves[random_move]        