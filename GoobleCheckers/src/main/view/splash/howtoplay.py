'''
Created on 2012-02-26

@author: Gooble
'''

class HowToPlayScreen(object):
    def get_text(self):
        return ["To play, use the mouse to click and drag checkers pieces across the board.",
                "If you choose to play against the AI, you start off as the top player, and can go first."
                "Go Back"]


class HowToPlayCommand(object):
    def process(self):
        return HowToPlayScreen()