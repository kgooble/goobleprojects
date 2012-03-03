'''
Created on 2012-02-26

@author: Gooble
'''
class EventHandler(object):
    
    def handle(self, event):
        try:
            pos = event.pos
        except AttributeError:
            pos = None
        return pos
