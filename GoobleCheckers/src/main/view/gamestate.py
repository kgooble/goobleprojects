'''
Created on 2012-02-26

@author: Gooble
'''
from main.ai import minimaxai, aithread
from main.view.gamestateprocess import ProcessGameState
import main.view.checkers
import main.view.drawing.canvas
import main.view.drawing.drawables

TIMEOUT = 10

AIS = {"Easy": minimaxai.MinimaxAI(1), "Medium" : minimaxai.MinimaxAI(2), "Hard" : minimaxai.MinimaxAI(3)}
class GameState(object):
    
    def __init__(self, info={"mode":"Easy"}):
        self.game = main.view.checkers.Checkers(ai=AIS[info["mode"]])
        self.aithread = None 
        self.info = info

    def display(self, screen, pos=None):
        main.view.drawing.canvas.Canvas(screen).draw(self.create_drawables(pos))
    
    def process(self, event):
        ProcessGameState(self.game).handle(event)
        return self
        
    def post_process(self):
        self.check_and_use_ai()
        
    def check_and_use_ai(self):
        if self.time_to_use_ai():
            if self.aithread is not None:
                self.aithread.join(TIMEOUT)
            self.aithread = aithread.AIThread(self.game)
            self.aithread.start()
                
    def time_to_use_ai(self):
        return not self.game.is_game_over() and (self.game.is_computers_turn() and (self.aithread is None or self.aithread.finished))
    
    def create_drawables(self, position):
        return main.view.drawing.drawables.Drawables(self.game).create(position)