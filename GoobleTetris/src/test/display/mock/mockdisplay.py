'''
Created on 2012-03-04

@author: Gooble
'''

class MockDisplay(object):
    
    def __init__(self, game):
        self.game = game
        
    def display(self, screen):
        self.displayed = True