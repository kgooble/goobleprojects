'''
Created on 2012-02-26

@author: Gooble
'''
import main.view.splash.newgame
import main.view.splash.choosedifficulty
import main.view.splash.howtoplay
import main.view.splash.quitting

class SplashScreen(object):
    
    def __init__(self):
        self.text = ["New Game", "Choose Difficulty", "How to Play", "Quit"];
        self.options = {"New Game"          : main.view.splash.newgame.NewGameCommand(), 
                        "Choose Difficulty" : main.view.splash.choosedifficulty.ChooseDifficultyCommand(), 
                        "How to Play"       : main.view.splash.howtoplay.HowToPlayCommand(), 
                        "Quit"              : main.view.splash.quitting.QuitCommand()}
    
    def get_text(self):
        return self.text
    
    def choose_option(self, opt):
        return self.options[opt].process()