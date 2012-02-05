'''
Created on 2012-02-04

@author: Gooble
'''
import threading

class TaskThread(threading.Thread):
    def __init__(self, game):
        threading.Thread.__init__(self)
        self._finished = threading.Event()
        self._interval = 1
        self.game = game
    
    def setInterval(self, interval):
        self._interval = interval
    
    def shutdown(self):
        self._finished.set()
    
    def run(self):
        while not self.game.is_game_over():
            if self._finished.isSet(): return
            self.task()
            self._finished.wait(self._interval)
    
    def task(self):
        self.game.step()
        self.setInterval(self.game.get_speed() / 100)
