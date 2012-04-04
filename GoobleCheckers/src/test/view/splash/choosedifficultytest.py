'''
Created on 2012-02-26

@author: Gooble
'''
import unittest
from main.view.splash.choosedifficulty import ChooseDifficultyScreen
from main.view.splash.splashscreen import SplashScreen
from main.game.gamemode import GameMode

class Test(unittest.TestCase):

    def test_after_choosing_difficulty_it_is_set_in_game_mode(self):
        screen = ChooseDifficultyScreen()
        game_mode = GameMode()
        self.assertEqual("Medium", game_mode.get_mode())
        next_screen = screen.choose_option("Easy", game_mode)
        self.assertEqual("Easy", game_mode.get_mode())
        self.assertEqual(type(next_screen), ChooseDifficultyScreen)
        
    def test_go_back_returns_to_splash_screen(self):
        screen = ChooseDifficultyScreen()
        next_screen = screen.choose_option("Go Back", None)
        self.assertEqual(type(next_screen), SplashScreen)


if __name__ == "__main__":
    unittest.main()