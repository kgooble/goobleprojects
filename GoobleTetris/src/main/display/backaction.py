'''
Created on 2012-03-11

@author: Gooble
'''

import main.display.splash.state

class BackAction(object):
    def do_action(self, info):
        return main.display.splash.state.SplashState()