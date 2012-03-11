'''
Created on 2012-03-04

@author: Gooble
'''

class State(object):
    def __init__(self, eventhandler, view):
        self.eventhandler = eventhandler
        self.eventhandler.state = self
        self.view = view
        
    def preprocess(self):
        pass
        
    def process(self, event, info):
        self.preprocess()
        return self.eventhandler.process_general(event, info)
    
    def display(self, screen):
        self.view.display(screen)
        
    def kill(self):
        pass
