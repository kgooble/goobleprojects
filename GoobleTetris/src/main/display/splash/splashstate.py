'''
Created on 2012-03-09

@author: Gooble
'''
import main.display.state
import main.display.splash.splashstateview

class SplashState(main.display.state.State):
    
    def __init__(self, eventhandler=main.display.quitplushandler.QuitPlusHandler(main.display.buttonhandler.ButtonClickHandler(None)), view=main.display.splash.splashstateview.SplashStateView()):
        main.display.state.State.__init__(self, eventhandler, view)
            
    def preprocess(self):
        self.eventhandler.otherhandler.buttons = self.view.drawables
