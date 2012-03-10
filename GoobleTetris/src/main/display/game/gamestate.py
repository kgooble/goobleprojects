'''
Created on 2012-03-04

@author: Gooble
'''
from main.display.state import State

class GameState(State):
    def __init__(self, eventhandler, view, gamethread):
        State.__init__(self, eventhandler, view)
        self.gamethread = gamethread
        
    def preprocess(self):
        self.gamethread.startIfNotAlready()
    
    def kill(self):
        self.gamethread.shutdown()
        self.gamethread.join()