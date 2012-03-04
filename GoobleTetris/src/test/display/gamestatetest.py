'''
Created on 2012-03-04

@author: Gooble
'''
import unittest
from test.display.mock.mockgame import MockGame
from test.display.mock.mockthread import MockGameThread
from test.display.mock.mockeventhandler import MockEventHandler
from test.display.mock.mockevent import MockEvent
from main.display.gamestate import GameState
from test.display.mock.mockdisplay import MockDisplay

class Test(unittest.TestCase):
    
    def test_use_eventhandler_and_start_thread_first_time_processing_then_draw_screen(self):
        game = MockGame()
        gamethread = MockGameThread(game)
        eventhandler = MockEventHandler(game)
        display = MockDisplay(game)
        
        state = GameState(gamethread, eventhandler, display)
        
        state.process(MockEvent())
        state.display()
        
        self.assertTrue(gamethread.started)
        self.assertTrue(eventhandler.processed)
        self.assertTrue(display.displayed)
        
    def test_kill_state_kills_thread(self):
        game = MockGame()
        game.set_game_over(True)
        gamethread = MockGameThread(game)
        eventhandler = MockEventHandler(game)
        display = MockDisplay(game)
        
        state = GameState(gamethread, eventhandler, display)
        
        state.kill()
        
        self.assertTrue(gamethread.gotshutdown)
        self.assertTrue(gamethread.gotjoined)

if __name__ == "__main__":
    unittest.main()