'''
Created on 2012-02-25

@author: Gooble
'''
import unittest
from main.view.splash.splashscreen import SplashScreen

class Test(unittest.TestCase):

    def setUp(self):
        self.splash = SplashScreen()

    def test_get_text_from_initial_splashscreen_shows_all_menu_options(self):
        self.assertEqual(["New Game", "Choose Difficulty", "How to Play", "Quit"], self.splash.get_text())
        
    def test_choose_difficulty_shows_all_difficulty_options(self):
        n = self.splash.choose_option("Choose Difficulty")
        self.assertEqual(["Easy", "Medium", "Difficult", "Go Back"], n.get_text())
        
    def test_how_to_play_shows_instructions(self):
        n = self.splash.choose_option("How to Play")
        self.assertEqual(["To play, use the mouse to click and drag checkers pieces across the board.",
                          "If you choose to play against the AI, you start off as the top player, and can go first."
                          "Go Back"], n.get_text())
        


if __name__ == "__main__":
    unittest.main()