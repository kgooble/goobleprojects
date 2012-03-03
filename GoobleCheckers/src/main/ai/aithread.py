'''
Created on 2012-02-26

@author: Gooble
'''
import threading

class AIThread(threading.Thread):
    def __init__(self, game):
        threading.Thread.__init__(self)
        self.game = game
        self.finished = False
    
    def run(self):
        self.game.make_ai_move()
        self.finished = True
        
    def stop(self):
        self.finished = True