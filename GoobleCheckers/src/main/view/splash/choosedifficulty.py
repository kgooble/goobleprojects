'''
Created on 2012-02-26

@author: Gooble
'''
from main.view.splash.splashscreen import SplashScreen

class ChooseDifficultyScreen(object):
    def get_text(self):
        return ["Easy", "Medium", "Difficult", "Go Back"]
    
    def choose_option(self, opt, mode):
        if opt in self.get_text() and mode is not None:
            mode.set_difficulty(opt)
            return self
        
        return SplashScreen()

    
class ChooseDifficultyCommand(object):
    def process(self):
        return ChooseDifficultyScreen()