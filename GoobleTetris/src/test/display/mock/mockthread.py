'''
Created on 2012-03-04

@author: Gooble
'''
class MockGameThread(object):
    def __init__(self, game):
        self.game = game
        
    def startIfNotAlready(self):
        self.started = True
        
    def shutdown(self):
        self.gotshutdown = True
        
    def join(self):
        self.gotjoined = True