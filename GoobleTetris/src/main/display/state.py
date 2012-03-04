'''
Created on 2012-03-04

@author: Gooble
'''

class State(object):
    def __init__(self, eventhandler, view):
        self.eventhandler = eventhandler
        self.view = view
        
    def preprocess(self):
        pass
        
    def process(self, event):
        self.preprocess()
        self.eventhandler.process(event)    
    
    def display(self):
        self.view.display()
        
    def kill(self):
        pass
