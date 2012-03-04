'''
Created on 2012-03-04

@author: Gooble
'''

class MockEventHandler(object):
    def __init__(self, game):
        self.game = game
    
    def process(self, event):
        self.processed = True