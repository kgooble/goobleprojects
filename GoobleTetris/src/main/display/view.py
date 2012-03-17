'''
Created on 2012-03-10

@author: Gooble
'''
class View(object):
    def __init__(self):
        self.layout = None
        self.buttons = None
        
    def display(self, screen):
        if self.layout is None:
            self.create_layout()
        self.layout.draw(screen)
            
    def create_layout(self):
        pass
