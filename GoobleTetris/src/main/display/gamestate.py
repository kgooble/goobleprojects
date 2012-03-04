'''
Created on 2012-03-04

@author: Gooble
'''

class GameState(object):
    def __init__(self, gamethread, eventhandler, view):
        self.gamethread = gamethread
        self.eventhandler = eventhandler
        self.view = view
        
    def process(self, event):
        self.gamethread.startIfNotAlready()
        self.eventhandler.process(event)
    
    def display(self):
        self.view.display()
        
    def kill(self):
        self.gamethread.shutdown()
        self.gamethread.join()